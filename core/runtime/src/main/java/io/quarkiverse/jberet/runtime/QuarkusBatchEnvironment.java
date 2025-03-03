package io.quarkiverse.jberet.runtime;

import java.util.Properties;

import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanManager;

import org.eclipse.microprofile.config.ConfigProvider;
import org.jberet.creation.AbstractArtifactFactory;
import org.jberet.repository.JobRepository;
import org.jberet.spi.ArtifactFactory;
import org.jberet.spi.BatchEnvironment;
import org.jberet.spi.JobExecutor;
import org.jberet.spi.JobTask;
import org.jberet.spi.JobXmlResolver;

import io.quarkus.arc.Arc;
// import jakarta.transaction.TransactionManager;
import jakarta.transaction.TransactionManager;

class QuarkusBatchEnvironment implements BatchEnvironment {
    private final ArtifactFactory artifactFactory;
    private final JobExecutor jobExecutor;
    private final JobRepository jobRepository;
    private final TransactionManager transactionManager;

    private static final Properties PROPS = new Properties();

    public QuarkusBatchEnvironment(
            final JBeretConfig config,
            final JobExecutor jobExecutor,
            final TransactionManager transactionManager) {

        this.artifactFactory = new QuarkusArtifactFactory();
        this.jobExecutor = jobExecutor;
        this.jobRepository = JBeretRepositoryFactory.getJobRepository(config);
        this.transactionManager = transactionManager;
    }

    @Override
    public ClassLoader getClassLoader() {
        ClassLoader cl = Thread.currentThread().getContextClassLoader();
        if (cl == null) {
            cl = QuarkusBatchEnvironment.class.getClassLoader();
        }
        return cl;
    }

    @Override
    public ArtifactFactory getArtifactFactory() {
        return artifactFactory;
    }

    @Override
    public void submitTask(JobTask jobTask) {
        jobExecutor.execute(jobTask);
    }

    @Override
    public TransactionManager getTransactionManager() {
        return transactionManager;
    }

    @Override
    public JobRepository getJobRepository() {
        return jobRepository;
    }

    @Override
    public JobXmlResolver getJobXmlResolver() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Properties getBatchConfigurationProperties() {
        return PROPS;
    }

    @Override
    public String getApplicationName() {
        return ConfigProvider.getConfig().getConfigValue("quarkus.application.name").getValue();
    }

    static class QuarkusArtifactFactory extends AbstractArtifactFactory {
        @Override
        public Object create(String ref, Class<?> cls, ClassLoader classLoader) {
            BeanManager bm = Arc.container().beanManager();
            Bean<?> bean = bm.resolve(bm.getBeans(ref));
            return bean == null ? null : bm.getReference(bean, bean.getBeanClass(), bm.createCreationalContext(bean));
        }

        @Override
        public Class<?> getArtifactClass(String ref, ClassLoader classLoader) {
            BeanManager bm = Arc.container().beanManager();
            Bean<?> bean = bm.resolve(bm.getBeans(ref));
            return bean == null ? null : bean.getBeanClass();
        }
    }
}

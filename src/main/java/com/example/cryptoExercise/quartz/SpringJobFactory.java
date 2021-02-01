package com.example.cryptoExercise.quartz;

import org.quartz.spi.TriggerFiredBundle;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;

/**
 * Inject quartz job. Supports Spring-style dependency injection on bean properties.
 * This is essentially the direct equivalent of Spring’s QuartzJobBean in the shape of a Quartz JobFactory.
 */
public final class SpringJobFactory extends SpringBeanJobFactory implements ApplicationContextAware {
    private transient AutowireCapableBeanFactory beanFactory;
    @Override
    public void setApplicationContext(final ApplicationContext context) {
        beanFactory = context.getAutowireCapableBeanFactory();
    }
    @Override
    protected Object createJobInstance(final TriggerFiredBundle bundle) throws Exception {
        final Object job = super.createJobInstance(bundle);
        System.out.println("create job instance");
        beanFactory.autowireBean(job);
        return job;
    }
}

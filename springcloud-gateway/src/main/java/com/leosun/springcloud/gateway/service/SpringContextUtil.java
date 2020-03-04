package com.leosun.springcloud.gateway.service;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Locale;
@Component
public class SpringContextUtil implements ApplicationContextAware {
    private static ApplicationContext context = null;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContextUtil .context = applicationContext;
    }
    //获取上下文
    public static ApplicationContext getApplicationContext(){
        return context;
    }


    // 通过名字获取上下文的Bean
    public static <T> T getBean(String beanName) {
        return (T) context.getBean(beanName);
    }

    //通过类型获取上下文的bean
    public static <T> T getBean(Class<?> requiredType){
        return (T)context.getBean(requiredType);
    }


    // 国际化使用
    public static String getMessage(String key) {
        return context.getMessage(key, null, Locale.getDefault());
    }

    // 获取当前环境
    public static String[] getActiveProfiles() {
        return context.getEnvironment().getActiveProfiles();
    }
}

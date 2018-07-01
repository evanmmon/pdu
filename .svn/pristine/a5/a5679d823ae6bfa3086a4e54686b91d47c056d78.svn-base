package com.chuangkou.pdu.thread;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import static org.apache.commons.lang.Validate.notEmpty;

/**
 * @Author:
 * @Description:
 * @Date:Created in 17:49 2018/4/9
 */
public class SpringApplicationContextHolder  implements ApplicationContextAware {


    private static ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        SpringApplicationContextHolder.context = context;
    }


    public static Object getSpringBean(String beanName) {
        notEmpty(beanName, "bean name is required");
        return context==null?null:context.getBean(beanName);
    }



    public static String[] getBeanDefinitionNames() {
        return context.getBeanDefinitionNames();
    }
}

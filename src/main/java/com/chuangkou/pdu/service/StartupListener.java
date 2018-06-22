package com.chuangkou.pdu.service;

import com.chuangkou.pdu.controller.PduController;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * @Author:xulei
 * @Description:服务自动启动程序
 * @Date:Created in 17:26 2018/4/25
 */
//@Service 暂时关闭
public class StartupListener implements ApplicationListener<ContextRefreshedEvent> {


    @Override
    public void onApplicationEvent(ContextRefreshedEvent evt) {

        // 防止启动两次
        if (evt.getApplicationContext().getParent() != null) {
            buildIndex();
        }

    }

    private void buildIndex() {

        System.out.println("启动后自动运行Socket线程池......");
//        PduController.jsonPdutest();

    }
}



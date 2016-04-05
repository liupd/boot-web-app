package com.wa.net.mdb;

import com.wa.net.utils.ActivemqConstant;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;


@Component
public class TestMdb {

    Map<String,String> map=new HashMap<>();

    @JmsListener(destination = ActivemqConstant.TEST_QUEUE)
    public void doWork(TextMessage textMessage, Session session) throws Throwable {
        String msg=textMessage.getText();
        map.put(ActivemqConstant.TEST_QUEUE,msg);
        System.out.println(session.getMessageListener());
        System.out.println(msg);
    }

    public String getListenMsg(){
      return  map.get(ActivemqConstant.TEST_QUEUE);
    }

}

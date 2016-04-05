package com.wa.net.controller;

import com.alibaba.fastjson.JSON;
import com.wa.net.mdb.TestMdb;
import com.wa.net.utils.ActivemqConstant;
import com.wa.net.utils.AjaxStatus;
import com.wa.net.utils.JsonResult;
import com.wa.net.vo.UserVo;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.servlet.http.HttpSession;

/**
 * Created by Administrator on 16-4-1.
 **/
@Controller
@RequestMapping("/mq")
public class MqController {

    @Resource
    private JmsTemplate jmsTemplate;

    @Resource
    private TestMdb testMdb;

    @RequestMapping("/dateToMq")
    public @ResponseBody String dateToMq(@RequestBody final UserVo userVo) {
        JsonResult result=new JsonResult(AjaxStatus.OK);
        jmsTemplate.send(ActivemqConstant.TEST_QUEUE, new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage(JSON.toJSONString(userVo));
            }
        });
        result.setBean(testMdb.getListenMsg());
        result.setResultstr("success");
        return JSON.toJSONString(result);
    }





}

package com.wa.net;

import com.wa.net.utils.ActivemqConstant;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.Queue;

@Configuration
public class SpringConfigActivemq {

    @Value("${spring.jms.broker-url}")
    private String brokerUrl;

    @Bean
    public ActiveMQQueue testQueue() {
        return new ActiveMQQueue(ActivemqConstant.TEST_QUEUE);
    }

    @Bean(name = "jmsConnectionFactory")
    public ActiveMQConnectionFactory connectionFactory() {
        return new ActiveMQConnectionFactory(brokerUrl);
    }

    @Bean(name = "jmsListenerContainerFactory")
    public DefaultJmsListenerContainerFactory jmsListenerContainerFactory() {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory());
        factory.setConcurrency("3-10");
        return factory;
    }

    @Bean(name = "jmsTemplate")
    public JmsTemplate jmsTemplate() {
        JmsTemplate template = new JmsTemplate();
        template.setConnectionFactory(connectionFactory());
        return template;
    }

    @Bean
    public Queue emailQueue() {
        return new ActiveMQQueue(ActivemqConstant.EMAIL_QUEUE);
    }

    @Bean
    public Queue SMSQueue() {
        return new ActiveMQQueue(ActivemqConstant.SMS_QUEUE);
    }

}

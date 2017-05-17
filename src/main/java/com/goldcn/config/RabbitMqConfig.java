package com.goldcn.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * Created by Administrator on 2017/5/17.
 */
@Configuration
@ConfigurationProperties(prefix = "rabbitmq")
public class RabbitMqConfig {

    public static final String EXCHANGE   = "spring-boot-exchange";
    public static final String ROUTINGKEY = "spring-boot-routingKey";

    private String adress;
    private String virtualHost;
    private String username;
    private String password;

    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setAddresses(this.adress);
        connectionFactory.setUsername(this.username);
        connectionFactory.setPassword(this.password);
        connectionFactory.setVirtualHost(this.virtualHost);
        connectionFactory.setPublisherConfirms(true); //必须要设置 进行消息的回调
        return connectionFactory;
    }

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE) //必须是prototype类型
    public RabbitTemplate rabbitTemplate() {
        RabbitTemplate template = new RabbitTemplate(connectionFactory());
        return template;
    }

    /**
     * 针对消费者配置
     FanoutExchange: 将消息分发到所有的绑定队列，无routingkey的概念
     HeadersExchange ：通过添加属性key-value匹配
     DirectExchange:按照routingkey分发到指定队列
     TopicExchange:多关键字匹配
     */
    @Bean
    public DirectExchange defaultExchange() {
        return new DirectExchange(EXCHANGE);
    }

    @Bean
    public Queue queue() {
        return new Queue("hello", true); //队列持久
    }

    @Bean
    public Binding binding() {
        return BindingBuilder.bind(queue()).to(defaultExchange()).with(RabbitMqConfig.ROUTINGKEY);
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public void setVirtualHost(String virtualHost) {
        this.virtualHost = virtualHost;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

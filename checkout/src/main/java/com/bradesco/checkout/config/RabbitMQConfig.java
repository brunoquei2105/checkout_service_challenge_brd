//package com.bradesco.checkout.config;
//
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.amqp.core.AmqpAdmin;
//import org.springframework.amqp.core.AmqpTemplate;
//import org.springframework.amqp.core.Binding;
//import org.springframework.amqp.core.BindingBuilder;
//import org.springframework.amqp.core.DirectExchange;
//import org.springframework.amqp.core.Queue;
//import org.springframework.amqp.rabbit.annotation.EnableRabbit;
//import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
//import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
//import org.springframework.amqp.rabbit.connection.ConnectionFactory;
//import org.springframework.amqp.rabbit.core.RabbitAdmin;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.amqp.rabbit.listener.ConditionalRejectingErrorHandler;
//import org.springframework.amqp.rabbit.support.ListenerExecutionFailedException;
//import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
//import org.springframework.amqp.support.converter.MessageConverter;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.springframework.util.ErrorHandler;
//
//@EnableRabbit
//@Slf4j
//public class RabbitMQConfig {
//
//    @Value("${spring.rabbitmq.queue}")
//    private String queueName;
//
//    @Value("${spring.rabbitmq.exchange}")
//    private String exchange;
//
//    @Value("${spring.rabbitmq.routingkey}")
//    private String routingkey;
//
//    @Value("${spring.rabbitmq.username}")
//    private String username;
//
//    @Value("${spring.rabbitmq.password}")
//    private String password;
//
//    @Value("${spring.rabbitmq.host}")
//    private String host;
//
//    @Value("${spring.rabbitmq.virtual-host}")
//    private String virtualHost;
//
//    @Value("${spring.rabbitmq.reply.timeout}")
//    private Integer replyTimeout;
//
//
//    @Bean
//    public Queue queue() {
//        return new Queue(queueName, false);
//    }
//
//    @Bean
//    public DirectExchange exchange() {
//        return new DirectExchange(exchange);
//    }
//
//    @Bean
//    public Binding binding(Queue queue, DirectExchange exchange) {
//        return BindingBuilder
//                .bind(queue)
//                .to(exchange)
//                .with(routingkey);
//    }
//
//    @Bean
//    public MessageConverter jsonMessageConverter() {
//        ObjectMapper objectMapper = new ObjectMapper();
//        return new Jackson2JsonMessageConverter(objectMapper);
//    }
//
//    @Bean
//    public ConnectionFactory connectionFactory(){
//        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
//        connectionFactory.setHost(host);
//        connectionFactory.setVirtualHost(virtualHost);
//        connectionFactory.setUsername(username);
//        connectionFactory.setPassword(password);
//        return connectionFactory;
//    }
//
//    @Bean
//    public AmqpAdmin amqpAdmin(){
//        return new RabbitAdmin(connectionFactory());
//    }
//    @Bean
//    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory() {
//        final SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
//        factory.setConnectionFactory(connectionFactory());
//        factory.setMessageConverter(jsonMessageConverter());
//        factory.setErrorHandler(errorHandler());
//        return factory;
//    }
//
//    @Bean
//    public ErrorHandler errorHandler() {
//        return new ConditionalRejectingErrorHandler(new MyFatalExceptionStrategy());
//    }
//
//
//    public static class MyFatalExceptionStrategy extends ConditionalRejectingErrorHandler.DefaultExceptionStrategy{
//
//        @Override
//        public boolean isFatal(Throwable t) {
//            if (t instanceof ListenerExecutionFailedException) {
//                ListenerExecutionFailedException lefe = (ListenerExecutionFailedException) t;
//                logger.error("Failed to process inbound message from queue "
//                        + lefe.getFailedMessage().getMessageProperties().getConsumerQueue()
//                        + "; failed message: " + lefe.getFailedMessage(), t);
//            }
//            return super.isFatal(t);
//        }
//    }
//}
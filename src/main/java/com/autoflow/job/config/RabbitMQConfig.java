
package com.autoflow.job.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.Connection;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String JOB_QUEUE = "autoflow.jobs.queue";
    public static final String JOB_EXCHANGE = "autoflow.jobs.exchange";
    public static final String JOB_ROUTING_KEY = "autoflow.jobs.routing-key";

    @Bean
    public Queue jobQueue() {
        return new Queue(JOB_QUEUE, true);
    }

    @Bean
    public DirectExchange jobExchange() {
        return new DirectExchange(JOB_EXCHANGE, true, false);
    }

    @Bean
    public Binding jobBinding(
            Queue jobQueue,
            DirectExchange jobExchange) {
        return BindingBuilder
                .bind(jobQueue)
                .to(jobExchange)
                .with(JOB_ROUTING_KEY);
    }

    @Bean
    public RabbitAdmin rabbitAdmin(
            ConnectionFactory connectionFactory) {
        return new RabbitAdmin(connectionFactory);
    }

    @Bean
    public String testRabbitMQConnection(
            ConnectionFactory connectionFactory) {
        System.out.println(
                ">>> Testing RabbitMQ connection...");

        try (Connection connection =
                     connectionFactory.createConnection()) {
            System.out.println(
                    ">>> RabbitMQ connection successful!");
        } catch (Exception e) {
            System.err.println(
                    ">>> RabbitMQ connection failed!");
            e.printStackTrace();
        }

        return "rabbitmq-connection-test";
    }
}

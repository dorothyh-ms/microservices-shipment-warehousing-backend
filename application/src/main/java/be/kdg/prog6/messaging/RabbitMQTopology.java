package be.kdg.prog6.messaging;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.DefaultJackson2JavaTypeMapper;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration
public class RabbitMQTopology {

    @Bean
    Queue weighbridgeDepartureQueues() {
        return new Queue("weighbridge_departures", false);
    }

    @Bean
    TopicExchange warehouseExchange() {
        return new TopicExchange("warehouse_exchange");
    }



    @Bean
    Binding bindFamilyExchangeToActivityCreatedQueue(
            Queue warehouseActivityCreatedQueue,
            TopicExchange warehouseExchange
    ){
        return BindingBuilder
                .bind(warehouseActivityCreatedQueue)
                .to(warehouseExchange)
                .with("weighbridge_departures");
    }

    @Bean
    RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory, ObjectMapper mapper) {
        final var rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(producerJackson2MessageConverter(mapper));
        return rabbitTemplate;
    }


    @Bean
    Jackson2JsonMessageConverter producerJackson2MessageConverter(ObjectMapper mapper) {
        mapper.registerModule(new JavaTimeModule());
        return  new Jackson2JsonMessageConverter(mapper);
    }


}

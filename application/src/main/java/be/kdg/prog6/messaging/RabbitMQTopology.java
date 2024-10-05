package be.kdg.prog6.messaging;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration
public class RabbitMQTopology {


    // Exchanges
    public static final String LANDSIDE_DELIVERIES_QUEUE = "landside_deliveries";
    public static final String WAREHOUSE_EVENTS_QUEUE = "warehouse_events_queue";


    // Queues
    public static final String WEIGHBRIDGE_DEPARTURES = "weighbridge_departures";

    public static final String WAREHOUSE_EVENTS_FAN_OUT = "warehouse_events";


    @Bean
    Queue weighbridgeDepartureQueue() {
        return new Queue(WEIGHBRIDGE_DEPARTURES, false);
    }

    @Bean
    DirectExchange landsideDeliveriesExchange() {
        return new DirectExchange(LANDSIDE_DELIVERIES_QUEUE);
    }



    @Bean
    FanoutExchange warehouseEventsExchange() {
        return new FanoutExchange(WAREHOUSE_EVENTS_FAN_OUT);
    }

    @Bean
    Queue warehouseEventsQueue() {
        return new Queue(WAREHOUSE_EVENTS_QUEUE);
    }


    @Bean
    Binding bindLandsideDeliveriesExchangeToWeighbridgeDeparturesQueue(
            Queue weighbridgeDepartureQueue,
            DirectExchange landsideDeliveriesExchange
    ){
        return BindingBuilder
                .bind(weighbridgeDepartureQueue)
                .to(landsideDeliveriesExchange)
                .with(WEIGHBRIDGE_DEPARTURES);
    }

    @Bean
    Binding bindWarehouseEventsExchangeToWarehouseActivitesQueue(
            Queue warehouseEventsQueue,
            FanoutExchange warehouseEventsExchange
    ){
        return BindingBuilder.bind(warehouseEventsQueue).to(warehouseEventsExchange);
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

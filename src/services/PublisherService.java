package services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import configurations.RabbitConfig;
import models.AlertNotification;
import models.TrashNotification;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

public class PublisherService implements IPublisherService{
    private static ConnectionFactory connectionFactory;
    private final ObjectMapper objectMapper = new ObjectMapper();


    public PublisherService() {
        connectionFactory = new ConnectionFactory();
        connectionFactory.setUsername(RabbitConfig.getUSERNAME());
        connectionFactory.setPassword(RabbitConfig.getPASSWORD());
        connectionFactory.setHost(RabbitConfig.getHOST());
    }

    @Override
    public void sendTrash(TrashNotification trashNotification) {

        try (Connection connection = connectionFactory.newConnection(); Channel channel = connection.createChannel()) {
            String messageToSend = objectMapper.writeValueAsString(trashNotification);


            channel.queueDeclarePassive(RabbitConfig.getTrashQueue());
            channel.basicPublish(RabbitConfig.getExchangeName(), RabbitConfig.getTrashRoutingKey(), null, messageToSend.getBytes(StandardCharsets.UTF_8));

            System.out.println(" [x] Sent '" + messageToSend + "'");
        } catch (IOException | TimeoutException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void sendAlert(AlertNotification alertNotification) {
        try (Connection connection = connectionFactory.newConnection(); Channel channel = connection.createChannel()) {
            String messageToSend = objectMapper.writeValueAsString(alertNotification);


            channel.queueDeclarePassive(RabbitConfig.getAlertQueue());
            channel.basicPublish(RabbitConfig.getExchangeName(), RabbitConfig.getAlertRoutingKey(), null, messageToSend.getBytes(StandardCharsets.UTF_8));

            System.out.println(" [x] Sent '" + messageToSend + "'");
        } catch (IOException | TimeoutException e) {
            throw new RuntimeException(e);
        }
    }
}

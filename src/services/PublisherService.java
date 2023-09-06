package services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import configurations.RabbitConfig;
import models.AlertNotification;
import models.TrashNotification;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

/**
 * Questa classe gestisce l'invio di notifiche relative ai bidoni di raccolta e agli avvisi
 * utilizzando una connessione RabbitMQ.
 */
public class PublisherService implements IPublisherService {
    // Factory di connessione RabbitMQ
    private static ConnectionFactory connectionFactory;

    // ObjectMapper per la conversione degli oggetti in formato JSON
    private final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Costruttore della classe PublisherService.
     * Configura la connessione RabbitMQ utilizzando le informazioni da RabbitConfig.
     */
    public PublisherService() {
        connectionFactory = new ConnectionFactory();
        connectionFactory.setUsername(RabbitConfig.getUSERNAME());
        connectionFactory.setPassword(RabbitConfig.getPASSWORD());
        connectionFactory.setHost(RabbitConfig.getHOST());
    }

    /**
     * Invia una notifica di rifiuto a RabbitMQ.
     *
     * @param trashNotification La notifica di rifiuto da inviare.
     */
    @Override
    public void sendTrash(TrashNotification trashNotification) {
        try (Connection connection = connectionFactory.newConnection(); Channel channel = connection.createChannel()) {
            String messageToSend = objectMapper.writeValueAsString(trashNotification);

            // Dichiarazione della coda (se esiste)
            channel.queueDeclarePassive(RabbitConfig.getTrashQueue());

            // Invio del messaggio alla coda di scambio
            channel.basicPublish(RabbitConfig.getExchangeName(), RabbitConfig.getTrashRoutingKey(), null, messageToSend.getBytes(StandardCharsets.UTF_8));

            System.out.println(" [x] Sent '" + messageToSend + "'");
        } catch (IOException | TimeoutException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Invia una notifica di avviso a RabbitMQ.
     *
     * @param alertNotification La notifica di avviso da inviare.
     */
    @Override
    public void sendAlert(AlertNotification alertNotification) {
        try (Connection connection = connectionFactory.newConnection(); Channel channel = connection.createChannel()) {
            String messageToSend = objectMapper.writeValueAsString(alertNotification);

            // Dichiarazione della coda (se esiste)
            channel.queueDeclarePassive(RabbitConfig.getAlertQueue());

            // Invio del messaggio alla coda di scambio
            channel.basicPublish(RabbitConfig.getExchangeName(), RabbitConfig.getAlertRoutingKey(), null, messageToSend.getBytes(StandardCharsets.UTF_8));

            System.out.println(" [x] Sent '" + messageToSend + "'");
        } catch (IOException | TimeoutException e) {
            throw new RuntimeException(e);
        }
    }
}

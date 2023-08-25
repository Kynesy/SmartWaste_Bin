import services.IPublisherService;
import services.PublisherService;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        IPublisherService publisherService = new PublisherService();
        publisherService.sendTrash("{ 'id': 'pippo'}");
    }
}
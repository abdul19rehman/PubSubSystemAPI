package pubsubsystem;

import org.springframework.web.bind.annotation.*;
import pubsubsystem.entities.Message;
import pubsubsystem.subscriber.AlertSubscriber;
import pubsubsystem.subscriber.NewsSubscriber;
import pubsubsystem.subscriber.Subscriber;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/pubsub")
public class PubSubController {

    private final PubSubService pubSubService = PubSubService.getInstance();
    private final Map<String, Subscriber> subscriberRegistry = new ConcurrentHashMap<>();

    // --- Topic APIs ---
    @PostMapping("/topics/{topicName}")
        public String createTopic(@PathVariable String topicName) {
        pubSubService.createTopic(topicName);
        return "Topic '" + topicName + "' created.";
    }

    // --- Subscriber APIs ---
    @PostMapping("/subscribe/{topicName}")
    public String subscribe(@PathVariable String topicName,
                            @RequestParam String subscriberId,
                            @RequestParam(defaultValue = "NEWS") String type) {
        Subscriber subscriber;

        if ("ALERT".equalsIgnoreCase(type)) {
            subscriber = new AlertSubscriber(subscriberId);
        } else {
            subscriber = new NewsSubscriber(subscriberId);
        }

        subscriberRegistry.put(subscriberId, subscriber);
        pubSubService.subscribe(topicName, subscriber);
        return "Subscriber '" + subscriberId + "' subscribed to topic '" + topicName + "'";
    }

    @PostMapping("/unsubscribe/{topicName}")
    public String unsubscribe(@PathVariable String topicName,
                              @RequestParam String subscriberId) {
        Subscriber subscriber = subscriberRegistry.get(subscriberId);
        if (subscriber == null) {
            return "Subscriber not found: " + subscriberId;
        }
        pubSubService.unsubscribe(topicName, subscriber);
        return "Subscriber '" + subscriberId + "' unsubscribed from topic '" + topicName + "'";
    }

    // --- Publish API ---
    @PostMapping("/publish/{topicName}")
    public String publish(@PathVariable String topicName,
                          @RequestParam String message) {
        pubSubService.publish(topicName, new Message(message));
        return "Message published to topic '" + topicName + "'";
    }

    // --- Shutdown API ---
    @PostMapping("/shutdown")
    public String shutdown() {
        pubSubService.shutdown();
        return "PubSubService shut down successfully.";
    }
}

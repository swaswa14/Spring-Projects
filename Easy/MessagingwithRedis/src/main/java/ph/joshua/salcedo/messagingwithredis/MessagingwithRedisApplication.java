package ph.joshua.salcedo.messagingwithredis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

@SpringBootApplication
@Slf4j
public class MessagingwithRedisApplication {

    public static void main(String[] args) throws InterruptedException {
        ApplicationContext context = SpringApplication.run(MessagingwithRedisApplication.class,args);

        StringRedisTemplate template = context.getBean(StringRedisTemplate.class);

        Receiver receiver = context.getBean(Receiver.class);
        while(receiver.getCount() == 0) {
            log.info("Sending message. . .");
            template.convertAndSend("chat", "Hello from Redis!");
            Thread.sleep(500L);
        }

        System.exit(0);
    }




    @Bean
    public RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory, MessageListenerAdapter listenerAdapter) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.addMessageListener(listenerAdapter, new PatternTopic("chat"));

        return container;
    }

    @Bean
    public MessageListenerAdapter listenerAdapter(Receiver receiver) {
        return new MessageListenerAdapter(receiver, "receiveMessage");

    }

    @Bean
    public Receiver receiver(){
        return new Receiver();
    }

    @Bean
    public StringRedisTemplate template(RedisConnectionFactory connectionFactory) {
        return new StringRedisTemplate(connectionFactory);
    }


}

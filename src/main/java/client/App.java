package client;

import client.util.redis.QueueRedisUtil;
import model.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.PropertySource;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;

@SpringBootApplication
@EnableCaching
@PropertySource(value = "classpath:application.properties")
public class App {
    @Autowired
    private QueueRedisUtil<Data> queueRedisUtil;

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @PostConstruct
    private void init() {
        System.out.println("start");
        for (int i = 0; i < 1000000; i++) {
            long startTime = System.currentTimeMillis();
            queueRedisUtil.addToDS("queue_name", -1, new Data(i));
            long endTime = System.currentTimeMillis();
            long diff = endTime - startTime;
            if (diff > 0) {
                System.out.println(i + ", " + diff);
            }
        }
    }
}

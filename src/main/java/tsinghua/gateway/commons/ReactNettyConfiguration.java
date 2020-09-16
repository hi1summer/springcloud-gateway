package tsinghua.gateway.commons;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorResourceFactory;

@Configuration
public class ReactNettyConfiguration {
    @Value("${reactor.netty.worker-count}")
    private String workerCount;

    @Bean
    public ReactorResourceFactory reactorClientResourceFactory() {
        System.setProperty("reactor.netty.ioWorkerCount", workerCount);
        return new ReactorResourceFactory();
    }
}
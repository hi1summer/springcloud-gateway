package tsinghua.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

@RestController
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    KeyResolver ipResolver() {
        return new KeyResolver() {

            @Override
            public Mono<String> resolve(ServerWebExchange exchange) {
                String ipString = exchange.getRequest().getRemoteAddress()
                        .getAddress().getHostAddress();
                System.out.println(ipString);
                return Mono.just(ipString);
            }
        };
    }
}

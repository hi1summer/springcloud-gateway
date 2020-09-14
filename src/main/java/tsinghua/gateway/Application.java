package tsinghua.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

@RestController
@SpringCloudApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @GetMapping("/fallback")
    public ResponseEntity<Boolean> fallback() {
        return new ResponseEntity<Boolean>(false, HttpStatus.TOO_MANY_REQUESTS);
    }

    @Bean
    KeyResolver ipResolver() {
        return exchange -> Mono.just(exchange.getRequest().getRemoteAddress()
                .getAddress().getHostAddress());
    }
}

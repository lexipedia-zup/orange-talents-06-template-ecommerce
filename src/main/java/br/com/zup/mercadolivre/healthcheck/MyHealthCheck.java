package br.com.zup.mercadolivre.healthcheck;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.health.Status;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class MyHealthCheck implements HealthIndicator {
    @Override
    public Health getHealth(boolean includeDetails) {
        return HealthIndicator.super.getHealth(includeDetails);
    }

    @Override
    public Health health() {
        Map<String, Object> details = new HashMap<>();
        List
        details.put("version", "1.2.3");
        details.put("description", "Detalhes do meu primeiro health check");
        details.put("address", "127.0.0.1");
        return Health.status(Status.UP).withDetails(details).build();
    }
}

package apigateway.apigateway;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayRoutesConfig {

	@Bean
	RouteLocator productServiceRoutes(RouteLocatorBuilder builder) {
		return builder.routes()
				.route("product-service",
						r -> r.path("/products", "/products/", "/products/**").uri("lb://PRODUCT-SERVICE"))
				.build();
	}
}

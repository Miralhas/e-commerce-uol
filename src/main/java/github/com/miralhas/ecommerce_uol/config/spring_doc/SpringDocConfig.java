package github.com.miralhas.ecommerce_uol.config.spring_doc;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.tags.Tag;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class SpringDocConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI().info(new Info()
                        .title("e-Commerce Uol Compass")
                        .version("v1.0.0")
                        .description("e-Commerce Uol RESTful API Documentation")
                )
                .tags(
                        Arrays.asList(
                                new Tag()
                                        .name("Products")
                                        .description("Product Endpoints"),
                                new Tag()
                                        .name("Orders")
                                        .description("Order Endpoints")
                        )
                );
    }
}

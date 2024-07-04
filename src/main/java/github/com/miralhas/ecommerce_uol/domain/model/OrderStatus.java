package github.com.miralhas.ecommerce_uol.domain.model;

import lombok.Getter;

@Getter
public enum OrderStatus {
    CREATED("Created"),
    CONFIRMED("Confirmed"),
    CANCELED("Canceled");

    private final String descricao;

    OrderStatus(String descricao) {
        this.descricao = descricao;
    }
}

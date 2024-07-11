package github.com.miralhas.ecommerce_uol.domain.exception;

public class OrderNotFoundException extends ResourceNotFoundException {

    public OrderNotFoundException(Long id) {
        super("Pedido de código %d não foi encontrado.".formatted(id));
    }

    public OrderNotFoundException(Long id, Throwable cause) {
        super("Pedido de código %d não foi encontrado.".formatted(id), cause);
    }
}

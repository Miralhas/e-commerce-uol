package github.com.miralhas.ecommerce_uol.domain.exception;

public class ProductNotFoundException extends ResourceNotFoundException {

    public ProductNotFoundException(Long id) {
        super("Produto de código %d não foi encontrado.".formatted(id));
    }

    public ProductNotFoundException(Long id, Throwable cause) {
        super("Produto de código %d não foi encontrado.".formatted(id), cause);
    }
}

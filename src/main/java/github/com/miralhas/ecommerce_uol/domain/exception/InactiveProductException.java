package github.com.miralhas.ecommerce_uol.domain.exception;

public class InactiveProductException extends BusinessException {

    public InactiveProductException(Long id) {
        super("Produto de código %d está inativo".formatted(id));
    }

    public InactiveProductException(Long id, Throwable cause) {
        super("Produto de código %d está inativo".formatted(id), cause);
    }
}

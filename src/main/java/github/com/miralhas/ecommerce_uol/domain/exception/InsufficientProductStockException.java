package github.com.miralhas.ecommerce_uol.domain.exception;

public class InsufficientProductStockException extends BusinessException {

    public InsufficientProductStockException(Long id) {
        super("Quantitade requisitada excede o estoque disponível para o produto de código %d".formatted(id));
    }

    public InsufficientProductStockException(Long id, Throwable cause) {
        super("Quantitade requisitada excede o estoque disponível para o produto de código %d".formatted(id), cause);
    }
}

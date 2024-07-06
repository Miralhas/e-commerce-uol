package github.com.miralhas.ecommerce_uol.api.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PageDTO {
    private long totalItems;
    private Integer next;
    private Integer previous;
    private List<?> results;
    private int totalPages;
    private int currentPage;
}

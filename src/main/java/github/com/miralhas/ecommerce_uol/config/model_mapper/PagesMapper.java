package github.com.miralhas.ecommerce_uol.config.model_mapper;

import github.com.miralhas.ecommerce_uol.api.dto.PageDTO;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class PagesMapper {

    public PageDTO toModel(Page<?> pages) {
        PageDTO pageDTO = new PageDTO();
        pageDTO.setTotalPages(pages.getTotalPages());
        pageDTO.setResults(pages.getContent());
        pageDTO.setCurrentPage(pages.getNumber());
        pageDTO.setTotalItems(pages.getTotalElements());
        pageDTO.setNext(pages.hasNext() ? pages.getNumber()+1 : null);
        pageDTO.setPrevious(pages.hasPrevious() ? pages.getNumber()-1 : null);
        return pageDTO;
    }
}
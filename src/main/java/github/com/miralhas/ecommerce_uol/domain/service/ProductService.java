package github.com.miralhas.ecommerce_uol.domain.service;

import github.com.miralhas.ecommerce_uol.api.dto.input.ProductInput;
import github.com.miralhas.ecommerce_uol.api.dto_mapper.ProductUnmapper;
import github.com.miralhas.ecommerce_uol.domain.exception.ProductNotFoundException;
import github.com.miralhas.ecommerce_uol.domain.model.Product;
import github.com.miralhas.ecommerce_uol.domain.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductUnmapper productUnmapper;

    public List<Product> getAll() {
        return productRepository.findAll();
    }


    public Product findByIdOrException(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
    }


    @Transactional
    public Product save(Product product) {
        return productRepository.save(product);
    }


    @Transactional
    public Product update(Long id, ProductInput productInput) {
        Product product = findByIdOrException(id);
        productUnmapper.copyToDomainObject(productInput, product);
        return productRepository.save(product);
    }


    @Transactional
    public void delete(Long id) {
        productRepository.deleteById(id);
    }


}

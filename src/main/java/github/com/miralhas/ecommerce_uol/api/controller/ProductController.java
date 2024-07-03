package github.com.miralhas.ecommerce_uol.api.controller;

import github.com.miralhas.ecommerce_uol.api.dto.ProductDTO;
import github.com.miralhas.ecommerce_uol.api.dto.ProductSummaryDTO;
import github.com.miralhas.ecommerce_uol.api.dto.input.ProductInput;
import github.com.miralhas.ecommerce_uol.api.dto_mapper.ProductMapper;
import github.com.miralhas.ecommerce_uol.api.dto_mapper.ProductUnmapper;
import github.com.miralhas.ecommerce_uol.domain.model.Product;
import github.com.miralhas.ecommerce_uol.domain.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController {

    private final ProductMapper productMapper;
    private final ProductUnmapper productUnmapper;
    private final ProductService productService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductSummaryDTO> getAllProducts() {
        List<Product> products = productService.getAll();
        return productMapper.toSummaryCollectionModel(products);
    }


    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProductDTO getProductById(@PathVariable Long id) {
        Product product = productService.findByIdOrException(id);
        return productMapper.toModel(product);
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDTO createProduct(@RequestBody @Valid ProductInput productInput) {
        Product product = productUnmapper.toDomainObject(productInput);
        product = productService.save(product);
        return productMapper.toModel(product);
    }


    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProductDTO updateProduct(@PathVariable Long id, @RequestBody @Valid ProductInput productInput) {
        Product product = productService.update(id, productInput);
        return productMapper.toModel(product);
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable Long id) {
        productService.delete(id);
    }
}

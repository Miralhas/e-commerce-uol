package github.com.miralhas.ecommerce_uol.api.controller;

import github.com.miralhas.ecommerce_uol.api.dto.ProductDTO;
import github.com.miralhas.ecommerce_uol.api.dto.ProductSummaryDTO;
import github.com.miralhas.ecommerce_uol.api.dto.input.ProductInput;
import github.com.miralhas.ecommerce_uol.api.dto_mapper.ProductMapper;
import github.com.miralhas.ecommerce_uol.api.dto_mapper.ProductUnmapper;
import github.com.miralhas.ecommerce_uol.api.open_api.ProductControllerOpenAPI;
import github.com.miralhas.ecommerce_uol.domain.model.Product;
import github.com.miralhas.ecommerce_uol.domain.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController implements ProductControllerOpenAPI {

    private final ProductMapper productMapper;
    private final ProductUnmapper productUnmapper;
    private final ProductService productService;

    // Cache com Shallow ETags. src/main/java/config/web
    // https://docs.spring.io/spring-framework/reference/web/webmvc/mvc-caching.html
    @Override
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<ProductSummaryDTO>> getAllProducts() {
        List<Product> products = productService.getAll();
        List<ProductSummaryDTO> productsSummaryDTO = productMapper.toSummaryCollectionModel(products);
        return ResponseEntity.ok()
                .cacheControl(CacheControl.maxAge(10, TimeUnit.SECONDS))
                .body(productsSummaryDTO);
    }


    @Override
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProductDTO getProductById(@PathVariable Long id) {
        Product product = productService.findByIdOrException(id);
        return productMapper.toModel(product);
    }


    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ADMIN')")
    public ProductDTO createProduct(@RequestBody @Valid ProductInput productInput) {
        Product product = productUnmapper.toDomainObject(productInput);
        product = productService.save(product);
        return productMapper.toModel(product);
    }


    @Override
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ADMIN')")
    public ProductDTO updateProduct(@PathVariable Long id, @RequestBody @Valid ProductInput productInput) {
        Product product = productService.update(id, productInput);
        return productMapper.toModel(product);
    }


    @Override
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable Long id) {
        productService.delete(id);
    }


    @Override
    @PutMapping("/{id}/activate")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('ADMIN')")
    public void activateProduct(@PathVariable Long id) {
        productService.activateProduct(id);
    }


    @Override
    @PutMapping("/{id}/inactivate")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void inactivateProduct(@PathVariable Long id) {
        productService.inactivateProduct(id);
    }
}

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
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController {

    private final ProductMapper productMapper;
    private final ProductUnmapper productUnmapper;
    private final ProductService productService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<ProductSummaryDTO>> getAllProducts() {
        List<Product> products = productService.getAll();
        List<ProductSummaryDTO> productsSummaryDTO = productMapper.toSummaryCollectionModel(products);
        return ResponseEntity.ok()
                .cacheControl(CacheControl.maxAge(10, TimeUnit.SECONDS))
                .body(productsSummaryDTO);
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


    @PutMapping("/{id}/activate")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void activateProduct(@PathVariable Long id) {
        productService.activateProduct(id);
    }


    @PutMapping("/{id}/inactivate")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void inactivateProduct(@PathVariable Long id) {
        productService.inactivateProduct(id);
    }
}

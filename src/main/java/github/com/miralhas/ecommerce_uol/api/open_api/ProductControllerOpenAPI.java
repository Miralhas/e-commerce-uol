package github.com.miralhas.ecommerce_uol.api.open_api;

import github.com.miralhas.ecommerce_uol.api.dto.ProductDTO;
import github.com.miralhas.ecommerce_uol.api.dto.ProductSummaryDTO;
import github.com.miralhas.ecommerce_uol.api.dto.input.ProductInput;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Products")
public interface ProductControllerOpenAPI {

    @Operation(summary = "Get a list of all products")
    ResponseEntity<List<ProductSummaryDTO>> getAllProducts();

    @Operation(summary = "Get a single product")
    ProductDTO getProductById(@PathVariable Long id);

    @Operation(summary = "Create a product")
    ProductDTO createProduct(@RequestBody @Valid ProductInput productInput);

    @Operation(summary = "Update a product")
    ProductDTO updateProduct(@PathVariable Long id, @RequestBody @Valid ProductInput productInput);

    @Operation(summary = "Delete a product")
    void deleteProduct(@PathVariable Long id);

    @Operation(summary = "Activate a product")
    void activateProduct(@PathVariable Long id);

    @Operation(summary = "Inactivate a product")
    void inactivateProduct(@PathVariable Long id);
}

package dev.abraham.product_service.controller;

import dev.abraham.product_service.model.Product;
import dev.abraham.product_service.request.ProductRequest;
import dev.abraham.product_service.response.APIResponse;
import dev.abraham.product_service.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping("/create")
    public ResponseEntity<APIResponse> createProduct(@RequestBody ProductRequest productRequest){
        try{
            Product product=productService.addProduct(productRequest);
            return ResponseEntity.ok(new APIResponse("Product created successfully", product));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new APIResponse("Error creating product", e));
        }
    }

    @GetMapping("/all")
    public ResponseEntity<APIResponse> getAllProducts(){
        List<Product> products=productService.getAllProducts();
        return ResponseEntity.ok(new APIResponse("All products", products));
    }
}

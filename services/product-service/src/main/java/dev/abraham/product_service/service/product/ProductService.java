package dev.abraham.product_service.service.product;

import dev.abraham.product_service.model.Product;
import dev.abraham.product_service.repository.ProductRepository;
import dev.abraham.product_service.request.ProductRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService implements IProductService {
    private final ProductRepository productRepository;

    public Product addProduct(ProductRequest productRequest){
        Product product=createProduct(productRequest);
        return productRepository.save(product);
    }

    private Product createProduct(ProductRequest productRequest){
        return Product
                .builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice()).build();
    }

    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }
}

package dev.abraham.product_service.service.product;

import dev.abraham.product_service.model.Product;
import dev.abraham.product_service.request.ProductRequest;

public interface IProductService {
    Product addProduct(ProductRequest product);
}

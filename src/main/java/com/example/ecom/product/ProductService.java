package com.example.ecom.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    public Page<Product> getAllProducts(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    public void addProduct(Product aadProduct) {
        productRepository.save(aadProduct);
    }

    public Product getProductById(int id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()){
            return product.get();
        }
        else {
            return null;
        }
    }

    public void deleteProductById(int id) {
        productRepository.deleteById(id);
    }

    public void updateById(int id, Product updatedProduct) {
        Optional<Product> existingProduct = productRepository.findById(id);
        if (existingProduct.isPresent()){
            Product product = existingProduct.get();
            product.productName = updatedProduct.productName;
            product.productDescription = updatedProduct.productDescription;
            product.productPrice = updatedProduct.productPrice;
            product.productImage = updatedProduct.productImage;
            productRepository.save(product);
        }
    }
}

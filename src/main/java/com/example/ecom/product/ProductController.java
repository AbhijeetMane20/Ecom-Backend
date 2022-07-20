package com.example.ecom.product;

import net.bytebuddy.asm.Advice;
import net.bytebuddy.implementation.bind.annotation.Origin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://ecom-frontend-react-app.s3-website.ap-south-1.amazonaws.com")
@RestController("/")
public class ProductController {
    @Autowired
    ProductService productService;
    @GetMapping("/")
    public String welcome(){
        return "Welcome";
    }
    @GetMapping("/ping")
    public String ping(){
        return "Ping";
    }
    @GetMapping("/products")
    public Page<Product> getAllProductsList (Pageable pageable){
        return productService.getAllProducts(pageable);
    }
    @GetMapping("/product/{id}")
    public Product getProductById (@PathVariable int id){
        return productService.getProductById(id);
    }
    @PutMapping("/product")
    public void addProduct (@RequestBody Product product){
        productService.addProduct(product);
    }
    @DeleteMapping("/product/{id}")
    public void deleteProductById (@PathVariable int id){
        productService.deleteProductById(id);
    }
    @PostMapping("/product/{id}")
    public void updateById (@PathVariable int id, @RequestBody Product product){
        productService.updateById(id,product);
    }
}

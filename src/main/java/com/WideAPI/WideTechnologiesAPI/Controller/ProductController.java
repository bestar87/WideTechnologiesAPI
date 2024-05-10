package com.WideAPI.WideTechnologiesAPI.Controller;


import com.WideAPI.WideTechnologiesAPI.Entities.Product;
import com.WideAPI.WideTechnologiesAPI.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/getAllProducts")
    public ResponseEntity<Page<Product>> getAllProducts(@RequestParam(defaultValue = "0") int page,
                                                         @RequestParam(defaultValue = "10") int size){

        Pageable pageRequest = PageRequest.of(page, size);
        Page<Product> productsPage = productService.findAllProducts(pageRequest);

        if(productsPage.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(productsPage);
    }

    @GetMapping("/getProductsById/{productId}")
    public ResponseEntity<Product> getProductById(@PathVariable Long productId){

        Optional<Product> product = productService.findProductById(productId);
        if(product.isPresent()){
            return ResponseEntity.ok(product.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/createNewProduct")
    @ResponseStatus(HttpStatus.CREATED)
    public Product createNewProduct(@RequestBody Product product){
        return productService.createNewProduct(product);
    }

    @PutMapping("/updateProduct/{productId}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long productId, @RequestBody Product updatedProduct) {
        Product product = productService.updateProduct(productId, updatedProduct);
        if (product != null) {
            return ResponseEntity.ok(product);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/deleteProduct/{productId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable Long productId){
        productService.deleteProduct(productId);
    }

}

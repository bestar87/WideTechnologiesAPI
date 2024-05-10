package com.WideAPI.WideTechnologiesAPI.Service;


import com.WideAPI.WideTechnologiesAPI.Entities.Product;
import com.WideAPI.WideTechnologiesAPI.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;


    //read all products
    public Page<Product> findAllProducts(Pageable pageable){
        return productRepository.findAll(pageable);
    }

    // read products by Id
    public Optional<Product> findProductById(Long productId){
        return productRepository.findById(productId);
    }

    public Product createNewProduct(Product product){
        return productRepository.save(product);
    }

    public Product updateProduct(Long productId, Product updatedProduct){
        Optional<Product> existing = findProductById(productId);
        if(existing.isPresent()){
            existing.get().setProductName(updatedProduct.getProductName());
            existing.get().setProductType(updatedProduct.getProductType());
            existing.get().setProductPrice(updatedProduct.getProductPrice());
            existing.get().setQuantity(updatedProduct.getQuantity());
            return productRepository.save(existing.get());
        }
        return null;
    }

    public void deleteProduct(Long productId){
        productRepository.deleteById(productId);
    }
}

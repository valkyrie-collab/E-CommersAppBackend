package com.valkyrie.product.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.valkyrie.product.model.Product;
import com.valkyrie.product.service.ProductService;

@Controller
@RequestMapping("/product")
public class ProductController {
    private ProductService service;
    @Autowired
    private void setService(ProductService service) {
        this.service = service;
    }

    @PostMapping("/save-product")
    public ResponseEntity<String> save(@RequestBody Product pro, List<MultipartFile> imgs) throws IOException {
        return service.save(pro, imgs);
    }
}

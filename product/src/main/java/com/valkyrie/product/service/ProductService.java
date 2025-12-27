package com.valkyrie.product.service;

import java.io.IOException;
import java.util.Base64;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.valkyrie.product.model.Image;
import com.valkyrie.product.model.ImageDTO;
import com.valkyrie.product.model.Product;
import com.valkyrie.product.model.ProductDTO;
import com.valkyrie.product.repository.ProductImageRepository;
import com.valkyrie.product.repository.ProductRepository;

@Service
public class ProductService {
    private ProductRepository pRepo;
    @Autowired
    private void setPrepo(ProductRepository pRepo) {
        this.pRepo = pRepo;
    }

    private ProductImageRepository iRepo;
    @Autowired
    private void setPrepo(ProductImageRepository iRepo) {
        this.iRepo = iRepo;
    }

    private String doDecoding(String word) {
        return new String(Base64.getDecoder().decode(word.getBytes()));
    }

    private ProductDTO getProductDTO(Product product) {
        List<Image> img = iRepo.findImageById(product.getId());
        List<ImageDTO> imgs = new LinkedList<>();

        for (Image i : img) {
            imgs.add(new ImageDTO().setData(i.getData()).setId(i.getId())
                .setName(i.getName()).setType(i.getType()));
        }

        return new ProductDTO().setDes(product.getDes()).setId(product.getId())
            .setImages(imgs).setName(product.getName()).setRating(product.getRating())
            .setSellerId(product.getSellerId()).setType(product.getType());
    }

    public ResponseEntity<String> save(Product pro, List<MultipartFile> imgs) throws IOException {
        String uuid;
        int count;
        List<Image> images = new LinkedList<>();

        count = 0;
        uuid = UUID.randomUUID().toString();
        pro.setId(uuid);

        for (MultipartFile im : imgs) {
            images.add(
                new Image().setName(im.getOriginalFilename()).setProductId(uuid)
                    .setType(im.getContentType()).setData(im.getBytes())
            );
            count++;
        }

        pRepo.save(pro);
        iRepo.saveAll(images);

        return pRepo.existsById(uuid) && iRepo.numberExistsByProductId(uuid) == count?
            ResponseEntity.status(HttpStatus.ACCEPTED).body("The Product has been saved successfully..") : 
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The Product not saved...");
    }

    public ResponseEntity<List<ProductDTO>> findByName(String name) {
        name = doDecoding(name).toLowerCase();
        List<Product> products = pRepo.findProductByName(name);
        List<ProductDTO> productDTOs = new LinkedList<>();
        
        if (products.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        for (Product p : products) {
            productDTOs.add(getProductDTO(p));
        }

        return ResponseEntity.status(HttpStatus.OK).body(productDTOs);
    }

    public ResponseEntity<List<ProductDTO>> findByType(String type) {
        type = doDecoding(type).toLowerCase();
        List<Product> products = pRepo.findProductByType(type);

        List<ProductDTO> productDTOs = new LinkedList<>();

        if (products.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        for (Product p : products) {
            productDTOs.add(getProductDTO(p));
        }

        return ResponseEntity.status(HttpStatus.OK).body(productDTOs);
    }

    public ResponseEntity<String> removeProduct(String productId) {
        productId = doDecoding(productId);

        boolean exist = pRepo.existsById(productId);

        if (exist) {
            pRepo.deleteById(productId);
            int val = iRepo.deleteProductImageById(productId);
            
            exist = pRepo.existsById(productId);

            if (!exist && val > 0) {
                return ResponseEntity.status(HttpStatus.OK).body("Product Deleted successfully...");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Product Not Deleted successfully...");
            }

        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Product with this id is already been deleted...");
        }
    
    }
}

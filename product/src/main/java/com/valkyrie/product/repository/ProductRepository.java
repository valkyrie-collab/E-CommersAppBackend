package com.valkyrie.product.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.valkyrie.product.model.Product;

public interface ProductRepository extends JpaRepository<Product, String> {

    @Query(value = "select * from product where lower(name) = :name", nativeQuery = true)
    public List<Product> findProductByName(@Param("name") String name);

    @Query(value = "select * from product where lower(type) = :type", nativeQuery = true)
    public List<Product> findProductByType(@Param("type") String type);

}

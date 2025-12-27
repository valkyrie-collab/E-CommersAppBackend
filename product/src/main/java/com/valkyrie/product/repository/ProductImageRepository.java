package com.valkyrie.product.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.valkyrie.product.model.Image;

@Repository
public interface ProductImageRepository extends JpaRepository<Image, Integer> {

    @Query(value = "select count(*) from image where product_id = :productId", nativeQuery = true)
    public Integer numberExistsByProductId(@Param("productId") String productId);

    @Query(value = "select * from image where product_id = :productId", nativeQuery = true)
    public List<Image> findImageById(@Param("productId") String productId);

    @Transactional
    @Modifying
    @Query(value = "delete from image where product_id = :id", nativeQuery = true)
    public Integer deleteProductImageById(@Param("id") String id);
}

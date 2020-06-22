package com.example.amazon.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.amazon.model.Product;

@Repository
public interface ProductDao extends CrudRepository<Product, Integer>{

	@Query(value = "select * from product p where p.product_name like %:apple%", nativeQuery =true)
	Optional<List<Product>> findByProductName(@Param("apple")String productName);
	
	@Query(value="select p.price from product p where p.product_id=:productId", nativeQuery = true)
	double findPriceByProductId(@Param("productId")int productId);

	@Query(value="select p.product_name from product p where p.product_id= :productId", nativeQuery = true)
	String findProductNameByProductId(@Param("productId") int productId);
	
	
	Product findByProductId(@Param("productId") int productId);
}

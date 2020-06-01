package io.badri.app.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import io.badri.app.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Page<Product> findByUserId(int userId, Pageable pageable); //find products by userId

    //Optional<Product> findByPidAndUserId(Long id, int userId);// not even used
	    
}



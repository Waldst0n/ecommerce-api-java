package com.waldston.ecommerce.repository;

import com.waldston.ecommerce.dto.ProductResponseDTO;
import com.waldston.ecommerce.model.Product;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {

    Optional<Product> getByName(String name);



}

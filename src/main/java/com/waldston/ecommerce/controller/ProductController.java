package com.waldston.ecommerce.controller;

import com.waldston.ecommerce.dto.product.ProductRequestDTO;
import com.waldston.ecommerce.dto.product.ProductResponseDTO;
import com.waldston.ecommerce.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController()
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {


    private final ProductService productService;



    @GetMapping("/product/{id}")
    public ResponseEntity<ProductResponseDTO> findById(@PathVariable UUID id) {
        ProductResponseDTO productResponseDTO = productService.findById(id);

        return ResponseEntity.ok(productResponseDTO);
    }


    @GetMapping("/{name}")
    public ResponseEntity<ProductResponseDTO> findByName(@PathVariable String name) {
        ProductResponseDTO productResponseDTO = productService.findByName(name);
        return ResponseEntity.ok(productResponseDTO);
    }

    @GetMapping()
    public ResponseEntity<Page<ProductResponseDTO>> getAll(@PageableDefault(size = 10, sort = "name")Pageable pagination) {
        return ResponseEntity.ok(productService.getAllPaginated(pagination));
    }


    @GetMapping("/categories")
    public List<ProductResponseDTO> findByCategory (@PageableDefault(sort = "name") Pageable pageable, @RequestParam(required = true) String category) {
        return productService.findByCategory_Name(category);
    }



    @PostMapping()
    public ResponseEntity<ProductResponseDTO> create(@RequestBody @Valid ProductRequestDTO productRequestDTO) {
            var response =  productService.create(productRequestDTO);
            return  ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

}

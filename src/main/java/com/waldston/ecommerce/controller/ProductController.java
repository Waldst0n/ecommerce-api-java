package com.waldston.ecommerce.controller;

import com.waldston.ecommerce.dto.ProductRequestDTO;
import com.waldston.ecommerce.dto.ProductResponseDTO;
import com.waldston.ecommerce.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {


    private final ProductService productService;


    @GetMapping("/{name}")
    public ResponseEntity<ProductResponseDTO> getByName(@PathVariable String name) {
        ProductResponseDTO productResponseDTO = productService.getByName(name);
        return ResponseEntity.ok(productResponseDTO);
    }

    @GetMapping()
    public ResponseEntity<Page<ProductResponseDTO>> getAll(@PageableDefault(size = 10, sort = "name")Pageable pagination) {
        return ResponseEntity.ok(productService.getAllPaginated(pagination));
    }



    @PostMapping()
    public ResponseEntity<ProductResponseDTO> create(@RequestBody @Valid ProductRequestDTO productRequestDTO) {
            var response =  productService.create(productRequestDTO);
            return  ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

}

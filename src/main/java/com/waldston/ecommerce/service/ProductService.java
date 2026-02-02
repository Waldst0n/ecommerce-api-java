package com.waldston.ecommerce.service;

import com.waldston.ecommerce.dto.ProductRequestDTO;
import com.waldston.ecommerce.dto.ProductResponseDTO;
import com.waldston.ecommerce.model.Category;
import com.waldston.ecommerce.model.Product;
import com.waldston.ecommerce.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    private final ProductRepository categoryRepository;



    public ProductResponseDTO getByName(String name) {
        Product product = productRepository.getByName(name).orElseThrow(() -> new RuntimeException("Produto não encontrado."));
        return new ProductResponseDTO(product.getId(), product.getName(), product.getPrice());
    }


    public Page<ProductResponseDTO> getAllPaginated(Pageable pagination) {
        return  productRepository.findAll(pagination).map(p -> new ProductResponseDTO(p.getId(), p.getName(), p.getPrice()));
    }

    public ProductResponseDTO create (ProductRequestDTO data) {
        Category category = categoryRepository.findById(data.category_id())
                .orElseThrow(() -> new RuntimeException("Categoria Não Encontrada.")).getCategory();


        Product newProduct = new Product();
        newProduct.setName(data.name());
        newProduct.setPrice(data.price());
        newProduct.setCategory(category);

        Product saved = productRepository.save(newProduct);

        return new ProductResponseDTO(saved.getId(), saved.getName(), saved.getPrice());
    }




}

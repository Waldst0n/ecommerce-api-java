package com.waldston.ecommerce.service;

import com.waldston.ecommerce.dto.product.ProductRequestDTO;
import com.waldston.ecommerce.dto.product.ProductResponseDTO;
import com.waldston.ecommerce.exception.category.CategoryNotFoundException;
import com.waldston.ecommerce.exception.product.ProductNotFoundException;
import com.waldston.ecommerce.model.Category;
import com.waldston.ecommerce.model.Product;
import com.waldston.ecommerce.repository.CategoryRepository;
import com.waldston.ecommerce.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    private final CategoryRepository categoryRepository;



    public ProductResponseDTO findById(UUID id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Produto não encontrado"));
        return new ProductResponseDTO(product.getId(), product.getName(), product.getPrice(), product.getCategory());
    }

    public ProductResponseDTO findByName(String name) {
        Product product = productRepository.findByName(name).orElseThrow(() -> new ProductNotFoundException("Produto não encontrado."));
        return new ProductResponseDTO(product.getId(), product.getName(), product.getPrice(), product.getCategory());
    }

    public List<ProductResponseDTO> findByCategory_Name(String category) {
        List<Product> products = productRepository.findByCategory_Name(category);
        if (products.isEmpty()) {
            throw  new ProductNotFoundException("Produtos não encontrados para essa categoria");
        }
        return products.stream().map(p -> new ProductResponseDTO(p.getId(), p.getName(), p.getPrice(), p.getCategory())).toList();
    }


    public Page<ProductResponseDTO> getAllPaginated(Pageable pagination) {
            return  productRepository.findAll(pagination).map(p -> new ProductResponseDTO(p.getId(), p.getName(), p.getPrice(), p.getCategory()));
    }

    public ProductResponseDTO create (ProductRequestDTO data) {
        Category category = categoryRepository.findById(data.category_id())
                .orElseThrow(() -> new CategoryNotFoundException("Categoria Não Encontrada."));


        Product newProduct = new Product();
        newProduct.setName(data.name());
        newProduct.setPrice(data.price());
        newProduct.setCategory(category);

        Product saved = productRepository.save(newProduct);

        return new ProductResponseDTO(saved.getId(), saved.getName(), saved.getPrice(), saved.getCategory());
    }




}

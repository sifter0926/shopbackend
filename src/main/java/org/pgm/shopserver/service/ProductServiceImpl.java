package org.pgm.shopserver.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.pgm.shopserver.dto.ProductDTO;
import org.pgm.shopserver.model.Product;
import org.pgm.shopserver.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public ProductDTO saveProduct(ProductDTO productDTO) {
        Product product=dtoToEntify(productDTO);
        product.setCreateTime(LocalDateTime.now());
        Product savedProduct=productRepository.save(product);
        return entityToDto(savedProduct);
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public List<ProductDTO> findAllProducts() {
        List<Product> products=productRepository.findAll();
        List<ProductDTO> productDTOs=products.stream()
                .map(product -> entityToDto(product))
                .collect(Collectors.toList());
        return productDTOs;
    }
}

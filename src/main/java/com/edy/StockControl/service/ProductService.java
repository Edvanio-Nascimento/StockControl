package com.edy.StockControl.service;

import com.edy.StockControl.dto.product.*;
import com.edy.StockControl.entity.Product;
import com.edy.StockControl.enums.ProductViewEnum;
import com.edy.StockControl.interfaces.ProductViewInterface;
import com.edy.StockControl.repository.ProductRepository;
import com.edy.stock_control.exception.DuplicateResourceException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class ProductService {

    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public SummaryProduct created(CreateProduct create) {
        try {
            Product product = create.toEntity();
            Product saved = repository.save(product);
            return SummaryProduct.fromEntity(saved);
        } catch (DataIntegrityViolationException ex) {
            throw new DuplicateResourceException("SKU já existe");
        }
    }

    @Transactional(readOnly = true)
    public List<SummaryProduct> getAll() {
        return repository.findAll()
                .stream()
                .map(SummaryProduct::fromEntity)
                .toList();
    }

    @Transactional(readOnly = true)
    public ProductViewInterface getSku(String sku, ProductViewEnum view) {
        Product product = repository.findBySku(sku)
                .orElseThrow(() -> new EntityNotFoundException("Produto não encontrado"));
        return buildView(product, view);
    }

    private ProductViewInterface buildView(Product product, ProductViewEnum view) {
        return ProductViewEnum.COMPACT.equals(view)
                ? DetailsProduct.fromEntity(product)
                : AuditingProduct.fromEntity(product);
    }

    @Transactional
    public void delete(UUID id) {
        Product product = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Produto não encontrado"));
        repository.delete(product);
    }

    @Transactional
    public SummaryProduct update(UUID id, UpdateProduct update) {
        Product product = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Produto não encontrado"));

        if (update.price() != null) {
            product.setPrice(update.price());
        }

        Product saved = repository.save(product);
        return SummaryProduct.fromEntity(saved);
    }

    @Transactional
    public void addStock(UUID id, int amount) {
        Product product = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Produto não encontrado"));

        product.increaseQuantity(amount);
    }

    @Transactional
    public void removeStock(UUID id, int amount) {
        Product product = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Produto não encontrado"));

        product.decreaseQuantity(amount);
    }
}

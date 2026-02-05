package com.edy.StockControl.controller;

import com.edy.StockControl.dto.product.CreateProduct;
import com.edy.StockControl.dto.product.MovementProduct;
import com.edy.StockControl.dto.product.SummaryProduct;
import com.edy.StockControl.dto.product.UpdateProduct;
import com.edy.StockControl.entity.Product;
import com.edy.StockControl.enums.ProductViewEnum;
import com.edy.StockControl.interfaces.ProductViewInterface;
import com.edy.StockControl.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("v1/api/products")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<SummaryProduct> save(@RequestBody @Valid CreateProduct create) {
        SummaryProduct summary = service.created(create);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(summary.sku())
                .toUri();

        return ResponseEntity.created(location).body(summary);
    }

    @PostMapping("/{sku}/stock/in")
    public ResponseEntity<Void> addStock(@PathVariable String sku,
                                         @RequestBody @Valid MovementProduct movement) {
        service.addStock(sku, movement.quantity());
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{sku}/stock/out")
    public ResponseEntity<Void> removeStock(@PathVariable String sku,
                                            @RequestBody @Valid MovementProduct movement) {
        service.removeStock(sku, movement.quantity());
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<SummaryProduct>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{sku}")
    public ResponseEntity<ProductViewInterface> getBySku(@PathVariable String sku,
                                                         @RequestParam(defaultValue = "DETAILS") String view) {
        return ResponseEntity.ok(service.getSku(sku, ProductViewEnum.from(view)));
    }

    @DeleteMapping("/{sku}")
    public ResponseEntity<Void> delete(@PathVariable String sku) {
        service.delete(sku);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{sku}")
    public ResponseEntity<SummaryProduct> update(@PathVariable String sku,
                                                 @RequestBody @Valid UpdateProduct update) {
        SummaryProduct summary = service.update(sku, update);
        return ResponseEntity.ok(summary);
    }
}

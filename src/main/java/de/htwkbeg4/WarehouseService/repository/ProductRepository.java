package de.htwkbeg4.WarehouseService.repository;

import de.htwkbeg4.WarehouseService.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}

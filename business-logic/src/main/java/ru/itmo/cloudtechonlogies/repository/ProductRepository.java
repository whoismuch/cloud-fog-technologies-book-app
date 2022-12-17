package ru.itmo.cloudtechonlogies.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.itmo.highloadsystems.model.Product;

import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {

}

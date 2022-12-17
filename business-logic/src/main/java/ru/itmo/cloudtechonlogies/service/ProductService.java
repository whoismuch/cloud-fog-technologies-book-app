package ru.itmo.cloudtechonlogies.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itmo.highloadsystems.model.Order;
import ru.itmo.highloadsystems.model.Product;
import ru.itmo.highloadsystems.repository.ProductRepository;

import javax.transaction.Transactional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    private final AntistressToyProductionService antistressToyProductionService;

    @Transactional
    public void addProduct(Product product, Order order) {
        product.setId(UUID.randomUUID());
        product.setOrder(order);
        Product productSaved = productRepository.save(product);
        antistressToyProductionService.takeToys(product.getAntistressToyType(), productSaved.getCount());
    }

}

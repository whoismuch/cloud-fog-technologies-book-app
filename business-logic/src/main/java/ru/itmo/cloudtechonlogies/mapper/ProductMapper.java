package ru.itmo.cloudtechonlogies.mapper;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.itmo.highloadsystems.dto.ProductDTO;
import ru.itmo.highloadsystems.model.Product;

@RequiredArgsConstructor
@Component
public class ProductMapper {

    private final ModelMapper modelMapper;

    public Product mapDtoToEntity(ProductDTO productDTO) {
        Product product = modelMapper.map(productDTO, Product.class);
        return modelMapper.map(productDTO, Product.class);
    }
    public ProductDTO mapEntityToDto(Product product) {
        return modelMapper.map(product, ProductDTO.class);
    }



}

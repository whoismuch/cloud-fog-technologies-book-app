package ru.itmo.cloudtechonlogies.mapper;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import ru.itmo.cloudtechonlogies.dto.CategoryBookDTO;
import ru.itmo.cloudtechonlogies.model.CategoryBook;

import java.time.LocalDateTime;
import java.util.function.Function;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class CategoryBookMapper {

    public Page<CategoryBookDTO> mapEntityToDtoPage(Page<CategoryBook> categoryBook) {
        return categoryBook.map(this::mapEntityToDto);
    }

    public CategoryBookDTO mapEntityToDto(CategoryBook categoryBook) {
        return CategoryBookDTO.builder()
                .id(categoryBook.getBook().getId())
                .name(categoryBook.getBook().getName())
                .author(categoryBook.getBook().getAuthor())
                .textPath(categoryBook.getBook().getTextPath())
                .audioPath(categoryBook.getBook().getAudioPath())
                .categoryName(categoryBook.getCategory().getName())
                .build();
    }
}

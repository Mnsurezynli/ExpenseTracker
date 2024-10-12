package org.example.Service;

import org.example.Dto.CategoryDto;

import java.util.List;

public interface ICategoryService {
    CategoryDto create(CategoryDto categoryDto);
    void deleteById(Long id);
    CategoryDto getById(Long id);
    List<CategoryDto> getAll();
}

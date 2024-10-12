package org.example.Service.Impl;

import org.example.Dto.CategoryDto;
import org.example.Dto.ExpenseDto;
import org.example.Exception.ResourceAlreadyExistsException;
import org.example.Exception.ResourceNotFoundException;
import org.example.Model.Category;
import org.example.Model.Expense;
import org.example.Repository.CategoryRepository;
import org.example.Service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements ICategoryService {

    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public CategoryDto create(CategoryDto categoryDto) {
        Optional<Category> category = categoryRepository.findById(categoryDto.getId());
        if (category.isPresent()) {
            throw new ResourceAlreadyExistsException("this category is already exists");
        }
        Category category1 = ConvertToEntity(categoryDto);
        category1 = categoryRepository.saveAndFlush(category1);
        CategoryDto categoryDto1 = ConvertToDto(category1);
        return categoryDto1;

    }

    @Override
    public void deleteById(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("category with ID " + id + " not found."));
        categoryRepository.deleteById(id);
    }


    @Override
    public CategoryDto getById(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("category with ID " + id + " not found."));
       return ConvertToDto(category);
    }

    @Override
    public List<CategoryDto> getAll() {
        List<Category> category = categoryRepository.findAll();
        return category.stream().map(this::ConvertToDto).collect(Collectors.toList());
    }

        public CategoryDto ConvertToDto(Category category) {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(category.getId());
        categoryDto.setName(category.getName());
        categoryDto.setExpense(category.getExpense());
        return categoryDto;
    }

    public Category ConvertToEntity(CategoryDto categoryDto) {
        Category category = new Category();
        category.setId(categoryDto.getId());
        category.setName(categoryDto.getName());
        category.setExpense(categoryDto.getExpense());
        return category;
    }
}

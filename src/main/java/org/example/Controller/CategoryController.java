package org.example.Controller;

import org.example.Dto.CategoryDto;
import org.example.Dto.ExpenseDto;
import org.example.Service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Action;
import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    private ICategoryService iCategoryService;

    @Autowired
    public CategoryController(ICategoryService iCategoryService) {
        this.iCategoryService = iCategoryService;
    }


    @PostMapping("/create")
    public ResponseEntity<CategoryDto> create(@RequestBody CategoryDto categoryDto) {
        CategoryDto categoryDto1 = iCategoryService.create(categoryDto);
        return new ResponseEntity<>(categoryDto1, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        iCategoryService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDto> getById(@PathVariable Long id) {
        CategoryDto categoryDto = iCategoryService.getById(id);
        return ResponseEntity.ok(categoryDto);
    }

    @GetMapping("/all")
    public ResponseEntity<List<CategoryDto>> getAll() {
        List<CategoryDto> categoryDtos = iCategoryService.getAll();
        return ResponseEntity.ok(categoryDtos);
    }

}

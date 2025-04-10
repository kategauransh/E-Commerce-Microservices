package com.ecom.ecommerce.service;


import com.ecom.ecommerce.model.Category;
import com.ecom.ecommerce.project.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
//import com.ecom.ecommerce.CategoryRepository.CategoryRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    private List<Category> CategoryRepository=new ArrayList<>();
 private Long nextId=1L;
    @Autowired
    private CategoryRepository categoryRepository;
    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public void createCategory(Category category) {
    category.setCategoryId(nextId++);
        categoryRepository.save(category);

    }

    @Override
    public String deleteCategory(Long categoryId) {
        Category category=categoryRepository.findById(categoryId).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Category not found"));
//        List<Category> categories=categoryRepository.findAll();
//        List<Object>   categories=CategoryRepository.findAll;
//        Category category=categories.stream().filter(c -> c.getCategoryId().equals(categoryId)).findFirst().orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Category not found"));
//        if (category==null) {
//            return "Category not found";
//        }
       categoryRepository.delete(category);
        return "Category with categaoryID "+categoryId+" deleted succesfully";
    }

    @Override
    public Category updateCategory(Category category, Long categoryId) {
//        List<Category> categoryRepository=new ArrayList<>();

        Optional<Category>savedCategoryOptional= categoryRepository.findById(categoryId);

        Category savedCategory=savedCategoryOptional.orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Category not found"));
        category.setCategoryId(categoryId);

//    if (savedCategoryOptional.isPresent()){
//        Category existingCategory=savedCategoryOptional.get();
//        existingCategory.setCategoryName(category.getCategoryName());
//        Category savedCategory1=categoryRepository.save(existingCategory);
//
//        return savedCategory1;
//    }
//    else
//        throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Category not found");
//
//    }
category.setCategoryId(categoryId);
    savedCategory=categoryRepository.save(category);
    return savedCategory;

    }
}

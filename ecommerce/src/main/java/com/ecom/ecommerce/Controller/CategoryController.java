package com.ecom.ecommerce.Controller;

import com.ecom.ecommerce.model.Category;
import com.ecom.ecommerce.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RestController
@RequestMapping("/api")
public class CategoryController{

    private CategoryService categoryService;
    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
//    private List<Category> categories=new ArrayList<>();


//    @GetMapping("/api/public/categories")
    @RequestMapping(value="/public/categories",method=RequestMethod.GET)
    public ResponseEntity<List<Category>> getAllCategories() {
        List<Category> categories = categoryService.getAllCategories();
        return  new ResponseEntity<>(categories,HttpStatus.OK);
    }
//    @PostMapping("/api/public/categories")
@RequestMapping(value="/public/categories",method=RequestMethod.POST)

public ResponseEntity<String> CreateCategory(@RequestBody Category category) {
      categoryService.createCategory(category);
        return new ResponseEntity<>("Category created",HttpStatus.CREATED);
    }
//    @DeleteMapping("/api/admin/categories/{categoryId}")
    @RequestMapping(value="/admin/categories/{categoryId}",method=RequestMethod.DELETE)

    public ResponseEntity<String> deleteCategory(@PathVariable Long categoryId) {
        try{
        String status = categoryService.deleteCategory(categoryId);
//        return new ResponseEntity<>(status, HttpStatus.OK);}
//        return ResponseEntity.ok(status);}
        return ResponseEntity.status( HttpStatus.OK).body(status);}
        catch (ResponseStatusException e) {
            return new ResponseEntity<>(e.getReason(),e.getStatusCode());
        }
    }
//    @PutMapping("/api/public/categories/{categoryId}")
    @RequestMapping(value="/public/categories/{categoryId}",method=RequestMethod.PUT)

    public ResponseEntity<String> updateCategory(@RequestBody Category category,@PathVariable Long categoryId) {
    try {
        Category savedcategory = categoryService.updateCategory(category,categoryId);
        return new ResponseEntity<>("Category with category id"+categoryId, HttpStatus.OK);

}
    catch (ResponseStatusException e) {
        return new ResponseEntity<>(e.getReason(),e.getStatusCode());
    }
    }}





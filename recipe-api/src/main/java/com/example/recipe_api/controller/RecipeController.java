package com.example.recipe_api.controller;

import com.example.recipe_api.model.Recipe;
import com.example.recipe_api.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/recipes")
public class RecipeController {

    @Autowired
    private RecipeService service;

    @PostMapping
    public Recipe addRecipe(@RequestBody Recipe recipe) {
        return service.saveRecipe(recipe);
    }

    @GetMapping
    public Map<String, Object> getAllRecipes() {

        List<Recipe> recipes = service.getAllRecipes();

        Map<String, Object> response = new HashMap<>();
        response.put("data", recipes);

        return response;
    }


    @GetMapping("/top")
    public Map<String, Object> getTopRecipes(
            @RequestParam(defaultValue = "5") int limit) {

        List<Recipe> recipes = service.getTopRecipes(limit);

        Map<String, Object> response = new HashMap<>();
        response.put("data", recipes);

        return response;
    }
}
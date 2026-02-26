package com.example.recipe_api.service;

import com.example.recipe_api.exception.CustomException;
import com.example.recipe_api.model.Recipe;
import com.example.recipe_api.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecipeService {

    @Autowired
    private RecipeRepository repository;

    public Recipe saveRecipe(Recipe recipe) {

        if (recipe.getTitle() == null ||
                recipe.getCuisine() == null ||
                recipe.getPrepTime() == null ||
                recipe.getCookTime() == null) {

            throw new CustomException("Required fields are missing!");
        }

        recipe.setTotalTime(recipe.getPrepTime() + recipe.getCookTime());

        return repository.save(recipe);
    }
    public List<Recipe> getAllRecipes() {
        return repository.findAll();
    }

    public List<Recipe> getTopRecipes(int limit) {
        return repository.findAllByOrderByRatingDesc(PageRequest.of(0, limit));
    }
}
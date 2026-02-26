package com.example.recipe_api.util;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.recipe_api.model.Recipe;
import com.example.recipe_api.repository.RecipeRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.Map;

@Component
public class JsonDataLoader {

    @Autowired
    private RecipeRepository repository;

    @PostConstruct
    public void loadData() {

        try {
            ObjectMapper mapper = new ObjectMapper();

            InputStream inputStream =
                    getClass().getResourceAsStream("/US_recipes_null.json");

            Map<String, JsonNode> root =
                    mapper.readValue(inputStream,
                            new TypeReference<Map<String, JsonNode>>() {});

            for (Map.Entry<String, JsonNode> entry : root.entrySet()) {

                JsonNode node = entry.getValue();

                Recipe recipe = new Recipe();
                recipe.setTitle(node.get("title").asText());
                recipe.setCuisine(node.get("cuisine").asText());
                recipe.setRating(node.get("rating").isNull() ? null :
                                (float) node.get("rating").asDouble());
                recipe.setPrepTime(node.get("prep_time").asInt());
                recipe.setCookTime(node.get("cook_time").asInt());
                recipe.setTotalTime(node.get("prep_time").asInt()
                                + node.get("cook_time").asInt());
                recipe.setDescription(node.get("description").asText());
                recipe.setNutrients(node.get("nutrients").toString());
                recipe.setServes(node.get("serves").asText());

                repository.save(recipe);
            }

            System.out.println("JSON Data Loaded Successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
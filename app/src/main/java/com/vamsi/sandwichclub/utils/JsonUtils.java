package com.vamsi.sandwichclub.utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import com.vamsi.sandwichclub.model.SandwichData;


public class JsonUtils {

    public static SandwichData parseSandwichJson(String json) {

        if (json == null || json.isEmpty()) {
            return null;
        }

        try {
            SandwichData sandwich = new SandwichData();

            JSONObject sandwichJsonObject = new JSONObject(json);
            JSONObject nameJsonObject = sandwichJsonObject.getJSONObject("name");
            sandwich.setMainName(nameJsonObject.getString("mainName"));
            JSONArray alsoKnownAsJsonArray = nameJsonObject.getJSONArray("alsoKnownAs");
            if (alsoKnownAsJsonArray != null) {
                List<String> alsoKnownAsList = new ArrayList<>();
                for (int i = 0; i < alsoKnownAsJsonArray.length(); i++) {
                    alsoKnownAsList.add(alsoKnownAsJsonArray.getString(i));
                }
                sandwich.setAlsoKnownAs(alsoKnownAsList);
            }
            sandwich.setPlaceOfOrigin(sandwichJsonObject.getString("placeOfOrigin"));
            sandwich.setDescription(sandwichJsonObject.getString("description"));
            sandwich.setImage(sandwichJsonObject.getString("image"));
            JSONArray ingredientsJsonArray = sandwichJsonObject.getJSONArray("ingredients");
            if (ingredientsJsonArray != null) {
                List<String> ingredientsList = new ArrayList<>();
                for (int i = 0; i < ingredientsJsonArray.length(); i++) {
                    ingredientsList.add(ingredientsJsonArray.getString(i));
                }
                sandwich.setIngredients(ingredientsList);
            }

            return sandwich;
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }
}
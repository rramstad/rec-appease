package recappease.org.rec_appease.Recipes;

import android.graphics.Matrix;
import android.media.Image;

import java.util.ArrayList;
import java.util.Iterator;

import recappease.org.rec_appease.Util.FoodItem;

/**
 * Created by Ramstadr6 on 3/30/2018.
 */

public class Recipe {
    public String title;
    Matrix image;
    public ArrayList<FoodItem> ingredients;
    public boolean privacy;
    public int time;
    public int serving;
    public int cost;
    public String instructions;
    public int likes;
    public String creator;
    public Recipe(String title, Matrix image, ArrayList<FoodItem> ingredients, boolean privacy, int time, int servings, int cost, String instructions, int likes, String creator) {
        this.title = title;
        this.image = image;
        this.ingredients = ingredients;
        this.privacy = privacy;
        this.time = time;
        this.serving = servings;
        this.cost = cost;
        this.instructions = instructions;
        this.likes = likes;
        this.creator = creator;
    }

    public static ArrayList<FoodItem> parseIngredients(String s) {
        ArrayList<FoodItem> ingredients = new ArrayList<FoodItem>(30);
        String ingredientToken[] = s.split(";;;");
        String token[];
        for (int i = 0; i < ingredientToken.length; i++) {
            token = ingredientToken[i].split(":::");
            for (int j = 0; j < token.length; j++) {
                ingredients.add(new FoodItem(token[0], Integer.parseInt(token[1]), token[2]));
            }
        }
        return ingredients;
    }

    public static String ingredientsString(ArrayList<FoodItem> ingredients) {
        String message = "";
        Iterator<FoodItem> iterator = ingredients.iterator();
        while (iterator.hasNext()) {
            FoodItem next = iterator.next();
            message = message + next.name + ":::" + Integer.toString(next.quantity) + ":::" + next.unit;
            if (iterator.hasNext()) {
                message = message + ";;;";
            }
        }
        return message;
    }
/*
    public static ArrayList<String> parseTags(String s) {
        ArrayList<String> tags = new ArrayList<String>(30);
        String[] tagToken = s.split(";;;");
        for (int i = 0; i < tagToken.length; i++) {
            tags.add(tagToken[i]);
        }
        return tags;
    }

    public static String tagsString(ArrayList<String> tags) {
        String message = "";
        Iterator<String> iterator = tags.iterator();
        while (iterator.hasNext()) {
            String next = iterator.next();
            message = message + next;
            if (iterator.hasNext()) {
                message = message + ";;;";
            }
        }
        return message;
    }
  */
}

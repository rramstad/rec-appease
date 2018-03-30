package recappease.org.rec_appease.Recipes;

import android.graphics.Matrix;
import android.media.Image;

import java.util.ArrayList;

import recappease.org.rec_appease.Util.FoodItem;

/**
 * Created by Ramstadr6 on 3/30/2018.
 */

public class Recipe {
    String title;
    Matrix image;
    ArrayList<FoodItem> ingredients;
    ArrayList<String> tags;
    boolean privacy;
    int time;
    int serving;
    int cost;
    String instructions;
    public Recipe(String title, Matrix image, ArrayList<FoodItem> ingredients, ArrayList<String> tags, boolean privacy, int time, int servings, int cost, String instructions) {
        this.title = title;
        this.image = image;
        this.ingredients = ingredients;
        this.tags = tags;
        this.privacy = privacy;
        this.time = time;
        this.serving = servings;
        this.cost = cost;
        this.instructions = instructions;
    }
}

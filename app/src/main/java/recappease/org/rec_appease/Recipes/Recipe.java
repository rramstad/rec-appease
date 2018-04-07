package recappease.org.rec_appease.Recipes;

import android.graphics.Matrix;
import android.media.Image;

import java.util.ArrayList;

import recappease.org.rec_appease.Util.FoodItem;

/**
 * Created by Ramstadr6 on 3/30/2018.
 */

public class Recipe {
    public String title;
    Matrix image;
    public ArrayList<FoodItem> ingredients;
    public ArrayList<String> tags;
    public boolean privacy;
    public int time;
    public int serving;
    public int cost;
    public String instructions;
    public int likes;
    public Recipe(String title, Matrix image, ArrayList<FoodItem> ingredients, ArrayList<String> tags, boolean privacy, int time, int servings, int cost, String instructions, int likes) {
        this.title = title;
        this.image = image;
        this.ingredients = ingredients;
        this.tags = tags;
        this.privacy = privacy;
        this.time = time;
        this.serving = servings;
        this.cost = cost;
        this.instructions = instructions;
        this.likes = likes;
    }
}

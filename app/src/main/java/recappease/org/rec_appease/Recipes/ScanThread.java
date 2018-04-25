package recappease.org.rec_appease.Recipes;

import android.util.Log;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ScanRequest;
import com.amazonaws.services.dynamodbv2.model.ScanResult;

import java.util.ArrayList;
import java.util.Map;

import recappease.org.rec_appease.models.nosql.RecipesDO;

/**
 * Created by Ramstadr6 on 4/12/2018.
 */

public class ScanThread implements Runnable {
    private AmazonDynamoDBClient dynamoDBClient;
    private static ArrayList<Recipe> recipesArrayList;

    public ScanThread(AmazonDynamoDBClient dynamoDBClient) {
        this.dynamoDBClient = dynamoDBClient;
        this.recipesArrayList = new ArrayList<Recipe>(30);
    }

    @Override
    public void run() {
        ScanRequest scanRequest = new ScanRequest().withTableName("recappease-mobilehub-1835379734-recipes");
        ScanResult scanResult = dynamoDBClient.scan(scanRequest);
        recipesArrayList = new ArrayList<Recipe>(30);
        for(Map<String, AttributeValue> item : scanResult.getItems()) {
            String name = item.get("name").toString();
            name = name.substring(3,name.length() - 2);
            Recipe recipe = new Recipe(name,
                    null,
                    Recipe.parseIngredients(item.get("ingredients").toString()),
                    Boolean.getBoolean(item.get("public").toString()),
                    Integer.parseInt(item.get("prep_time").toString().replaceAll("[^\\d.]", "")),
                    Integer.parseInt(item.get("serving_size").toString().replaceAll("[^\\d.]", "")),
                    Integer.parseInt(item.get("approx_cost").toString().replaceAll("[^\\d.]", "")),
                    item.get("instructions").toString(),
                    Integer.parseInt(item.get("likes").toString().replaceAll("[^\\d.]", "")));

            recipesArrayList.add(recipe);
        }
    }

    public static ArrayList<Recipe> getRecipes() {
        return recipesArrayList;
    }
}

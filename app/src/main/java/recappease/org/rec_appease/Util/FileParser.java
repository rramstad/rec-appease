package recappease.org.rec_appease.Util;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;

import recappease.org.rec_appease.Grocery.GroceryFragment;
import recappease.org.rec_appease.Inventory.InventoryFragment;
import recappease.org.rec_appease.Recipes.Recipe;

/**
 * Created by Ramstadr6 on 2/18/2018.
 */

public class FileParser {

    private String groceryFileName = "grocery.txt";
    private String inventoryFileName = "inventory.txt";
    private String recipeFileName = "recipe.txt";
    public Context context;

    public FileParser(Context context) {
        this.context = context;
    }

    public ArrayList<FoodItem> readGroceryFile() {

        try {
            String message;
            ArrayList<FoodItem> groceryList = new ArrayList<>(30);
            FileInputStream fileInputStream = this.context.openFileInput(groceryFileName);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            //StringBuffer stringBuffer = new StringBuffer();
            while ((message = bufferedReader.readLine()) != null) {
                //stringBuffer.append(message);
                String[] tokens = message.split(",");
                if (tokens.length == 3) {
                    groceryList.add(new FoodItem(tokens[0], Integer.parseInt(tokens[1]), tokens[2]));
                }
            }
            return groceryList;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<FoodItem>(30);
    }

    public void writeGroceryFile(ArrayList<FoodItem> groceryList) {
        try {
            Iterator<FoodItem> iterator = groceryList.iterator();
            String message = "";
            while (iterator.hasNext()) {
                FoodItem next = iterator.next();
                message = message + next.name + "," + next.quantity + "," + next.unit + "\n";
            }
            FileOutputStream fileOutputStream = this.context.openFileOutput(groceryFileName, context.MODE_PRIVATE);
            fileOutputStream.write(message.getBytes());
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<FoodItem> readInventoryFile() {
        try {
            String message;
            ArrayList<FoodItem> inventoryList = new ArrayList<>(30);
            FileInputStream fileInputStream = this.context.openFileInput(inventoryFileName);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            //StringBuffer stringBuffer = new StringBuffer();
            while ((message = bufferedReader.readLine()) != null) {
                //stringBuffer.append(message + "\n");
                String[] tokens = message.split(",");
                if (tokens.length == 3) {
                    inventoryList.add(new FoodItem(tokens[0], Integer.parseInt(tokens[1]), tokens[2]));
                }
                //Log.d("Token", tokens[0] + " " + tokens[1] + " " + tokens[2]);
            }
            return inventoryList;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<FoodItem>(30);
    }

    public void writeInventoryFile(ArrayList<FoodItem> inventoryList) {
        try {
            Iterator<FoodItem> iterator = inventoryList.iterator();
            String message = "";
            while (iterator.hasNext()) {
                FoodItem next = iterator.next();
                message = message + next.name + "," + next.quantity + "," + next.unit + "\n";
            }
            FileOutputStream fileOutputStream = this.context.openFileOutput(inventoryFileName, context.MODE_PRIVATE);
            fileOutputStream.write(message.getBytes());
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void checkOutGrocery(ArrayList<FoodItem> groceryList, int position) {
        ArrayList<FoodItem> inventoryList = readInventoryFile();

        FoodItem checkout = groceryList.remove(position);
        inventoryList.add(checkout);

        writeInventoryFile(inventoryList);
        writeGroceryFile(groceryList);

        GroceryFragment.updateList();
        InventoryFragment.updateList();
    }

    public ArrayList<Recipe> readRecipeFile() {
        try {
            String message;
            ArrayList<Recipe> recipeList = new ArrayList<>(30);
            FileInputStream fileInputStream = this.context.openFileInput(recipeFileName);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            //StringBuffer stringBuffer = new StringBuffer();
            ArrayList<FoodItem> ingredientList = new ArrayList<FoodItem>(30);
            while ((message = bufferedReader.readLine()) != null) {
                //stringBuffer.append(message + "\n");
                String[] tokens = message.split(";;;");
                String[] ingredienttokens = tokens[2].split(":::");

                for(int i = 0; i < ingredienttokens.length; i++) {
                    String[] foodtokens = ingredienttokens[i].split(",");
                    if (foodtokens[i].length() == 3) {
                        ingredientList.add(new FoodItem(foodtokens[0], Integer.parseInt(foodtokens[1]), foodtokens[2]));
                    }
                }

                if (tokens.length == 10) {
                    recipeList.add(new Recipe(tokens[0], null, ingredientList, Boolean.parseBoolean(tokens[3]), Integer.parseInt(tokens[4]), Integer.parseInt(tokens[5]), Integer.parseInt(tokens[6]), tokens[7], Integer.parseInt(tokens[8])));
                }
                //Log.d("Token", tokens[0] + " " + tokens[1] + " " + tokens[2]);
            }
            return recipeList;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<Recipe>(30);
    }

    public void writeRecipeFile(ArrayList<Recipe> recipeList) {
        try {
            Iterator<Recipe> iterator = recipeList.iterator();
            String message = "";
            while (iterator.hasNext()) {
                Recipe next = iterator.next();

                ArrayList<FoodItem> ingredients = next.ingredients;
                Iterator<FoodItem> iterator2 = ingredients.iterator();
                String message2 = "";
                while (iterator2.hasNext()) {
                    FoodItem next2 = iterator2.next();
                    message2 = message2 + next2.name + "," + next2.quantity + "," + next2.unit + ":::";
                }

                message = message + next.title + ";;;" + "image" + ";;;" + message2 + ";;;" + "tags" + ";;;"
                        + Boolean.toString(next.privacy) + ";;;" + Integer.toString(next.time) + ";;;"
                        + Integer.toString(next.serving) + ";;;" + Integer.toString(next.cost) + ";;;"
                        + next.instructions + ";;;" + Integer.toString(next.likes) + "\n";
            }
            FileOutputStream fileOutputStream = this.context.openFileOutput(recipeFileName, context.MODE_PRIVATE);
            fileOutputStream.write(message.getBytes());
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getGroceryFileName() {
        return groceryFileName;
    }

    public String getInventoryFileName() {
        return inventoryFileName;
    }

}

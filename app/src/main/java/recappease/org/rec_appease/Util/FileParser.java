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

/**
 * Created by Ramstadr6 on 2/18/2018.
 */

public class FileParser {

    private String groceryFileName = "grocery.txt";
    private String inventoryFileName = "inventory.txt";
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

    public void checkOutGrocery() {
        ArrayList<FoodItem> groceryList = readGroceryFile();
        ArrayList<FoodItem> inventoryList = readInventoryFile();
        Iterator<FoodItem> iterator = groceryList.iterator();
        String message = "";
        while (iterator.hasNext()) {
            FoodItem next = iterator.next();
            inventoryList.add(next);
        }
        groceryList.clear();
        writeInventoryFile(inventoryList);
        writeGroceryFile(groceryList);
    }

    public String getGroceryFileName() {
        return groceryFileName;
    }

    public String getInventoryFileName() {
        return inventoryFileName;
    }

}

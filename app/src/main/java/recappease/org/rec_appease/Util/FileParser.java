package recappease.org.rec_appease.Util;

import android.content.Context;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

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

    public String readGroceryFile() {
        try {
            String message;
            FileInputStream fileInputStream = this.context.openFileInput(groceryFileName);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuffer stringBuffer = new StringBuffer();
            while ((message = bufferedReader.readLine()) != null) {
                stringBuffer.append(message + "\n");
            }
            return stringBuffer.toString();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void writeGroceryFile(String message) {
        try {
            FileOutputStream fileOutputStream = this.context.openFileOutput(groceryFileName, context.MODE_PRIVATE);
            fileOutputStream.write(message.getBytes());
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String readInventoryFile() {
        try {
            String message;
            FileInputStream fileInputStream = this.context.openFileInput(inventoryFileName);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuffer stringBuffer = new StringBuffer();
            while ((message = bufferedReader.readLine()) != null) {
                stringBuffer.append(message + "\n");
            }
            return stringBuffer.toString();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void writeInventoryFile(String message) {
        try {
            FileOutputStream fileOutputStream = this.context.openFileOutput(inventoryFileName, context.MODE_PRIVATE);
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

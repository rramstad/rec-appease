package recappease.org.rec_appease.Recipes;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.InputFilter;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import recappease.org.rec_appease.MainActivity;
import recappease.org.rec_appease.R;
import recappease.org.rec_appease.Util.FileParser;
import recappease.org.rec_appease.Util.FoodItem;
import recappease.org.rec_appease.Util.FoodListAdapter;
import recappease.org.rec_appease.Util.InputFilterMinMax;

import java.util.ArrayList;

/**
 * Created by jungr on 4/10/18.
 */



public class AddIngredients extends Activity {
    ArrayList<String> addArray = new ArrayList<String>();
    Button done;
    EditText txt;
    ListView show;

    private EditText text_box;
    private EditText text_qty;
    private Spinner spn_unit;
    private ImageButton btn;
    ListView list;
    private static FoodListAdapter adapter;
    private static ArrayAdapter<CharSequence> adapter2;
    private static FileParser fileParser;
    private static ArrayList<FoodItem> inventoryItems;
    public static final int ACTIVITY_NUM = 1;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_add_ingredients);



        done = (Button) findViewById(R.id.done);
        text_box = (EditText) findViewById(R.id.item_name);
        text_qty = (EditText) findViewById(R.id.item_qty);
        text_qty.setFilters(new InputFilter[]{new InputFilterMinMax("0", "1000")});
        spn_unit = (Spinner) findViewById(R.id.item_unit);
        btn = (ImageButton) findViewById(R.id.add_button);
        list = (ListView) findViewById(R.id.inventory_list);
        list.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        fileParser = new FileParser(this.getApplicationContext());
        inventoryItems = fileParser.readIngredientsFile();
        adapter = new FoodListAdapter(this, inventoryItems, FoodListAdapter.fragmentID.INVENTORY, fileParser);
        list.setAdapter(adapter);
        adapter2 = ArrayAdapter.createFromResource(getApplicationContext(), R.array.units, android.R.layout.simple_spinner_dropdown_item);
        spn_unit.setAdapter(adapter2);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String foodname = text_box.getText().toString();
                int foodqty;
                try {
                    foodqty = Integer.parseInt(text_qty.getText().toString());
                } catch (NullPointerException e) {
                    return;
                }
                String foodunit = spn_unit.getSelectedItem().toString();
                if (foodunit.equals("-none-")) {
                    foodunit = "";
                }
                // this line adds the data of your EditText and puts in your array
                FoodItem newfood = new FoodItem(foodname, foodqty, foodunit);
                //String item_Name = text_box.getText().toString();
                //String quantity = text_qty.getText().toString();
                //Integer qty = Integer.parseInt(quantity);
                inventoryItems.add(newfood);
                fileParser.writeIngredientsFile(inventoryItems);
                text_box.setText("");
                text_qty.setText("");
                spn_unit.setSelection(0);



                Toast.makeText(getApplicationContext(), (CharSequence) "Added To Grocery List.", Toast.LENGTH_SHORT);
                // next thing you have to do is check if your adapter has changed
                adapter.notifyDataSetChanged();
                adapter2.notifyDataSetChanged();
            }
        });

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), CreateRecipeActivity.class);
                startActivity(i);
            }
        });
    }

    public static void updateList() {
        inventoryItems = fileParser.readIngredientsFile();
        adapter.notifyDataSetChanged();
    }
}


        //txt = (EditText) findViewById(R.id.ingredientsList);
        //show = (ListView) findViewById(R.id.list);

//        save.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String getinput = txt.getText().toString();
//                if (addArray.contains(getinput)) {
//
//                }
//                else {
//                    addArray.add(getinput);
//                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(AddIngredients.this, android.R.layout.simple_list_item_1, addArray);
//                    show.setAdapter(adapter);
//                    ((EditText) findViewById(R.id.ingredientsList)).setText(" ");
//
//                }
//            }
//        });




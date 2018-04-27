package recappease.org.rec_appease.Recipes;

import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.opengl.Matrix;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import recappease.org.rec_appease.MainActivity;
import recappease.org.rec_appease.R;
import recappease.org.rec_appease.Util.FileParser;
import recappease.org.rec_appease.Util.FoodItem;
import recappease.org.rec_appease.Util.FoodListAdapter;

/**
 * Created by Ramstadr6 on 3/30/2018.
 */



public class CreateRecipeActivity extends Activity implements View.OnClickListener{

    ListView list;
    RadioButton privacy_radio;

    Button save_btn;
    Button cancel_btn;
    Button add;

    ArrayList<FoodItem> ingredients;
    TextView title;
    ImageButton imageButton;

    ImageButton add_ingredient;
    EditText food_name;
    EditText food_quantity;
    Spinner food_unit;

    EditText time_text;
    EditText servings_text;
    EditText cost_text;

    EditText instruction_text;

    Recipe recipe;

    ImageView imageToUpload;
    private static final int RESULT_LOAD_IMAGE = 0;
    private static FileParser fileParser;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_recipe);

        add = findViewById(R.id.ingredients_button);
        //list = findViewById(R.id.ingredient_list);
        title = findViewById(R.id.recipe_title);
        //imageButton = findViewById(R.id.imageButton);
        add_ingredient = findViewById(R.id.add_button);
        food_name = findViewById(R.id.item_name);
        food_quantity = findViewById(R.id.item_qty);
        food_unit = findViewById(R.id.item_unit);
        time_text = findViewById(R.id.time_text);
        servings_text = findViewById(R.id.servings_text);
        cost_text = findViewById(R.id.cost_text);
        instruction_text = findViewById(R.id.instruction_text);
        privacy_radio = findViewById(R.id.public_button);

        save_btn = findViewById(R.id.save_button);
        cancel_btn = findViewById(R.id.cancel_button);

        imageToUpload = findViewById(R.id.imageButton);

        imageToUpload.setOnClickListener(this);


        //final FoodListAdapter adapter = new FoodListAdapter(this, ingredients, FoodListAdapter.fragmentID.CREATERECIPE, null);
        //list.setAdapter(adapter);
//        final ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.units, android.R.layout.simple_spinner_dropdown_item);
//        food_unit.setAdapter(adapter2);
        /*
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        */
        /**add_ingredient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = food_name.getText().toString();
                int quantity = Integer.parseInt(food_quantity.getText().toString());
                String unit = food_unit.getSelectedItem().toString();
                if (unit.equals("-none-")) {
                    unit = "";
                }
                FoodItem item = new FoodItem(name, quantity, unit);
                ingredients.add(item);
                food_name.setText("");
                food_quantity.setText("");
                food_unit.setSelection(0);

                adapter.notifyDataSetChanged();
                adapter2.notifyDataSetChanged();
            }
        });*/

        save_btn.setOnClickListener(new View.OnClickListener() {
            ArrayList<FoodItem> ingredients = new ArrayList<>(30);

            @Override
            public void onClick(View v) {
                fileParser = new FileParser(getApplicationContext());
                String titlestring = title.getText().toString();
//                android.graphics.Matrix image = imageButton.getImageMatrix();
                int time = Integer.parseInt(time_text.getText().toString());
                int serving = Integer.parseInt(servings_text.getText().toString());
                int cost = Integer.parseInt(cost_text.getText().toString());
                boolean privacy = privacy_radio.isChecked();
                String instructions = instruction_text.getText().toString();
                ingredients = fileParser.readIngredientsFile();
                recipe = new Recipe(titlestring, null, ingredients, privacy, time, serving, cost, instructions, 0, MainActivity.userId);
                //FileParser fileParser = new FileParser(getApplicationContext());
                //RecipesFragment.recipeList.add(recipe);
                //ArrayList<Recipe> recipeList = fileParser.readRecipeFile();
                //recipeList.add(recipe);

                //fileParser.writeRecipeFile(RecipesFragment.recipeList);
                MainActivity.createRecipe(recipe);

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        cancel_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), AddIngredients.class);
                startActivity(i);
            }
        });

        /*
        .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        */


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imageButton:
                Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent, RESULT_LOAD_IMAGE);
                break;

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && data != null) {
            Uri selectedImage = data.getData();
            imageToUpload.setImageURI(selectedImage);
        }
    }
}



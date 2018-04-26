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

    Button ingredients;
    Button tags;

    //ArrayList<FoodItem> ingredients;
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

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_recipe);

        ingredients = findViewById(R.id.ingredients_button);
        //tags = findViewById(R.id.tags_button);

        title = findViewById(R.id.recipe_title);
        add_ingredient = findViewById(R.id.add_button);
        food_name = findViewById(R.id.item_name);
        food_quantity = findViewById(R.id.item_qty);
        food_unit = findViewById(R.id.item_unit);
        time_text = findViewById(R.id.time_text);
        servings_text = findViewById(R.id.servings_text);
        cost_text = findViewById(R.id.cost_text);
        privacy_radio = findViewById(R.id.public_button);


        imageToUpload = findViewById(R.id.imageButton);


        imageToUpload.setOnClickListener(this);




        ingredients.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "ingredients", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getApplicationContext(), AddIngredients.class);
                startActivity(i);
            }
        });

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

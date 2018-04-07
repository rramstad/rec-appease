package recappease.org.rec_appease.Recipes;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import recappease.org.rec_appease.R;
import recappease.org.rec_appease.Util.FileParser;
import recappease.org.rec_appease.Util.FoodItem;
import recappease.org.rec_appease.Util.FoodListAdapter;

/**
 * Created by Ramstadr6 on 3/30/2018.
 */

public class RecipeAdapter extends ArrayAdapter {
    public ArrayList<Recipe> recipeList;
    public Activity context;

    public RecipeAdapter(Activity context, ArrayList<Recipe> recipeList) {
        super(context, R.layout.layout_recipes, recipeList);
        this.context = context;
        this.recipeList = recipeList;
    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.layout_recipes, null,true);

        //this code gets references to objects in the listview_row.xml file
        //TextView foodField = (TextView) rowView.findViewById(R.id.foodItemTextView);

        TextView titleText = (TextView)rowView.findViewById(R.id.recipe_title);
        TextView serving_size = (TextView)rowView.findViewById(R.id.serving_size);
        TextView ingredients = (TextView)rowView.findViewById(R.id.ingredients);
        TextView time = (TextView)rowView.findViewById(R.id.recipe_time);
        //this code sets the values of the objects to values from the arrays
        //foodText.setText(foodList.get(position).quantity + " " + foodList.get(position).unit + " " + foodList.get(position).name);

        //foodunit.setSelection(spinnerAdapter.getPosition(foodList.get(position).unit));
        Recipe rec = recipeList.get(position);
        titleText.setText(rec.title);
        serving_size.setText(rec.serving + " servings");
        time.setText(rec.time + " minutes");

        return rowView;
    };
}

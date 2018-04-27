package recappease.org.rec_appease.Recipes;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;

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
    private String recipeID;



    public RecipeAdapter(Activity context, ArrayList<Recipe> recipeList) {
        super(context, R.layout.recipe_profile, recipeList);
        this.context = context;
        this.recipeList = recipeList;
    }

    public View getView(final int position, View view, ViewGroup parent) {
        final LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.recipe_profile, null,true);

        //this code gets references to objects in the listview_row.xml file
        //TextView foodField = (TextView) rowView.findViewById(R.id.foodItemTextView);

        TextView titleText = (TextView)rowView.findViewById(R.id.recipe_title);
        TextView serving_size = (TextView)rowView.findViewById(R.id.serving_size);
        TextView cost = (TextView)rowView.findViewById(R.id.cost);
        TextView time = (TextView)rowView.findViewById(R.id.recipe_time);
        TextView ingredient = (TextView)rowView.findViewById(R.id.ingredients);
        TextView instructions = (TextView)rowView.findViewById(R.id.instructions);
        TextView likes = (TextView)rowView.findViewById(R.id.likes);

        ImageButton upvote = (ImageButton)rowView.findViewById(R.id.upvote);
        ImageButton downvote = (ImageButton)rowView.findViewById(R.id.downvote);
        ImageButton favorite = (ImageButton)rowView.findViewById(R.id.favorite);
        Button addToday = (Button)rowView.findViewById(R.id.addToday);
        Button addInventory = (Button)rowView.findViewById(R.id.addIngredients);
        //this code sets the values of the objects to values from the arrays
        //foodText.setText(foodList.get(position).quantity + " " + foodList.get(position).unit + " " + foodList.get(position).name);

        //foodunit.setSelection(spinnerAdapter.getPosition(foodList.get(position).unit));

        final Recipe rec = recipeList.get(position);
        recipeID = rec.title+rec.creator;
        titleText.setText(rec.title);
        serving_size.setText(rec.serving + " servings");
        cost.setText("$" + rec.cost);
        time.setText(rec.time + " minutes");
        likes.setText(rec.likes + " Likes");

        Iterator iterator = rec.ingredients.iterator();
        String m = "";
        while (iterator.hasNext()) {
            FoodItem next = (FoodItem) iterator.next();
            m = m + next.toString();
        }
        ingredient.setText(m);
        instructions.setText(rec.instructions);

        upvote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }

        });

        downvote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }

        });

        favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }

        });

        addToday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String contents = RecipesFragment.getFileParser().readTodayFile();
                contents += recipeID;
                RecipesFragment.getFileParser().writeTodayRecipeFile(contents);
            }

        });

        addInventory.setOnClickListener(new View.OnClickListener() {
            FileParser f = new FileParser(getContext());

            @Override
            public void onClick(View v) {
                ArrayList<FoodItem> finalList = new ArrayList<FoodItem>();
                Recipe rec = recipeList.get(position);
                ArrayList<FoodItem> initial = f.readGroceryFile();
                //Iterator iterator = initial.iterator();
                for (int i = 0; i < initial.size(); i++) {
                    finalList.add(initial.get(i));
                }
                for (int i = 0; i < rec.ingredients.size(); i++) {
                    finalList.add(rec.ingredients.get(i));
                }
                f.writeGroceryFile(finalList);
            }

        });

        return rowView;
    }
}

package recappease.org.rec_appease.Recipes;

import android.app.Activity;
import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

import recappease.org.rec_appease.R;
import recappease.org.rec_appease.Util.FileParser;
import recappease.org.rec_appease.Util.FoodItem;
import recappease.org.rec_appease.Util.FoodListAdapter;

/**
 * Created by Ramstadr6 on 3/30/2018.
 */

public class RecipeAdapter extends ArrayAdapter {
    ArrayList<Recipe> recipeList;
    Context context;

    public RecipeAdapter(Activity context, ArrayList<Recipe> recipeList) {
        super(context, R.layout.layout_listview, recipeList);
        this.context = context;
        this.recipeList = recipeList;
    }
}

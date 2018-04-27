package recappease.org.rec_appease.Today;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import recappease.org.rec_appease.R;
import recappease.org.rec_appease.Recipes.Recipe;

/**
 * Created by gunjangauri on 25/04/18.
 */


public class Today_Adapter extends ArrayAdapter {
    public ArrayList<Recipe> recipeList;
    public Activity context;

    public Today_Adapter(Activity context, ArrayList<Recipe> recipeList) {
        super(context, R.layout.layout_today, recipeList);
        this.context = context;
        this.recipeList = recipeList;
    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.layout_today, null,true);

        //this code gets references to objects in the listview_row.xml file
        //TextView foodField = (TextView) rowView.findViewById(R.id.foodItemTextView);

        TextView today_title = (TextView)rowView.findViewById(R.id.today_title);
        TextView today_ingredients = (TextView)rowView.findViewById(R.id.today_ingredients);
        TextView  today_instruction= (TextView)rowView.findViewById(R.id.today_instructions);
        //TextView time = (TextView)rowView.findViewById(R.id.recipe_time);
        //this code sets the values of the objects to values from the arrays
        //foodText.setText(foodList.get(position).quantity + " " + foodList.get(position).unit + " " + foodList.get(position).name);

        //foodunit.setSelection(spinnerAdapter.getPosition(foodList.get(position).unit));
        Recipe rec = recipeList.get(position);
        today_title.setText(rec.title);
        today_ingredients.setText(Recipe.displayIngredients(rec.ingredients));
        today_instruction.setText(rec.instructions);

        return rowView;
    };
}

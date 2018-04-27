package recappease.org.rec_appease.Today;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import recappease.org.rec_appease.R;
import recappease.org.rec_appease.Recipes.Recipe;

/**
 * Created by gunjangauri on 25/04/18.
 */


public class Today_Adapter extends ArrayAdapter {
    public ArrayList<Recipe> recipeList;
    //public ArrayList<Recipe> todayList;
    public Activity context;
    private String recipeID;

    public Today_Adapter(Activity context, ArrayList<Recipe> recipeList) {
        super(context, R.layout.layout_today, recipeList);
        this.context = context;
        this.recipeList = recipeList;
        //this.todayList = todayList;
    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.layout_today, null,true);

        TextView today_title = (TextView)rowView.findViewById(R.id.today_title);
        TextView today_ingredients = (TextView)rowView.findViewById(R.id.today_ingredients);
        TextView  today_instruction= (TextView)rowView.findViewById(R.id.today_instructions);
        Button today_delete = (Button)rowView.findViewById(R.id.today_delete);
        Button today_cooked = (Button)rowView.findViewById(R.id.today_cooked);

        Recipe rec = recipeList.get(position);
        //Recipe t_list = todayList.get(position);
        String todayList = TodayFragment.getFileParser().readTodayFile();

        recipeID = rec.title+rec.creator;

        if(todayList.contains(recipeID)) {

            today_title.setText(rec.title);


            today_ingredients.setText(Recipe.displayIngredients(rec.ingredients));


            today_instruction.setText(rec.instructions);
        }

        today_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String contents = TodayFragment.getFileParser().readTodayFile();
                contents = contents.replaceAll(recipeID, "");
                TodayFragment.getFileParser().writeTodayRecipeFile(contents);
            }

        });

        today_cooked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String contents = TodayFragment.getFileParser().readTodayFile();
                contents = contents.replaceAll(recipeID, "");
                TodayFragment.getFileParser().writeTodayRecipeFile(contents);
            }

        });

        return rowView;
    };
}

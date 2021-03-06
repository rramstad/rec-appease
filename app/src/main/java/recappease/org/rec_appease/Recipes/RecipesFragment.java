package recappease.org.rec_appease.Recipes;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Iterator;

import recappease.org.rec_appease.MainActivity;
import recappease.org.rec_appease.Util.BottomNavigationViewHelper;
import recappease.org.rec_appease.R;
import recappease.org.rec_appease.Util.FileParser;


public class RecipesFragment extends Fragment {
    public static final int ACTIVITY_NUM = 4;

    ListView list;
    Button create_recipe;
    Button browse_button;
    Button favorite_button;
    public static ArrayList<Recipe> recipeList;
    public static ArrayList<Recipe> tempList;
    public RecipeAdapter recipeAdapter;
    private static FileParser fileParser;

    public static FileParser getFileParser(){
        return fileParser;
    }

    //@Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recipes, container, false);


        new Thread(new Runnable() {
            @Override
            public void run() {
                new ScanThread(MainActivity.dynamoDBClient).run();
                // Item saved
            }
        }).start();

        fileParser = new FileParser(this.getContext());
        recipeList = ScanThread.getRecipes();
        recipeAdapter = new RecipeAdapter(getActivity(), recipeList);
        list = view.findViewById(R.id.recipes_result);
        list.setAdapter(recipeAdapter);
        recipeAdapter.notifyDataSetChanged();


        create_recipe = view.findViewById(R.id.create_recipe_btn);
        create_recipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), CreateRecipeActivity.class);
                startActivity(intent);
            }
        });


        browse_button = view.findViewById(R.id.browse_button);
        browse_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                /*
                if (tempList.isEmpty()) {
                    recipeAdapter.notifyDataSetChanged();
                    return;
                }
                Iterator iterator = tempList.iterator();
                while (iterator.hasNext()) {
                    Recipe temp = (Recipe) iterator.next();
                    recipeList.add(temp);
                    tempList.remove(temp);
                }
                recipeAdapter.notifyDataSetChanged();
                */
            }

        });

        favorite_button = view.findViewById(R.id.favorite_button);
        favorite_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                /*
                // Search database for useraction on the recipe
                // Move non-favorited recipes to tempList
                tempList.clear();
                UserActionHandler UAH = new UserActionHandler(MainActivity.dynamoDBClient);
                UAH.loadUserActions(MainActivity.userId);
                Iterator iterator = UAH.favList.iterator();
                while(iterator.hasNext()) {
                    UserAction ua = (UserAction) iterator.next();
                    Recipe rec = ScanThread.getRecipe(ua.recipeId);
                    if(rec != null) {

                    }
                }
                */
            }

        });

        return view;
    }

}

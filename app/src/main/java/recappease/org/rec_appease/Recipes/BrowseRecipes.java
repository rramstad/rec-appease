package recappease.org.rec_appease.Recipes;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import recappease.org.rec_appease.MainActivity;
import recappease.org.rec_appease.R;

public class BrowseRecipes extends Fragment {
    ListView list;
    Button create_recipe;
    public static ArrayList<Recipe> recipeList;
    public RecipeAdapter recipeAdapter;

    //@Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_listview, container, false);
        /*
        FileParser fileParser = new FileParser(getContext());
        recipeList = fileParser.readRecipeFile();
           */
        new Thread(new Runnable() {
            @Override
            public void run() {
                new ScanThread(MainActivity.dynamoDBClient).run();
                // Item saved
            }
        }).start();

        recipeList = ScanThread.getRecipes();
        recipeAdapter = new RecipeAdapter(getActivity(), recipeList);

        list = view.findViewById(R.id.recipes_result);
        list.setAdapter(recipeAdapter);
        recipeAdapter.notifyDataSetChanged();

        return view;
    }

}

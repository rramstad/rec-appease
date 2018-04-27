package recappease.org.rec_appease.Today;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;

import recappease.org.rec_appease.MainActivity;
import recappease.org.rec_appease.R;
import recappease.org.rec_appease.Recipes.Recipe;
import recappease.org.rec_appease.Recipes.ScanThread;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

public class TodayFragment extends Fragment {
    public static final int ACTIVITY_NUM = 4;

    ListView list;
    public static ArrayList<Recipe> recipeList;
    public Today_Adapter today_Adapter;

    //private Session session;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_today, container, false);

        /*
        FileParser fileParser = new FileParser(getContext());
        recipeList = fileParser.readRecipeFile();
           */
/*        new Thread(new Runnable() {
            @Override
            public void run() {
                new ScanThread(MainActivity.dynamoDBClient).run();
                // Item saved
            }
        }).start();
       */
/*
        recipeList = ScanThread.getRecipes();
        today_Adapter = new Today_Adapter(getActivity(), recipeList);


        list = view.findViewById(R.id.today_result);
        list.setAdapter(today_Adapter);
        today_Adapter.notifyDataSetChanged();
*/
        return view;

    }

    private void today() {

        //Recipe recipe = MainActivity.readRecipe();
        //signout.setText(recipe.title);
    }


}
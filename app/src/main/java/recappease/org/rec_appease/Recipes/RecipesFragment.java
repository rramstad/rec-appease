package recappease.org.rec_appease.Recipes;

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
import android.widget.ListView;

import java.util.ArrayList;

import recappease.org.rec_appease.Util.BottomNavigationViewHelper;
import recappease.org.rec_appease.R;
import recappease.org.rec_appease.Util.FileParser;

public class RecipesFragment extends Fragment  {
    public static final int ACTIVITY_NUM = 4;

    ListView list;
    Button create_recipe;
    public static ArrayList<Recipe> recipeList;
    public RecipeAdapter recipeAdapter;

    //@Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recipes, container, false);
        FileParser fileParser = new FileParser(getContext());
        recipeList = fileParser.readRecipeFile();
        recipeAdapter = new RecipeAdapter(getActivity(), recipeList);

        /*
        TabLayout tabs = (TabLayout) view.findViewById(R.id.tablayout);
        tabs.addTab(tabs.newTab().setText("Browse"));
        tabs.addTab(tabs.newTab().setText("Saved"));
        tabs.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = (ViewPager)view.findViewById(R.id.pager2);
        final PagerAdapter adapter = new PagerAdapter(getFragmentManager(), tabs.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.setOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabs));

        tabs.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        */

        list = view.findViewById(R.id.recipes_result);
        list.setAdapter(recipeAdapter);
        create_recipe = view.findViewById(R.id.create_recipe_btn);
        create_recipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), CreateRecipeActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }

}

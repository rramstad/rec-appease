package recappease.org.rec_appease.Util;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.view.MenuItem;

import recappease.org.rec_appease.Grocery.GroceryFragment;
import recappease.org.rec_appease.Inventory.InventoryFragment;
import recappease.org.rec_appease.MealPlan.MealPlanFragment;
import recappease.org.rec_appease.R;
import recappease.org.rec_appease.Recipes.RecipesFragment;
import recappease.org.rec_appease.MainActivity;

/**
 * Created by Ramstadr6 on 2/16/2018.
 */

public class BottomNavigationViewHelper {

    public static void enableNavigation(final Context context, BottomNavigationView bottomNavigationView){

        BottomNavigationView.OnNavigationItemSelectedListener bottomNavListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_today:
                        // ACTIVITY_NUM == 0
                        Intent intentToday = new Intent(context, MainActivity.class);
                        context.startActivity(intentToday);
                        return true;
                    case R.id.action_grocery:
                        // ACTIVITY_NUM == 1
                        Intent intentGrocery = new Intent(context, GroceryFragment.class);
                        context.startActivity(intentGrocery);
                        return true;
                    case R.id.action_inventory:
                        // ACTIVITY_NUM == 2
                        Intent intentInventory = new Intent(context, InventoryFragment.class);
                        context.startActivity(intentInventory);
                        return true;
                    case R.id.action_mealplan:
                        // ACTIVITY_NUM == 3
                        Intent intentMealPlan = new Intent(context, MealPlanFragment.class);
                        context.startActivity(intentMealPlan);
                        return true;
                    case R.id.action_recipes:
                        // ACTIVITY_NUM == 4
                        Intent intentRecipes = new Intent(context, RecipesFragment.class);
                        context.startActivity(intentRecipes);
                        return true;
                }
                return false;
            }
        };

        bottomNavigationView.setOnNavigationItemSelectedListener(bottomNavListener);
    }

    public static void enableNavigationWithPager(BottomNavigationView bottomNavigationView, final ViewPager pager){

        BottomNavigationView.OnNavigationItemSelectedListener bottomNavListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_today:
                        // ACTIVITY_NUM == 0
                        pager.setCurrentItem(0);
                        return true;
                    case R.id.action_grocery:
                        // ACTIVITY_NUM == 1
                        pager.setCurrentItem(1);
                        return true;
                    case R.id.action_inventory:
                        // ACTIVITY_NUM == 2
                        pager.setCurrentItem(2);
                        return true;
                    case R.id.action_mealplan:
                        // ACTIVITY_NUM == 3
                        pager.setCurrentItem(3);
                        return true;
                    case R.id.action_recipes:
                        // ACTIVITY_NUM == 4
                        pager.setCurrentItem(4);
                        return true;
                }
                return false;
            }
        };

        bottomNavigationView.setOnNavigationItemSelectedListener(bottomNavListener);
    }
}

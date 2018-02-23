package recappease.org.rec_appease;

import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import recappease.org.rec_appease.Grocery.GroceryFragment;
import recappease.org.rec_appease.Inventory.InventoryFragment;
import recappease.org.rec_appease.MealPlan.MealPlanFragment;
import recappease.org.rec_appease.Recipes.RecipesFragment;
import recappease.org.rec_appease.Today.TodayFragment;
import recappease.org.rec_appease.Util.BottomNavigationViewHelper;
import recappease.org.rec_appease.R;
import recappease.org.rec_appease.Util.SectionsPagerAdapter;
import com.facebook.FacebookSdk;


public class MainActivity extends AppCompatActivity {
    public static final int ACTIVITY_NUM = 0;
    private MenuItem prevMenuItem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final BottomNavigationView bottomNavigationView = (BottomNavigationView)  findViewById(R.id.bottom_navigation);
        //BottomNavigationViewHelper.enableNavigation(this, bottomNavigationView);
        setupViewPager();
        ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (prevMenuItem != null) {
                    prevMenuItem.setChecked(false);
                }
                else
                {
                    bottomNavigationView.getMenu().getItem(0).setChecked(false);
                }

                bottomNavigationView.getMenu().getItem(position).setChecked(true);
                prevMenuItem = bottomNavigationView.getMenu().getItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        BottomNavigationViewHelper.enableNavigationWithPager(bottomNavigationView, viewPager);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(ACTIVITY_NUM);
        menuItem.setChecked(true);
    }

    private void setupViewPager() {
        SectionsPagerAdapter adapter = new SectionsPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new TodayFragment());
        adapter.addFragment(new GroceryFragment());
        adapter.addFragment(new InventoryFragment());
        adapter.addFragment(new MealPlanFragment());
        adapter.addFragment(new RecipesFragment());
        ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.setAdapter(adapter);
    }

}

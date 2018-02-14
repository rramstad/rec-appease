package recappease.org.rec_appease;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    BottomNavigationView bottomNavigationView = (BottomNavigationView)
            findViewById(R.id.bottom_navigation);

    BottomNavigationView.OnNavigationItemSelectedListener bottomNavListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            //TextView textview = (TextView) findViewById(R.id.textView);
            switch (item.getItemId()) {
                case R.id.action_today:
                    //textview.setText("Today");
                    break;
                case R.id.action_grocery:
                    //textview.setText("Grocery");
                    break;
                case R.id.action_inventory:
                    //textview.setText("Inventory");
                    break;
                case R.id.action_mealplan:
                    //textview.setText("Meal Plan");
                    break;
                case R.id.action_recipes:
                    //textview.setText("Recipes");
                    break;
            }
            return true;
        }
    };

}

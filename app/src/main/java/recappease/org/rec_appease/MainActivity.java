package recappease.org.rec_appease;

import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBAttribute;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBMapper;
import com.amazonaws.ClientConfiguration;
import com.amazonaws.mobile.auth.core.IdentityHandler;
import com.amazonaws.mobile.auth.core.IdentityManager;
import com.amazonaws.mobile.client.AWSMobileClient;
import com.amazonaws.mobile.client.AWSStartupHandler;
import com.amazonaws.mobile.client.AWSStartupResult;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.CognitoUser;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.CognitoUserPool;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;

import java.util.HashSet;
import java.util.Set;

import recappease.org.rec_appease.Grocery.GroceryFragment;
import recappease.org.rec_appease.Inventory.InventoryFragment;
import recappease.org.rec_appease.MealPlan.MealPlanFragment;
import recappease.org.rec_appease.Recipes.RecipesFragment;
import recappease.org.rec_appease.Today.TodayFragment;
import recappease.org.rec_appease.Util.BottomNavigationViewHelper;
import recappease.org.rec_appease.Util.SectionsPagerAdapter;
import recappease.org.rec_appease.Util.SectionsPagerAdapterRecipes;
import recappease.org.rec_appease.models.nosql.RecipesDO;


public class MainActivity extends AppCompatActivity {
    public static final int ACTIVITY_NUM = 0;
    private MenuItem prevMenuItem;

    private SectionsPagerAdapterRecipes mSectionsPageAdapter;

    private ViewPager mViewPager;

    ClientConfiguration clientConfiguration = new ClientConfiguration();
    // Create a CognitoUserPool object to refer to your user pool
    CognitoUserPool userPool = new CognitoUserPool(this, " us-east-2_vunJZOb2v", "7d7476oeo5af22gqe8phoko0u1", "1mkcuecti7vtobf4src2kl50trefg4fflmqghb4lbtncks24la49", clientConfiguration);

    /*CognitoUser user;

    public MainActivity() {
        user = userPool.getCurrentUser();
        Log.d("username: %s\n", user.getUserId());
    }*/

    // Declare a DynamoDBMapper object
    private DynamoDBMapper dynamoDBMapper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        /* Gets user id */
        AWSMobileClient.getInstance().initialize(this, new AWSStartupHandler() {
            @Override
            public void onComplete(AWSStartupResult awsStartupResult) {
                //Make a network call to retrieve the identity ID
                // using IdentityManager. onIdentityId happens UPon success.
                IdentityManager.getDefaultIdentityManager().getUserID(new IdentityHandler() {
                    @Override
                    public void onIdentityId(String s) {
                        //The network call to fetch AWS credentials succeeded, the cached
                        // user ID is available from IdentityManager throughout your app
                        Log.d("MainActivity", "Identity ID is: " + s);
                        Log.d("MainActivity", "Cached Identity ID: " +
                                IdentityManager.getDefaultIdentityManager().getCachedUserID());
                    }

                    @Override
                    public void handleError(Exception e) {
                        Log.e("MainActivity", "Error in retrieving Identity ID: " +
                                e.getMessage());
                    }
                });
            }
        }).execute();

        // Instantiate a AmazonDynamoDBMapperClient
        AmazonDynamoDBClient dynamoDBClient = new AmazonDynamoDBClient(AWSMobileClient.getInstance().getCredentialsProvider());
        this.dynamoDBMapper = DynamoDBMapper.builder()
                .dynamoDBClient(dynamoDBClient)
                .awsConfiguration(AWSMobileClient.getInstance().getConfiguration())
                .build();

        /* Creating a recipe */
        createRecipe();

        /* Reading the recipe */
        readRecipe();

        final BottomNavigationView bottomNavigationView = (BottomNavigationView)  findViewById(R.id.bottom_navigation);
        //BottomNavigationViewHelper.enableNavigation(this, bottomNavigationView);
        setupViewPager();

//        mSectionsPageAdapter = new SectionsPagerAdapterRecipes(getSupportFragmentManager());
//        mViewPager = (ViewPager) findViewById(R.id.);
//         setupRecipesPager(mViewPager);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

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


    /* Function used to put stuff into the database */
    private void createRecipe(){

        final RecipesDO recipe = new RecipesDO();

        Set<String> s = new HashSet<String>();
        s.add("chicken");

        recipe.setName("Raw chicken");
        recipe.setCreator("A Dawg");
        recipe.setUserId(recipe.getName()+recipe.getCreator());
        recipe.setApproxCost((double) 2000);
        recipe.setIngredients(s);
        recipe.setInstructions("Get the chicken");
        recipe.setLikes((double)0);
        recipe.setPrepTime(0.0);
        recipe.setPublic(true);
        recipe.setServingSize(23.0);
        recipe.setTags(s);


        new Thread(new Runnable() {
            @Override
            public void run() {
                dynamoDBMapper.save(recipe);
                // Item saved
            }
        }).start();
    }

    /* function used to get stuff from the database and print it */
    private void readRecipe(){
        new Thread(new Runnable() {
            @Override
            public void run() {

                RecipesDO recipe = dynamoDBMapper.load(
                        RecipesDO.class,
                        "raw chicken");

                // Item read
                Log.d("Approx Cost:", recipe.toString());
            }
        }).start();
    }

}

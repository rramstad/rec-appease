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
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBScanExpression;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.KeyPair;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ScanRequest;
import com.amazonaws.services.dynamodbv2.model.ScanResult;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import recappease.org.rec_appease.Grocery.GroceryFragment;
import recappease.org.rec_appease.Inventory.InventoryFragment;
import recappease.org.rec_appease.MealPlan.MealPlanFragment;
import recappease.org.rec_appease.Recipes.Recipe;
import recappease.org.rec_appease.Recipes.RecipesFragment;
import recappease.org.rec_appease.Recipes.ScanThread;
import recappease.org.rec_appease.Today.TodayFragment;
import recappease.org.rec_appease.Util.BottomNavigationViewHelper;
import recappease.org.rec_appease.Util.FoodItem;
import recappease.org.rec_appease.Util.SectionsPagerAdapter;
import recappease.org.rec_appease.Util.SectionsPagerAdapterRecipes;
import recappease.org.rec_appease.models.nosql.RecipesDO;


public class MainActivity extends AppCompatActivity {
    public static final int ACTIVITY_NUM = 0;
    private MenuItem prevMenuItem;

    private SectionsPagerAdapterRecipes mSectionsPageAdapter;

    private ViewPager mViewPager;

    public static String userId;
    //public Recipe rcp;

    ClientConfiguration clientConfiguration = new ClientConfiguration();
    public static AmazonDynamoDBClient dynamoDBClient;
    //public AmazonDynamoDB dynamoDBClient;
    // Create a CognitoUserPool object to refer to your user pool
    CognitoUserPool userPool = new CognitoUserPool(this, " us-east-2_vunJZOb2v", "7d7476oeo5af22gqe8phoko0u1", "1mkcuecti7vtobf4src2kl50trefg4fflmqghb4lbtncks24la49", clientConfiguration);

    /*CognitoUser user;

    public MainActivity() {
        user = userPool.getCurrentUser();
        Log.d("username: %s\n", user.getUserId());
    }*/

    // Declare a DynamoDBMapper object
    public static DynamoDBMapper dynamoDBMapper;

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

                        userId = IdentityManager.getDefaultIdentityManager().getCachedUserID();
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


        dynamoDBClient = new AmazonDynamoDBClient(AWSMobileClient.getInstance().getCredentialsProvider());
        dynamoDBMapper = DynamoDBMapper.builder()
                .dynamoDBClient(dynamoDBClient)
                .awsConfiguration(AWSMobileClient.getInstance().getConfiguration())
                .build();


        //dynamoDBClient = AmazonDynamoDBClientBuilder.standard().build();

        /* Creating a recipe */
//        createRecipe();

        /* Reading the recipe */
//        readRecipe();

        findRecipes();

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

/* MainActivity.createRecipe(recipe); */
    /* Function used to put stuff into the database */
    public static void createRecipe(Recipe recipe){

        final RecipesDO recipeDO = new RecipesDO();

        Set<String> s = new HashSet<String>();

        recipeDO.setName(recipe.title);
        recipeDO.setCreator(userId);
        recipeDO.setUserId(recipeDO.getName()+recipeDO.getCreator());
        recipeDO.setApproxCost((double) recipe.cost);
        Iterator<FoodItem> iterator = recipe.ingredients.iterator();
        String message = "";
        while(iterator.hasNext()) {
            FoodItem next = iterator.next();
            message = message + next.name + ":::" + Integer.toString(next.quantity) + ":::" + next.unit;
            if (iterator.hasNext()) {
                message = message + ";;;";
            }
        }
        recipeDO.setIngredients(message);
        recipeDO.setInstructions(recipe.instructions);
        recipeDO.setLikes((double)0);
        recipeDO.setPrepTime((double)recipe.time);
        recipeDO.setPublic(recipe.privacy);
        recipeDO.setServingSize((double)recipe.serving);


        new Thread(new Runnable() {
            @Override
            public void run() {
                dynamoDBMapper.save(recipeDO);
                // Item saved
            }
        }).start();
    }

    /* function used to get stuff from the database and print it */

    private void findRecipes() {

//        System.out.println("FindBooksPricedLessThanSpecifiedValue: Scan ProductCatalog.");

        /*
        Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
        eav.put(":val1", new AttributeValue().withN(value));
        eav.put(":val2", new AttributeValue().withS("Book"));

        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
                .withFilterExpression("Price < :val1 and ProductCategory = :val2").withExpressionAttributeValues(eav);
        */
        /*
        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();

        List<RecipesDO> scanResult = dynamoDBMapper.scan(RecipesDO.class, scanExpression);
        ArrayList<Recipe> recipeList = new ArrayList<Recipe>(30);
        for (RecipesDO recipe : scanResult) {
            //Recipe entry = new Recipe(recipe.getName(), null, )
            Log.d("RecipeFound", recipe.getName() + " " + recipe.getApproxCost() + " " + recipe.getServingSize() + " " + recipe.getPrepTime() + "\n");
        }
        */

        new Thread(new Runnable() {
            @Override
            public void run() {
                new ScanThread(dynamoDBClient).run();
                // Item saved
            }
        }).start();


    }
}

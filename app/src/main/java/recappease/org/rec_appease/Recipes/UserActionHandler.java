package recappease.org.rec_appease.Recipes;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ScanRequest;
import com.amazonaws.services.dynamodbv2.model.ScanResult;

import java.util.ArrayList;
import java.util.Map;

import recappease.org.rec_appease.MainActivity;
import recappease.org.rec_appease.models.nosql.UserActionDO;

/**
 * Created by Ramstadr6 on 4/25/2018.
 */

public class UserActionHandler {
    private static boolean favorited;
    private static int like;
    private AmazonDynamoDBClient dynamoDBClient;
    public ArrayList<UserAction> favList;
    public ArrayList<UserAction> likeList;

    public UserActionHandler(AmazonDynamoDBClient dynamoDBClient) {
        this.dynamoDBClient = dynamoDBClient;
        favList = new ArrayList<UserAction>(30);
        likeList = new ArrayList<UserAction>(30);
    }

    public void loadUserActions(String UserID) {
        final String userId = UserID;
        new Thread(new Runnable() {
            @Override
            public void run() {
                ScanRequest scanRequest = new ScanRequest().withTableName("recappease-mobilehub-1835379734-recipes");
                ScanResult scanResult = dynamoDBClient.scan(scanRequest);
                for(Map<String, AttributeValue> item : scanResult.getItems()) {
                    String user = item.get("user_name").toString();
                    user = user.substring(3, user.length() - 2);
                    if (user.equals(userId)) {
                        String recp = item.get("recipe_name").toString();
                        recp = recp.substring(3, recp.length() - 2);
                        String favStr = item.get("favorite").toString();
                        favStr = favStr.substring(3, favStr.length() - 2);
                        boolean fav = Boolean.parseBoolean(favStr);
                        String likeStr = item.get("like").toString();
                        likeStr = likeStr.substring(3, likeStr.length() - 2);
                        int like = Integer.parseInt(likeStr);
                        if (fav == true) {
                            favList.add(new UserAction(recp, like, fav));
                        }
                        likeList.add(new UserAction(recp, like, fav));
                    }
                }
            }
        }).start();

    }

    public void changeLikeAction(String userId, Recipe recipe, int like, boolean favorite) {
        final UserActionDO uaDO = new UserActionDO();
        uaDO.setFavorite(favorite);
        uaDO.setLike((double) like);
        uaDO.setUserName(userId);
        uaDO.setRecipeName(recipe.title+recipe.creator);
        uaDO.setPrimaryKey(uaDO.getUserName()+uaDO.getRecipeName());
        new Thread(new Runnable() {
            @Override
            public void run() {
                MainActivity.dynamoDBMapper.save(uaDO);
                // Item saved
            }
        }).start();
    }

    public boolean isFavorited() {
        return favorited;
    }

    public int getLike() {
        return like;
    }
}

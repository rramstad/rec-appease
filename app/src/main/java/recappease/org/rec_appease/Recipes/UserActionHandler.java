package recappease.org.rec_appease.Recipes;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;

/**
 * Created by Ramstadr6 on 4/25/2018.
 */

public class UserActionHandler {
    private static boolean favorited;
    private static int like;
    private AmazonDynamoDBClient dynamoDBClient;

    public UserActionHandler(AmazonDynamoDBClient dynamoDBClient) {
        this.dynamoDBClient = dynamoDBClient;
    }

    public static void loadLikeAndFavorite() {
        
    }

    public boolean isFavorited() {
        return favorited;
    }

    public int getLike() {
        return like;
    }
}

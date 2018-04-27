package recappease.org.rec_appease.Recipes;

/**
 * Created by Ramstadr6 on 4/27/2018.
 */

public class UserAction {
    public String recipeId;
    public int like;
    public boolean favorite;
    public UserAction(String recipeId, int like, boolean favorite) {
        this.recipeId = recipeId;
        this.like = like;
        this.favorite = favorite;
    }
}

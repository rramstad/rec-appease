package recappease.org.rec_appease.models.nosql;

import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBAttribute;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBHashKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBIndexHashKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBIndexRangeKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBRangeKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBTable;

import java.util.List;
import java.util.Map;
import java.util.Set;

@DynamoDBTable(tableName = "recappease-mobilehub-1835379734-recipes")

public class RecipesDO {
    private String _userId;
    private String _name;
    private Double _approxCost;
    private String _creator;
    private String _ingredients;
    private String _instructions;
    private Double _likes;
    private Double _prepTime;
    private Boolean _public;
    private Double _servingSize;

    @DynamoDBHashKey(attributeName = "userId")
    @DynamoDBAttribute(attributeName = "userId")
    public String getUserId() {
        return _userId;
    }

    public void setUserId(final String _userId) {
        this._userId = _userId;
    }
    @DynamoDBRangeKey(attributeName = "name")
    @DynamoDBAttribute(attributeName = "name")
    public String getName() {
        return _name;
    }

    public void setName(final String _name) {
        this._name = _name;
    }
    @DynamoDBAttribute(attributeName = "approx_cost")
    public Double getApproxCost() {
        return _approxCost;
    }

    public void setApproxCost(final Double _approxCost) {
        this._approxCost = _approxCost;
    }
    @DynamoDBAttribute(attributeName = "creator")
    public String getCreator() {
        return _creator;
    }

    public void setCreator(final String _creator) {
        this._creator = _creator;
    }
    @DynamoDBAttribute(attributeName = "ingredients")
    public String getIngredients() {
        return _ingredients;
    }

    public void setIngredients(final String _ingredients) {
        this._ingredients = _ingredients;
    }
    @DynamoDBAttribute(attributeName = "instructions")
    public String getInstructions() {
        return _instructions;
    }

    public void setInstructions(final String _instructions) {
        this._instructions = _instructions;
    }
    @DynamoDBAttribute(attributeName = "likes")
    public Double getLikes() {
        return _likes;
    }

    public void setLikes(final Double _likes) {
        this._likes = _likes;
    }
    @DynamoDBAttribute(attributeName = "prep_time")
    public Double getPrepTime() {
        return _prepTime;
    }

    public void setPrepTime(final Double _prepTime) {
        this._prepTime = _prepTime;
    }
    @DynamoDBAttribute(attributeName = "public")
    public Boolean getPublic() {
        return _public;
    }

    public void setPublic(final Boolean _public) {
        this._public = _public;
    }
    @DynamoDBAttribute(attributeName = "serving_size")
    public Double getServingSize() {
        return _servingSize;
    }

    public void setServingSize(final Double _servingSize) {
        this._servingSize = _servingSize;
    }

}

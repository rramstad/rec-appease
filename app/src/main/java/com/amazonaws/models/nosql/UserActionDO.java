package com.amazonaws.models.nosql;

import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBAttribute;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBHashKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBIndexHashKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBIndexRangeKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBRangeKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBTable;

import java.util.List;
import java.util.Map;
import java.util.Set;

@DynamoDBTable(tableName = "recappease-mobilehub-1835379734-user_action")

public class UserActionDO {
    private String _primaryKey;
    private Boolean _favorite;
    private Double _like;
    private String _recipeName;
    private String _userName;

    @DynamoDBHashKey(attributeName = "primaryKey")
    @DynamoDBAttribute(attributeName = "primaryKey")
    public String getPrimaryKey() {
        return _primaryKey;
    }

    public void setPrimaryKey(final String _primaryKey) {
        this._primaryKey = _primaryKey;
    }
    @DynamoDBAttribute(attributeName = "favorite")
    public Boolean getFavorite() {
        return _favorite;
    }

    public void setFavorite(final Boolean _favorite) {
        this._favorite = _favorite;
    }
    @DynamoDBAttribute(attributeName = "like")
    public Double getLike() {
        return _like;
    }

    public void setLike(final Double _like) {
        this._like = _like;
    }
    @DynamoDBAttribute(attributeName = "recipe_name")
    public String getRecipeName() {
        return _recipeName;
    }

    public void setRecipeName(final String _recipeName) {
        this._recipeName = _recipeName;
    }
    @DynamoDBAttribute(attributeName = "user_name")
    public String getUserName() {
        return _userName;
    }

    public void setUserName(final String _userName) {
        this._userName = _userName;
    }

}

package recappease.org.rec_appease.Util;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Iterator;

import recappease.org.rec_appease.R;
import recappease.org.rec_appease.Util.FoodItem;

/**
 * Created by Ramstadr6 on 3/5/2018.
 */

public class FoodListAdapter extends ArrayAdapter {
    private final Activity context;
    private final ArrayList<FoodItem> foodList;

    public FoodListAdapter(Activity context, ArrayList<FoodItem> foodList) {
        super(context, R.layout.layout_listview, foodList);
        this.context = context;
        this.foodList = foodList;
    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.layout_listview, null,true);

        //this code gets references to objects in the listview_row.xml file
        TextView foodField = (TextView) rowView.findViewById(R.id.foodItemTextView);
        //this code sets the values of the objects to values from the arrays
        foodField.setText(foodList.get(position).quantity + " " + foodList.get(position).unit + " " + foodList.get(position).name);

        return rowView;
    };

    private static ArrayList<String> getFoodNameList(ArrayList<FoodItem> foodList) {
        ArrayList<String> foodNameList = new ArrayList<String>(30);
        Iterator<FoodItem> iterator = foodList.iterator();
        while (iterator.hasNext()) {
            FoodItem next = iterator.next();
            foodNameList.add(next.name);
        }
        return foodNameList;
    }
}

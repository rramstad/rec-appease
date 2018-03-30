package recappease.org.rec_appease.Util;

import android.app.Activity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import recappease.org.rec_appease.R;

/**
 * Created by Ramstadr6 on 3/5/2018.
 */

public class FoodListAdapter extends ArrayAdapter {
    private final Activity context;
    private final ArrayList<FoodItem> foodList;
    private final fragmentID fID;
    private final FileParser fileParser;

    public enum fragmentID {
        GROCERY, INVENTORY;
    }

    public FoodListAdapter(Activity context, ArrayList<FoodItem> foodList, fragmentID fID, FileParser fileParser) {
        super(context, R.layout.layout_listview, foodList);
        this.context = context;
        this.foodList = foodList;
        this.fID = fID;
        this.fileParser = fileParser;
    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.layout_listview, null,true);

        //this code gets references to objects in the listview_row.xml file
        //TextView foodField = (TextView) rowView.findViewById(R.id.foodItemTextView);

        TextView foodText = (TextView)rowView.findViewById(R.id.food);
        ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter.createFromResource(getContext().getApplicationContext(), R.array.units, android.R.layout.simple_spinner_dropdown_item);

        ImageButton dlt_btn = (ImageButton)rowView.findViewById(R.id.delete_button);
        Button checkout_btn = (Button)rowView.findViewById(R.id.checkout_button);
        //this code sets the values of the objects to values from the arrays
        foodText.setText(foodList.get(position).quantity + " " + foodList.get(position).unit + " " + foodList.get(position).name);

        //foodunit.setSelection(spinnerAdapter.getPosition(foodList.get(position).unit));

        final int finalposition = position;

        if (fID == fragmentID.GROCERY) {
            dlt_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    foodList.remove(finalposition);
                    fileParser.writeGroceryFile(foodList);
                    Toast.makeText(getContext().getApplicationContext(), (CharSequence)"Item Deleted From Grocery List.", Toast.LENGTH_SHORT);
                    notifyDataSetChanged();
                }
            });

            checkout_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    fileParser.checkOutGrocery(foodList, finalposition);
                    Toast.makeText(getContext().getApplicationContext(), (CharSequence)"Item Sent To Inventory List.", Toast.LENGTH_SHORT);
                    notifyDataSetChanged();
                }
            });
        }
        else if (fID == fragmentID.INVENTORY) {
            dlt_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    foodList.remove(finalposition);
                    fileParser.writeInventoryFile(foodList);
                    Toast.makeText(getContext().getApplicationContext(), (CharSequence)"Item Deleted From Inventory List.", Toast.LENGTH_SHORT);
                    notifyDataSetChanged();
                }
            });

            checkout_btn.setVisibility(View.GONE);
        }
        return rowView;
    };

}

package recappease.org.rec_appease.Grocery;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Iterator;

import recappease.org.rec_appease.R;
import recappease.org.rec_appease.Util.FileParser;
import recappease.org.rec_appease.Util.FoodItem;
import recappease.org.rec_appease.Util.FoodListAdapter;
import recappease.org.rec_appease.Util.InputFilterMinMax;


public class GroceryFragment extends Fragment {
    private EditText text_box;
    private EditText text_qty;
    private Spinner spn_unit;
    private Button btn;
    private Button btn_del;
    private Button btn_checkout;
    ListView list;
    private ArrayList<FoodItem> item_list;
    private FoodListAdapter adapter;
    private ArrayAdapter<CharSequence> adapter2;
    public static final int ACTIVITY_NUM = 1;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_grocery, container, false);
        //testing listview
        // String[] test = {"Apple", "Banana", "Chicken"};

        text_box = (EditText)view.findViewById(R.id.item_name);
        text_qty = (EditText)view.findViewById(R.id.item_qty);
        text_qty.setFilters(new InputFilter[]{new InputFilterMinMax("0", "1000")});
        spn_unit = (Spinner)view.findViewById(R.id.item_unit);
        btn = (Button)view.findViewById(R.id.add_button);
        btn_del = (Button)view.findViewById(R.id.del_button);
        btn_checkout = (Button)view.findViewById(R.id.checkout_button);
        list = (ListView) view.findViewById(R.id.grocery_list);
        item_list = new ArrayList<FoodItem>();
        final FileParser fileParser = new FileParser(this.getContext());
        final ArrayList<FoodItem> groceryItems = fileParser.readGroceryFile();
        Iterator<FoodItem> iterator = groceryItems.iterator();
        while(iterator.hasNext()) {
            item_list.add(iterator.next());
        }

        adapter = new FoodListAdapter(getActivity(), groceryItems);
        list.setAdapter(adapter);
        adapter2 = ArrayAdapter.createFromResource(getContext(), R.array.units, android.R.layout.simple_spinner_dropdown_item);
        spn_unit.setAdapter(adapter2);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String foodname = text_box.getText().toString();
                int foodqty;
                try {
                    foodqty = Integer.parseInt(text_qty.getText().toString());
                } catch (NullPointerException e) {
                    return;
                }
                String foodunit = spn_unit.getSelectedItem().toString();
                if (foodunit.equals("-none-")) {
                    foodunit = "";
                }
                // this line adds the data of your EditText and puts in your array
                FoodItem newfood = new FoodItem(foodname, foodqty, foodunit);
                item_list.add(newfood);
                //String item_Name = text_box.getText().toString();
                //String quantity = text_qty.getText().toString();
                //Integer qty = Integer.parseInt(quantity);
                groceryItems.add(newfood);
                fileParser.writeGroceryFile(groceryItems);
                text_box.setText("");
                text_qty.setText("");
                spn_unit.setSelected(false);
                // next thing you have to do is check if your adapter has changed
                adapter.notifyDataSetChanged();
                adapter2.notifyDataSetChanged();
            }
        });

        btn_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // this line adds the data of your EditText and puts in your array
                //text_box.setText("");
                item_list.clear();
                groceryItems.clear();
                fileParser.writeGroceryFile(groceryItems);
                //text_box.setText("");
                // next thing you have to do is check if your adapter has changed
                adapter.notifyDataSetChanged();
            }
        });

        btn_checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // this line adds the data of your EditText and puts in your array
                //text_box.setText("");
                fileParser.checkOutGrocery();
                item_list.clear();
                groceryItems.clear();
                Toast.makeText(getContext(), (CharSequence)"Items were sent to inventory", Toast.LENGTH_SHORT);
                //text_box.setText("");
                // next thing you have to do is check if your adapter has changed
                adapter.notifyDataSetChanged();
            }
        });

        return view;
    }
}

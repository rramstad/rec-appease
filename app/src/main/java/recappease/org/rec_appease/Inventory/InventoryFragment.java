package recappease.org.rec_appease.Inventory;

import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Iterator;

import recappease.org.rec_appease.Util.BottomNavigationViewHelper;
import recappease.org.rec_appease.R;
import recappease.org.rec_appease.Util.FileParser;
import recappease.org.rec_appease.Util.FoodItem;
import recappease.org.rec_appease.Util.FoodListAdapter;

public class InventoryFragment extends Fragment {
    private EditText text_box;
    private EditText text_qty;
    private Spinner spn_unit;
    private Button btn_add;
    private Button btn_del;
    ListView list;
    private ArrayList<FoodItem> item_list;
    private FoodListAdapter adapter;
    private ArrayAdapter<CharSequence> adapter2;
    public static final int ACTIVITY_NUM = 1;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_inventory, container, false);
        //testing listview
        // String[] test = {"Apple", "Banana", "Chicken"};

        text_box = (EditText)view.findViewById(R.id.item_name) ;
        text_qty = (EditText)view.findViewById(R.id.item_qty);
        spn_unit = (Spinner)view.findViewById(R.id.item_unit);
        btn_add = (Button)view.findViewById(R.id.add_button);
        btn_del = (Button)view.findViewById(R.id.del_button);
        list = (ListView) view.findViewById(R.id.inventory_list);
        item_list = new ArrayList<FoodItem>();
        final FileParser fileParser = new FileParser(getContext());
        final ArrayList<FoodItem> inventoryItems = fileParser.readInventoryFile();
        Iterator<FoodItem> iterator = inventoryItems.iterator();
        while(iterator.hasNext()) {
            item_list.add(iterator.next());
        }
        adapter = new FoodListAdapter(getActivity(), inventoryItems);
        list.setAdapter(adapter);
        adapter2 = ArrayAdapter.createFromResource(getContext(), R.array.units, android.R.layout.simple_spinner_dropdown_item);
        spn_unit.setAdapter(adapter2);

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // this line adds the data of your EditText and puts in your array
                String foodname = text_box.getText().toString();
                int foodqty = Integer.parseInt(text_qty.getText().toString());
                String foodunit = spn_unit.getSelectedItem().toString();
                if (foodunit.equals("-none-")) {
                    foodunit = "";
                }
                FoodItem newfood = new FoodItem(foodname, foodqty, foodunit);
                item_list.add(newfood);
                //String item_Name = text_box.getText().toString();
                //String quantity = text_qty.getText().toString();
                //Integer qty = Integer.parseInt(quantity);
                inventoryItems.add(new FoodItem(foodname, foodqty, foodunit));
                fileParser.writeInventoryFile(inventoryItems);
                text_box.setText("");
                // next thing you have to do is check if your adapter has changed
                adapter.notifyDataSetChanged();
            }
        });

        btn_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // this line adds the data of your EditText and puts in your array
                //text_box.setText("");
                item_list.clear();
                inventoryItems.clear();
                fileParser.writeInventoryFile(inventoryItems);
                //text_box.setText("");
                // next thing you have to do is check if your adapter has changed
                adapter.notifyDataSetChanged();
            }
        });

        return view;
    }
}

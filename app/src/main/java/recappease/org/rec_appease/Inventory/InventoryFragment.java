package recappease.org.rec_appease.Inventory;

import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Iterator;

import recappease.org.rec_appease.Util.BottomNavigationViewHelper;
import recappease.org.rec_appease.R;
import recappease.org.rec_appease.Util.FileParser;
import recappease.org.rec_appease.Util.FoodItem;
import recappease.org.rec_appease.Util.FoodListAdapter;
import recappease.org.rec_appease.Util.InputFilterMinMax;

public class InventoryFragment extends Fragment {
    private EditText text_box;
    private EditText text_qty;
    private Spinner spn_unit;
    private ImageButton btn;
    private Button save_btn;
    ListView list;
    private static FoodListAdapter adapter;
    private static ArrayAdapter<CharSequence> adapter2;
    private static FileParser fileParser;
    private static ArrayList<FoodItem> inventoryItems;
    public static final int ACTIVITY_NUM = 1;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_inventory, container, false);
        //testing listview
        // String[] test = {"Apple", "Banana", "Chicken"};

        text_box = (EditText)view.findViewById(R.id.item_name);
        text_qty = (EditText)view.findViewById(R.id.item_qty);
        text_qty.setFilters(new InputFilter[]{new InputFilterMinMax("0", "1000")});
        spn_unit = (Spinner)view.findViewById(R.id.item_unit);
        btn = (ImageButton)view.findViewById(R.id.add_button);
        save_btn = (Button)view.findViewById(R.id.save_button);
        list = (ListView) view.findViewById(R.id.inventory_list);
        list.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        fileParser = new FileParser(this.getContext());
        inventoryItems = fileParser.readInventoryFile();
        adapter = new FoodListAdapter(getActivity(), inventoryItems, FoodListAdapter.fragmentID.INVENTORY, fileParser);
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
                //String item_Name = text_box.getText().toString();
                //String quantity = text_qty.getText().toString();
                //Integer qty = Integer.parseInt(quantity);
                inventoryItems.add(newfood);
                fileParser.writeGroceryFile(inventoryItems);
                text_box.setText("");
                text_qty.setText("");
                spn_unit.setSelection(0);

                Toast.makeText(getContext(), (CharSequence)"Added To Grocery List.", Toast.LENGTH_SHORT);
                // next thing you have to do is check if your adapter has changed
                adapter.notifyDataSetChanged();
                adapter2.notifyDataSetChanged();
            }
        });

        save_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fileParser.writeInventoryFile(inventoryItems);

                Toast.makeText(getContext(), (CharSequence)"Inventory List Saved.", Toast.LENGTH_SHORT);

                adapter.notifyDataSetChanged();
                adapter2.notifyDataSetChanged();
            }
        });

        return view;
    }

    public static void updateList() {
        inventoryItems = fileParser.readInventoryFile();
        adapter.notifyDataSetChanged();
    }
}

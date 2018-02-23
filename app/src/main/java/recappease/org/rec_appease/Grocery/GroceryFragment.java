package recappease.org.rec_appease.Grocery;

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

import java.util.ArrayList;

import recappease.org.rec_appease.Util.BottomNavigationViewHelper;
import recappease.org.rec_appease.R;


public class GroceryFragment extends Fragment {
    private EditText text_box;
    private EditText text_qty;
    private Button btn;
    private Button btn_del;
    ListView list;
    private ArrayList<String> item_list;
    ArrayAdapter<String> adapter;
    public static final int ACTIVITY_NUM = 1;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_grocery, container, false);
        //testing listview
        // String[] test = {"Apple", "Banana", "Chicken"};

        text_box = (EditText)view.findViewById(R.id.item_name) ;
        text_qty = (EditText)view.findViewById(R.id.item_qty);
        btn = (Button)view.findViewById(R.id.add_button);
        btn_del = (Button)view.findViewById(R.id.del_button);
        list = (ListView) view.findViewById(R.id.grocery_list);
        item_list = new ArrayList<String>();
        adapter = new ArrayAdapter<String>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                item_list
        );


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // this line adds the data of your EditText and puts in your array
                item_list.add(text_box.getText().toString());
                //String item_Name = text_box.getText().toString();
                //String quantity = text_qty.getText().toString();
                //Integer qty = Integer.parseInt(quantity);

                text_box.setText("");
                // next thing you have to do is check if your adapter has changed
                adapter.notifyDataSetChanged();
            }
        });

        list.setAdapter(adapter);

        btn_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // this line adds the data of your EditText and puts in your array
                //text_box.setText("");
                item_list.clear();
                //text_box.setText("");
                // next thing you have to do is check if your adapter has changed
                adapter.notifyDataSetChanged();
            }
        });


        return view;
    }
}

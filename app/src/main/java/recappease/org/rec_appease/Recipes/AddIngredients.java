package recappease.org.rec_appease.Recipes;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import recappease.org.rec_appease.R;
import java.util.ArrayList;

/**
 * Created by jungr on 4/10/18.
 */



public class AddIngredients extends Activity implements View.OnClickListener {
    ArrayList<String> addArray = new ArrayList<String>();
    Button save;
    EditText txt;
    ListView show;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_add_ingredients);

        save = (Button) findViewById(R.id.save);
        txt = (EditText) findViewById(R.id.ingredientsList);
        show = (ListView) findViewById(R.id.list);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String getinput = txt.getText().toString();
                if (addArray.contains(getinput)) {

                }
                else {
                    addArray.add(getinput);
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(AddIngredients.this, android.R.layout.simple_list_item_1, addArray);
                    show.setAdapter(adapter);
                    ((EditText) findViewById(R.id.ingredientsList)).setText(" ");

                }
            }
        });
    }

    @Override
    public void onClick(View view) {

    }
}

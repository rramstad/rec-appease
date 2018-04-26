package recappease.org.rec_appease.Recipes;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

import recappease.org.rec_appease.R;

/**
 * Created by jungr on 4/10/18.
 */

public class AddTags extends Activity implements View.OnClickListener {
    ArrayList<String> addArray = new ArrayList<String>();
    Button save;
    EditText txt;
    ListView show;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_add_tag);

        save = (Button) findViewById(R.id.save);
        txt = (EditText) findViewById(R.id.tags);
        show = (ListView) findViewById(R.id.list);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String getinput = txt.getText().toString();
                if (addArray.contains(getinput)) {

                }
                else {
                    addArray.add(getinput);
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(AddTags.this, android.R.layout.simple_list_item_1, addArray);
                    show.setAdapter(adapter);
                    ((EditText) findViewById(R.id.tags)).setText(" ");

                }
            }
        });
    }

    @Override
    public void onClick(View view) {

    }
}

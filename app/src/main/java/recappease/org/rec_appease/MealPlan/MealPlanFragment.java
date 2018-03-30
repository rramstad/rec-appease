package recappease.org.rec_appease.MealPlan;

import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
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
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;

import recappease.org.rec_appease.Util.BottomNavigationViewHelper;
import recappease.org.rec_appease.R;

import static android.app.Activity.RESULT_OK;

public class MealPlanFragment extends Fragment implements View.OnClickListener{
    public static final int ACTIVITY_NUM = 3;

    private static final int RESULT_LOAD_IMAGE = 0;
    ImageView imageToUpload;
    EditText uploadImageName;
    Button bUploadImage;

    Button save;
    ArrayList<String> addArray = new ArrayList<String>();
    EditText txt;
    ListView show;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        //View view = inflater.inflate(R.layout.recipe_profile, container, false);


        /*imageToUpload = (ImageView) view.findViewById(R.id.change_picture);
        bUploadImage = (Button) view.findViewById(R.id.change_picture_button);

        imageToUpload.setOnClickListener(this);
        bUploadImage.setOnClickListener(this);*/

        /*
        BottomNavigationView bottomNavigationView = (BottomNavigationView)  findViewById(R.id.bottom_navigation);
        BottomNavigationViewHelper.enableNavigation(this, bottomNavigationView);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(ACTIVITY_NUM);
        menuItem.setChecked(true);
        */
        final View root = inflater.inflate(R.layout.fragment_add_tag, container, false);

        txt = (EditText) root.findViewById(R.id.tags);
        show = (ListView) root.findViewById(R.id.list);
        save = (Button) root.findViewById(R.id.save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String getinput = txt.getText().toString();
                if (addArray.contains(getinput)) {

                }
                else {
                    addArray.add(getinput);
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, addArray);
                    show.setAdapter(adapter);
                    ((EditText) root.findViewById(R.id.tags)).setText(" ");
                }
            }
        });
        //return view;
        return root;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.change_picture:
                Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent, RESULT_LOAD_IMAGE);
                break;
            case R.id.change_picture_button:
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && data != null) {
            Uri selectedImage = data.getData();
            imageToUpload.setImageURI(selectedImage);
        }
    }
}

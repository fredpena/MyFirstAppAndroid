package com.pucmm.app.example;

import android.app.ListActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.pucmm.app.R;

import java.util.Arrays;

public class ListViewMainActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_list_view_main);

        String[] androidOS = new String[]{"Cupcake", "Donut", "Eclair", "Froyo", "Gingerbread", "Honeycomb", "Ice Cream SandWich", "Jelly Bean", "KitKat"};

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, R.layout.activity_list_view_main, R.id.text, androidOS);

        setListAdapter(arrayAdapter);

        ListView listView = getListView();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String os = ((TextView) view).getText().toString();

                Snackbar.make(view, ("HI: " + os), Snackbar.LENGTH_SHORT).show();
            }
        });
    }
}
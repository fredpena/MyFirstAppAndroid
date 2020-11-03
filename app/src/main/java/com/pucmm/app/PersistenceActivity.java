package com.pucmm.app;

import android.view.View;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class PersistenceActivity extends AppCompatActivity {


    private EditText name;
    private EditText email;
    private SharedPreferencesStore preferencesStore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_persistence);

        name = findViewById(R.id.name);
        email = findViewById(R.id.email);

        preferencesStore = new SharedPreferencesStore(this);
    }

    public void save(View view) {
        preferencesStore.put("name", name.getText().toString());
        preferencesStore.put("email", email.getText().toString());
    }

    public void show(View view) {
        String _name = preferencesStore.getString("name");
        String _email = preferencesStore.getString("email");

        name.setText(_name);
        email.setText(_email);
    }

    public void clean(View view) {
        name.setText(null);
        email.setText(null);
    }

    public void delete(View view) {
        preferencesStore.remove("name");
        preferencesStore.remove("email");

        show(view);
    }

}
package com.pucmm.app;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.fragment.app.DialogFragment;

import java.util.Date;
import java.util.List;


public class MainActivity extends AppCompatActivity /*implements View.OnClickListener*/ {

    public static final String EXTRA_MESSAGE = "com.pucmm.app.MESSAGE";


    private EditText name;
    private EditText lastName;
    private Spinner gender;
    private TextView dateOfBirth;
    private RadioGroup rgLikePro;

    private CheckBoxUtils boxUtils;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.name);
        lastName = findViewById(R.id.lastName);

        gender = findViewById(R.id.gender);
        ArrayAdapter<String> genderAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, Utils.getGenders());
        genderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        gender.setAdapter(genderAdapter);

        dateOfBirth = findViewById(R.id.dateOfBirth);
        dateOfBirth.setText(Utils.format(this, new Date()));

        rgLikePro = findViewById(R.id.rgLikePro);
        rgLikePro.check(R.id.rbYes);

        boxUtils = new CheckBoxUtils(this);

//ArraysUtils
    }

    //METHOD 1
    public void sendMessage(View view) {
//        Intent intent = new Intent();
//        intent.setAction(Intent.ACTION_SEND);
//        intent.setType("text/plain");
//        intent.putExtra("DATA", "HOLA");
//        startActivity(intent);
        //Toast.makeText(this, "Hello MainActivity!", Toast.LENGTH_SHORT).show();
        boxUtils.checkSelected();
        final List<String> list = boxUtils.getLanguajes();

        if (TextUtils.isEmpty(name.getText())) {
            Toast.makeText(this, "El nombre es requerido!", Toast.LENGTH_SHORT).show();
            name.setError("El nombre es requerido!");
            name.setHint("Por favor " + getResources().getString(R.string.name).toLowerCase());
        } else if (TextUtils.isEmpty(lastName.getText())) {
            Toast.makeText(this, "El apellido es requerido!", Toast.LENGTH_SHORT).show();
            lastName.setError("El apellido es requerido!");
            lastName.setHint("Por favor " + getResources().getString(R.string.lastName).toLowerCase());
        } else if (rgLikePro.getCheckedRadioButtonId() == R.id.rbYes && list.isEmpty()) {
            Toast.makeText(this, "Dede seleccionar al menos un lenguaje!", Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent(getApplicationContext(), DisplayMessageActivity.class);
            String message = getMessage();
            intent.putExtra(EXTRA_MESSAGE, message);
            startActivity(intent);
        }

        //Intent intent = new Intent(this, DisplayMessageActivity.class);
        // intent.putExtra(EXTRA_MESSAGE, message);
        //startActivity(intent);
    }

    private String getMessage() {
        final String _name = name.getText().toString();
        final String _lastName = lastName.getText().toString();
        final String _gender = gender.getSelectedItem().toString();
        final String _dateOfBirth = dateOfBirth.getText().toString();
        final String likeProg;
        final String languajes;

        if (rgLikePro.getCheckedRadioButtonId() == R.id.rbYes) {
            likeProg = "Me gusta programar";
            final List<String> list = boxUtils.getLanguajes();
            if (list.size() == 1) {
                languajes = "Mi lenguaje favorito es: " + list.stream().findFirst().get();
            } else {
                languajes = "Mis lenguajes favoritos son: " + Utils.valueFromArray(list);
            }


        } else {
            likeProg = "No me gusta programar";
            languajes = "";
        }

        return String.format("Hola!, mi nombre es: %s %s. \n\nSoy %s, y naci en fecha %s. \n\n " +
                        "%s. %s."
                , _name, _lastName, _gender, _dateOfBirth, likeProg, languajes);
    }

    public void showDatePickerDialog(View view) {
        DialogFragment dialogFragment = new DatePickerFragment(dateOfBirth);
        dialogFragment.show(getSupportFragmentManager(), "datePicker");
    }

    public void clean(View view) {
        name.setText("");
        lastName.setText("");
        gender.setSelection(0);
        dateOfBirth.setText(Utils.format(this, new Date()));
        rgLikePro.check(R.id.rbYes);
        boxUtils.checked(false);
    }

    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        switch (view.getId()) {
            case R.id.rbYes:
                if (checked)
                    boxUtils.enabled(true);
                break;
            case R.id.rbNo:
                if (checked)
                    boxUtils.enabled(false);
                break;
        }
    }
//METHOD 2: Elimine la llamada del metodo sendMessage() en la propiedad onClick del boton
//        Button button = findViewById(R.id.button);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //Toast.makeText(MainActivity.this, "Hello MainActivity!", Toast.LENGTH_SHORT).show();
//
//                Intent intent = new Intent(MainActivity.this, DisplayMessageActivity.class);
//                EditText editText = findViewById(R.id.editText);
//                String message = editText.getText().toString();
//                intent.putExtra(EXTRA_MESSAGE, message);
//                startActivity(intent);
//            }
//        });
//
//
// METHOD 3: implements View.OnClickListener en la clase
//        Button button = findViewById(R.id.button);
//        button.setOnClickListener(this);
//
//
//    @Override
//    public void onClick(View v) {
//        //Toast.makeText(MainActivity.this, "Hello MainActivity!", Toast.LENGTH_SHORT).show();
//
//        Intent intent = new Intent(MainActivity.this, DisplayMessageActivity.class);
//        EditText editText = findViewById(R.id.editText);
//        String message = editText.getText().toString();
//        intent.putExtra(EXTRA_MESSAGE, message);
//        startActivity(intent);
//    }
}
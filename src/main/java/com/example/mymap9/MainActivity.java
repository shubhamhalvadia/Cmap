package com.example.mymap9;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.mymap9.R;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
        Button bt;
        Spinner spinner, spinner2;
        int location1;
    int location2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spinner = findViewById(R.id.spinner);
        spinner2 = findViewById(R.id.spinner2);
        Button bt=findViewById(R.id.button);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.locations, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter);
        spinner2.setOnItemSelectedListener(this);

        final String[] latitudes = getResources().getStringArray(R.array.latitudes);
        final String[] longitudes = getResources().getStringArray(R.array.longitudes);

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(location1==location2){
                    String text = "Both location cannot be same";
                    Toast.makeText(MainActivity.this, text, Toast.LENGTH_SHORT).show();
                }
                else{
                    try {
                        Uri uri = Uri.parse("https://www.google.com/maps/dir/" + latitudes[location1]+","+longitudes[location1] + "/" + latitudes[location2]+","+longitudes[location2]+"/data=!3m1!4b1!4m2!4m1!3e2");
                        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                    }catch (ActivityNotFoundException e){
                        Toast.makeText(MainActivity.this, "Install Google Maps", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        Spinner spin = (Spinner)parent;
        Spinner spin2 = (Spinner)parent;
        if(spin.getId() == R.id.spinner)
        {
            location1 = position;
        }
        if(spin2.getId() == R.id.spinner2)
        {
            location2 = position;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}

package com.example.coloredcounter;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private Integer counter;
    private String color;
    private SharedPreferences sharedPref;
    private SharedPreferences.Editor prefEditor;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        counter = 0;

        sharedPref = this.getSharedPreferences("com.example.coloredcount.PREFERENCE_FILE", Context.MODE_PRIVATE);

        TextView tvCounter = findViewById(R.id.tvCounter);
        Button btnClick = findViewById(R.id.btnClick);
        Button btnBlue = findViewById(R.id.btnBlue);
        Button btnRed = findViewById(R.id.btnRed);
        Button btnPurple = findViewById(R.id.btnPurple);
        Button btnGreen = findViewById(R.id.btnGreen);

        prefEditor = sharedPref.edit();

        counter = sharedPref.getInt("counter",counter);
        color = sharedPref.getString("color","#AFABAB");


        tvCounter.setBackgroundColor(Color.parseColor(color));
        tvCounter.setText(counter.toString());


        btnClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                counter++;
                tvCounter.setText(counter.toString());
            }
        });



        btnGreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                color = "#17C50B";
                tvCounter.setBackgroundColor(Color.parseColor(color));

            }
        });
        btnPurple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                color = "#520BC5";
                tvCounter.setBackgroundColor(Color.parseColor(color));
            }
        });

        btnRed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                color = "#C50B11";
                tvCounter.setBackgroundColor(Color.parseColor(color));
            }
        });
        btnBlue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                color = "#0B50C5";
                tvCounter.setBackgroundColor(Color.parseColor(color));
            }
        });




    }

    @Override
    protected void onPause() {
        super.onPause();
        prefEditor.putString("color",color);
        prefEditor.putInt("counter",counter);
        prefEditor.apply();
    }


}
package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements MainView {
    MainPresenter presenter;
    private EditText cityname;
    private TextView weatherdata;
    private Button data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    @Override
    public String getcityname() {
        cityname=findViewById(R.id.city);
        String text=cityname.getText().toString().trim();
        return text;
    }
    @Override
    public void onButtonClicked() {
        data=findViewById(R.id.bn);
        data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayText();
            }
        });
    }
    @Override
    public void displayText()
    {
        presenter.display();
    }
    @Override
    public void updatedText(String string){
        weatherdata=findViewById(R.id.textView);
        weatherdata.setText(string);

    }
}





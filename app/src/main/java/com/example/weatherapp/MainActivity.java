package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    public static String BaseUrl = "http://api.openweathermap.org/";
    public static String API="04d51d94b6603269028898f672008f50";
    private TextView weatherdata;
    private EditText cityname;
    private Button data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        data=findViewById(R.id.bn);
        data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getcurrentdata();
            }
        });
    }
    public void getcurrentdata(){
        Retrofit retrofit=new Retrofit.Builder().baseUrl(BaseUrl).addConverterFactory(GsonConverterFactory.create()).build();
        weatherApi weather=retrofit.create(weatherApi.class);
        String text=cityname.getText().toString().trim();
        Call<WeatherResponse> call=weather.getCurrentWeatherData(text,API);
        call.enqueue(new Callback<WeatherResponse>() {
            @Override
            public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {
                if(response.code()==200) {

                    WeatherResponse weatherResponse = response.body();
                    assert weatherResponse != null;

                    String stringBuilder = "Country: " +
                            weatherResponse.sys.country +
                            "\n" +
                            "Temperature: " +
                            weatherResponse.main.temp +
                            "\n" +
                            "Temperature(Min): " +
                            weatherResponse.main.temp_min +
                            "\n" +
                            "Temperature(Max): " +
                            weatherResponse.main.temp_max +
                            "\n" +
                            "Humidity: " +
                            weatherResponse.main.humidity +
                            "\n" +
                            "Pressure: " +
                            weatherResponse.main.pressure;

                    weatherdata.setText(stringBuilder);

                }
                else {
                    weatherdata.setText(response.message());
                }
            }
            @Override
            public void onFailure(Call<WeatherResponse> call, Throwable t) {
                weatherdata.setText(t.getMessage());
            }
        });
    }

}



package com.example.weatherapp;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainPresenter {
    MainView mainView;
    public static String BaseUrl = "http://api.openweathermap.org/";
    public static String API = "04d51d94b6603269028898f672008f50";

    public void display() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BaseUrl).addConverterFactory(GsonConverterFactory.create()).build();
        weatherApi weather = retrofit.create(weatherApi.class);
        //  String text=cityname.getText().toString().trim();
        Call<WeatherResponse> call = weather.getCurrentWeatherData(mainView.getCityName(), API);
        call.enqueue(new Callback<WeatherResponse>() {
            @Override
            public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {
                if (response.code() == 200) {

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

                    mainView.updatedText(stringBuilder);

                } else {
                    mainView.updatedText(response.message());
                }
            }

            @Override
            public void onFailure(Call<WeatherResponse> call, Throwable t) {
                mainView.updatedText(t.getMessage());

            }
        });
    }
}


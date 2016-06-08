package com.yudha.user.mycuaca;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.yudha.user.mycuaca.HandleJSON;
import com.yudha.user.mycuaca.R;

public class MainActivity extends Activity {
    private String url1 = "http://api.openweathermap.org/data/2.5/weather?q=";
    private EditText location, country, humidity, pressure,temperature;
    private ImageView gambar;
    private HandleJSON obj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout);
        location = (EditText)findViewById(R.id.lokasi);
        country = (EditText)findViewById(R.id.negara);
        temperature = (EditText)findViewById(R.id.suhu);
        humidity = (EditText)findViewById(R.id.kelembapan);
        pressure = (EditText)findViewById(R.id.tekanan);
        gambar = (ImageView) findViewById(R.id.gambarCuaca);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void open(View view){
        String url = location.getText().toString();
        String finalUrl = url1+url;
        country.setText(finalUrl);
        obj = new HandleJSON(finalUrl);
        obj.fetchJSON();
        while(obj.parsingComplete);
        country.setText(obj.getCountry());
        temperature.setText(String.valueOf(obj.getTemperature()+"°C"));
        humidity.setText(obj.getHumidity()+" %");
        pressure.setText(obj.getPressure()+" hPa");
        Imageicon(obj.getIcon());
    }
    public static String getTime()
    {
        SimpleDateFormat sdf1 = new SimpleDateFormat("d/M/yyyy h:m:s a");
        Calendar calendar = Calendar.getInstance();
        String strdate1 = sdf1.format(calendar.getTime());
        return strdate1;
    }
    private void Imageicon (String image){
        if(image.equals("01d")){
            gambar.setImageResource(R.drawable.clear);
        }
        else if (image.equals("01n")){
            gambar.setImageResource(R.drawable.ntclear);
        }
        else if (image.equals("02d")){
            gambar.setImageResource(R.drawable.mostlysunny);
        }
        else if (image.equals("02n")){
            gambar.setImageResource(R.drawable.ntmostlycloudy);
        }
        else if (image.equals("03d") ||image.equals("03n") ){
            gambar.setImageResource(R.drawable.cloudy);
        }
        else if (image.equals("04d") || image.equals("04n") ){
            gambar.setImageResource(R.drawable.fog);
        }
        else if (image.equals("09d") || image.equals("10d") || image.equals("09n") || image.equals("10n")){
            gambar.setImageResource(R.drawable.chancerain);
        }
        else if (image.equals("11d") || image.equals("11n")){
            gambar.setImageResource(R.drawable.chancetstorms);
        }
        else if (image.equals("13d") || image.equals("13n")){
            gambar.setImageResource(R.drawable.chanceflurries);
        }
    }
}

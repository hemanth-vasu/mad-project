package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    EditText edit;
    TextView text,text1,text2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edit=findViewById(R.id.edit);
        text=findViewById(R.id.text);
        text1=findViewById(R.id.text1);
        text2=findViewById(R.id.text2);

    }
    public void get(View v)
    {
        String apikey="6d97d6ae35d2acd3fe64bd455da3a091";
        String cityname=edit.getText().toString();
        String url="https://api.openweathermap.org/data/2.5/weather?q="+cityname+"&appid=6d97d6ae35d2acd3fe64bd455da3a091";
        RequestQueue queue= Volley.newRequestQueue(getApplicationContext());
        JsonObjectRequest request=new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONObject object = response.getJSONObject("main");
                    String temperature = object.getString("temp");
                    String humidity = object.getString("humidity");
                    String pressure = object.getString("pressure");
                    Double temp=Double.parseDouble(temperature)-273.15;
                    text.setText("Temperature is "+temp.toString().substring(0,2)+" degree C");
                    text1.setText("Humidity is "+humidity.toString().substring(0,2)+"%");
                    text2.setText("Pressure is "+pressure.toString().substring(0,2)+" hPa");
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();
                }

            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this,"please enter valid city name",Toast.LENGTH_SHORT).show();
            }

    });
        queue.add(request);

    }
}
package com.example.jsonws;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void traerDatos(View view) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String URI = "https://jsonplaceholder.typicode.com/posts";

        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.GET,
                URI,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        TextView tvData = findViewById(R.id.tvData);
                        tvData.setText("");
                        JSONArray  data = null;
                        try {
                            data = response.getJSONArray("data");
                            for(int i = 0; i<=data.length(); i++){
                                JSONObject post = data.getJSONObject(i);
                                tvData.append(post.length()+"\n");
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("WebService", error.toString());
                    }
                }
        );
        requestQueue.add(request);
    }
}
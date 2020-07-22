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
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Array;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    TextView tvDatos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void traerDatos(View view) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String URI1 = "https://jsonplaceholder.typicode.com/posts";
        String URI2 = "https://gateway.marvel.com/v1/public/characters?name=thor&apikey=5c465051a788252db815c7bd342d97a1&ts=1&hash=3e46134f8ed0002366b015568a3f320c";

        /*
        JsonArrayRequest requestArray = new JsonArrayRequest(
                Request.Method.GET, URI1, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        tvDatos = findViewById(R.id.tvData);
                        tvDatos.setText("");
                        for(int i=0; i<response.length(); i++) {
                            try {
                                JSONObject post = (JSONObject) response.get(i);
                                tvDatos.append(
                                        "userId:"+post.getString("userId")+"\n"+
                                        "id:"+post.getString("id")+"\n"+
                                        "title:"+post.getString("title")+"\n"+
                                        "body:"+post.getString("body")+"\n\n"
                                );
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("ErrorRequest", error.getMessage());
                    }
                }
        );
        */

        JsonObjectRequest requestObject = new JsonObjectRequest(
                Request.Method.GET, URI2, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        tvDatos = findViewById(R.id.tvData);
                        try {
                            JSONObject data = (JSONObject) response.get("data");
                            JSONArray results = data.getJSONArray("results");
                            JSONObject personaje = (JSONObject) results.get(0);
                            tvDatos.setText(
                                        "Nombre: "+ personaje.get("name")+"\n"+
                                        "Descripcion: "+personaje.get("description")
                                    );
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

        requestQueue.add(requestObject);
    }
}
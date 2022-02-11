package com.example.jsonapi;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.Filter;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.load.model.ModelCache;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements RecyclerViewClickInterface{

    private static String JSON_URL = "http://testproject1.indigierp.com/api/citylist";


    List<Modelclass> modelList;
    RecyclerView recyclerView;
    Adaptery adaptery;
    TextView filter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        modelList = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerview);
        filter = findViewById(R.id.filter);


        filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });




        jsonparse();

    }

    private void showDialog() {

        ViewGroup viewGroup = findViewById(android.R.id.content);

        Button close;

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        View view1 = LayoutInflater.from(MainActivity.this).inflate(R.layout.filter_item,viewGroup,false);
        builder.setCancelable(false);
        builder.setView(view1);


        close = view1.findViewById(R.id.close);
        AlertDialog alertDialog = builder.create();

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });
        alertDialog.show();

    }

    private void jsonparse() {
        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.POST, JSON_URL, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.e("Resp", "" + response);
                        Toast.makeText(MainActivity.this, ""+response, Toast.LENGTH_SHORT).show();
                        try {

                            JSONObject jsonObject = response.getJSONObject(0);

                            JSONArray jsonArray = jsonObject.getJSONArray("data");


                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject data = jsonArray.getJSONObject(i);

                                String id= data.getString("id");
                                String city_name= data.getString("city_name");
                                String city_code= data.getString("city_code");
                                String user_id= data.getString("user_id");
                                String created_at= data.getString("created_at");
                                String updated_at= data.getString("updated_at");

                                modelList.add(new Modelclass(id,city_name,city_code,user_id,created_at,updated_at));
                            }

                        } catch (JSONException e) {
                            Log.e("exceptions", e.getLocalizedMessage());
                            Toast.makeText(MainActivity.this, ""+e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                            e.printStackTrace();
                        }
                        adaptery =new Adaptery(MainActivity.this,modelList,MainActivity.this);
                        adaptery.notifyDataSetChanged();
                        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                        recyclerView.setAdapter(adaptery);

                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("error",error.getLocalizedMessage());
                Toast.makeText(MainActivity.this, ""+error.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

            }
        });
        requestQueue.add(jsonArrayRequest);


    }


    @Override
    public void onItemClick(int position) {
        Toast.makeText(this,String.valueOf(modelList.get(position)),Toast.LENGTH_SHORT).show();
//        Toast.makeText(this,""+modelList,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLongItemClick(int position) {

        modelList.remove(position);
        adaptery.notifyItemRemoved(position);

    }

    private void filter(String text) {
        ArrayList<Modelclass> filteredList = new ArrayList<>();

        for (Modelclass item : modelList) {
            if (item.getCity_name().toLowerCase().contains(text.toLowerCase()) ||
                    item.getCity_name().toLowerCase().contains(text.toLowerCase())
//                    || item.getSubarea().toLowerCase().contains(text.toLowerCase())

//                    || item.getDma().toLowerCase().contains(text.toLowerCase())

            ) {
                filteredList.add(item);
            }
        }

        adaptery.filterList(filteredList);
    }
}
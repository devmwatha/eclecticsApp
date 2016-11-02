package com.smartboma.development.m_bankingapp.UI;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.smartboma.development.m_bankingapp.R;

import java.util.HashMap;
import java.util.Map;

public class PlaceCheque extends AppCompatActivity {
TextView ch_number,cheque_placed;
    String acc_name;
    final String cheque_number="MBAP-1000",placed="1";
    Button place;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_cheque);
        ch_number=(TextView) findViewById(R.id.ch_number);
        cheque_placed=(TextView) findViewById(R.id.acc_ch_number);
        cheque_placed.setText("Account number "+acc_name);
        ch_number.setText("Cheque number: "+cheque_number);
        place=(Button) findViewById(R.id.place);
        place.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                placeCheque();
                finish();
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    private void placeCheque() {
        SharedPreferences sharedPreferences=getSharedPreferences("myprefs", Context.MODE_PRIVATE);
        acc_name=sharedPreferences.getString("acc_number",null);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.CHEQUE_PLACEMENTURL,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String s) {
                        Toast.makeText(PlaceCheque.this,s,Toast.LENGTH_LONG).show();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
Toast.makeText(PlaceCheque.this,volleyError.toString(),Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                //Adding parameters to request
                params.put(Config.CHEQUE_ACC_NUMBER, acc_name);
                params.put(Config.CHEQUE_NUMBER, cheque_number);
                params.put(Config.CHEQUE_PLACED, "1");
                //returning parameter
                return params;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}

package com.smartboma.development.m_bankingapp.UI;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class Deposit extends AppCompatActivity {
EditText makeDeposit;
    Button placeDeposit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deposit);

        Toolbar toolbar=(Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Make a deposit");
        makeDeposit=(EditText) findViewById(R.id.makeDeposit);
        placeDeposit=(Button) findViewById(R.id.placeDeposit);
        placeDeposit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                makeDepo();
                finish();
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void makeDepo() {
        //Creating a string request
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.DEPO_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(Deposit.this,response,Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Deposit.this,error.toString(),Toast.LENGTH_LONG).show();
                        //You can handle error here if you want
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();

                String deposit;
                SharedPreferences sharedPreferences=getSharedPreferences("myprefs", Context.MODE_PRIVATE);
                String name=sharedPreferences.getString("acc_number",null);
                deposit=makeDeposit.getText().toString();
                //Adding parameters to request
                params.put(Config.ACC_DNUMBER, name);
                params.put(Config.AMOUNT, deposit);
                //returning parameter
                return params;
            }
        };

        //Adding the string request to the queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }
}

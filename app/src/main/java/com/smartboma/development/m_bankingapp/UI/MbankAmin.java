package com.smartboma.development.m_bankingapp.UI;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class MbankAmin extends AppCompatActivity {
EditText acc_number,pin;
    Button login;
    TextView register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Toolbar toolbar=(Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setThings();
    }

    private void setThings() {
        acc_number=(EditText) findViewById(R.id.acc_num);
        pin=(EditText) findViewById(R.id.your_mpin);
        login=(Button) findViewById(R.id.login_now);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();
            }
        });
        register=(TextView) findViewById(R.id.tregister);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MbankAmin.this,Register.class));
            }
        });

    }

    private void signIn() {

       final String acc_name, mpin;
        acc_name=acc_number.getText().toString();
        mpin=pin.getText().toString();
        //Creating a string request
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.LOGIN_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response.equals("success")) {
                            Toast.makeText(MbankAmin.this, response, Toast.LENGTH_SHORT).show();
                            SharedPreferences preferences = getSharedPreferences("myprefs", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = preferences.edit();
                            editor.putString("acc_number", acc_name);
                            editor.putString("pin", mpin);
                            editor.apply();
                            startActivity(new Intent(MbankAmin.this, MainActivity.class));
                        }
                        else
                        {
                            acc_number.setError("Wrong acc number");
                            pin.setError("Wrong pin");

                            acc_number.requestFocus();
                            pin.requestFocus();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MbankAmin.this,error.toString(),Toast.LENGTH_LONG).show();
                        //You can handle error here if you want
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                //Adding parameters to request
                params.put(Config.ACC_NUMBER, acc_name);
                params.put(Config.ACC_PIN, mpin);
                //returning parameter
                return params;
            }
        };

        //Adding the string request to the queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}

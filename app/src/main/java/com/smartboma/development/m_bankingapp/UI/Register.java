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

public class Register extends AppCompatActivity {
    EditText mf_name, ml_name, maccount_number, mPIN;
    Button mReister;
    TextView login;
    String error=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Register here");
        setThingsUp();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    private void setThingsUp() {
        mf_name = (EditText) findViewById(R.id.fname);
        ml_name = (EditText) findViewById(R.id.lname);
        maccount_number = (EditText) findViewById(R.id.acc_number);
        mPIN = (EditText) findViewById(R.id.pin);
        login=(TextView) findViewById(R.id.loginhere) ;
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Register.this,MbankAmin.class));
            }
        });
        mReister = (Button) findViewById(R.id.register);
        mReister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendToserverAsJson();
            }
        });

    }

    private void sendToserverAsJson() {
        if (mf_name.getText().toString().isEmpty()) {
            mf_name.setError("Required!");
            mf_name.requestFocus();
        } else if (ml_name.getText().toString().isEmpty()) {
            ml_name.setError("Required!");
            ml_name.requestFocus();
        } else if (maccount_number.getText().toString().isEmpty()) {
            maccount_number.setError("Required!");
            maccount_number.requestFocus();
        } else if (mPIN.getText().toString().isEmpty()) {
            mPIN.setError("Required!");
            mPIN.requestFocus();
        } else if (mPIN.getText().toString().length() < 5) {
            mPIN.setError("Your PIN is too short. Make it atleast 5 characters!");
            mPIN.requestFocus();
        } else {
            //Creating a string request
            StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.REGISTER_URL,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            if(response.equals("Successfully Registered"))
                            {
                                Toast.makeText(Register.this,response,Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(Register.this,MbankAmin.class));
                            }
                            else {
                                maccount_number.setError("This account is already registered!");
                                maccount_number.requestFocus();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError verror) {
                               }
                    }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String,String> params = new HashMap<>();

                    String name, lastname, acc_no, pin;
                    name=mf_name.getText().toString();
                    lastname=ml_name.getText().toString();
                    acc_no=maccount_number.getText().toString();
                    pin=mPIN.getText().toString();
                    //Adding parameters to request
                    params.put(Config.RECISTER_FNAME, name);
                    params.put(Config.RECISTER_LNAME, lastname);
                    params.put(Config.REGISTER_PIN,pin);
                    params.put(Config.REGISTER_ACC_NUM,acc_no);

                    //returning parameter
                    return params;
                }
            };

            //Adding the string request to the queue
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);
        }
    }
}

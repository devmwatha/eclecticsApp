package com.smartboma.development.m_bankingapp.UI;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
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

public class ChangePIN extends AppCompatActivity {
EditText oldpin,newpin,confirmpin;
    Button changePin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_pin);
        Toolbar toolbar=(Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
         setTitle("Change PIN");
        oldpin = (EditText) findViewById(R.id.oldpin);
        newpin = (EditText) findViewById(R.id.newPIN);
        confirmpin = (EditText) findViewById(R.id.confirmPIN);
        changePin = (Button) findViewById(R.id.change_pinm);
        changePin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(newpin.getText().toString().length()<5)
                {
                    newpin.setError("Your PIN is too short!");
                    newpin.requestFocus();
                }
                else if(confirmpin.getText().toString().length()<5)
                {
                    confirmpin.setError("Your PIN is too short. Make it atleast 5 characters");
                    confirmpin.requestFocus();
                }
                else if(confirmpin.getText().toString().equals(newpin.getText().toString())){
                    changePIN();
                }
                else{
                    confirmpin.setError("PIN do not match!");
                }
            }
        });
    }

    private void changePIN() {
        AlertDialog.Builder builder = new AlertDialog.Builder(ChangePIN.this);
        builder.setTitle("Want to change PIN?");
        builder.setMessage("Are you sure to change the PIN?");
        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                changePin();
                finish();
            }
        });
        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void changePin() {

        SharedPreferences preferences = getSharedPreferences("myprefs", Context.MODE_PRIVATE);
        final String name=preferences.getString("acc_number",null);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.CHANGEPIN_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        SharedPreferences preferences = getSharedPreferences("myprefs", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putString("pin",confirmpin.getText().toString());
                        editor.apply();
                        Toast.makeText(ChangePIN.this,response,Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ChangePIN.this,error.toString(),Toast.LENGTH_LONG).show();
                        //You can handle error here if you want
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                //Adding parameters to request
                params.put(Config.ACC_CNUMBER, name);
                params.put(Config.ACC_NPIN, confirmpin.getText().toString());
                //returning parameter
                return params;
    }
};   //Adding the string request to the queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }}

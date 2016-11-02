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

public class StopCheque extends AppCompatActivity {
EditText stopcheque;
    Button chequestopping;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stop_cheque);

        Toolbar toolbar=(Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Stop Cheque");
        stopcheque=(EditText)findViewById(R.id.stopcheque);
        stopcheque.setText("MBAP-1000");
        stopcheque.setEnabled(false);
        chequestopping=(Button) findViewById(R.id.chequestopping);
        chequestopping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences preferences = getSharedPreferences("myprefs", Context.MODE_PRIVATE);
                final String acc_name=preferences.getString("acc_number",null);
                StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.STOPCHECK_URL,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Toast.makeText(StopCheque.this,response,Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(StopCheque.this,error.toString(),Toast.LENGTH_LONG).show();
                                //You can handle error here if you want
                            }
                        }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String,String> params = new HashMap<>();
                        //Adding parameters to request
                        params.put(Config.ACC_MNUMBER, acc_name);
                        params.put(Config.ACC_CHECKNUMBER, stopcheque.getText().toString());
                        params.put(Config.ACC_CHECKPLACED, "0");
                        //returning parameter
                        return params;
                    }
                };
                //Adding the string request to the queue
                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                requestQueue.add(stringRequest);
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}

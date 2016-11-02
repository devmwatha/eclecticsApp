package com.smartboma.development.m_bankingapp.UI;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.smartboma.development.m_bankingapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class BalanceEnquiry extends AppCompatActivity {
TextView acc_num,m_balance,user_name,acc_numba;
    String fnmae,lname,name;
    String j_fname,j_lname,j_acc,j_amount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_balance_enquiry);

        Toolbar toolbar=(Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Balance Enquiry");
        acc_num=(TextView) findViewById(R.id.acc_name);
        m_balance=(TextView) findViewById(R.id.balance);
        user_name=(TextView) findViewById(R.id.user_name);

        name=getIntent().getStringExtra("accnu");
        acc_num.setText("Account number "+name);

        getData();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    public void getData() {
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest("http://10.0.2.2/eclectics/balance.php?acc="+name, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray jsonArray) {
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject json;
                    try {
                        json = jsonArray.getJSONObject(i);
                        j_fname=json.getString("name");
                        j_amount=json.getString("amount");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }                }

                user_name.setText(getString(R.string.helo)+" "+j_fname);
                m_balance.setText(getString(R.string.bal)+": Ksh. "+j_amount);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        });

        //Adding the string request to the queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);
    }

}

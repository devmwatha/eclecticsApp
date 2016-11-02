package com.smartboma.development.m_bankingapp.UI;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.smartboma.development.m_bankingapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity  implements NavigationView.OnNavigationItemSelectedListener{
    DrawerLayout drawer;
    TextView name;
    String name2;
    String j_fname,j_lname;
    int j_amount,j_acc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar=(Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawer= (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(this, drawer,toolbar,R.string.open,
                R.string.close);
        toggle.syncState();
        name=(TextView) findViewById(R.id.name);
        SharedPreferences sharedPreferences=getSharedPreferences("myprefs", Context.MODE_PRIVATE);
        name2=sharedPreferences.getString("acc_number",null);
        name.setText(name2);
        NavigationView navigationView= (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id=item.getItemId();
        switch (id)
        {
            case R.id.deposit:
                startActivity(new Intent(MainActivity.this,Deposit.class));
                break;
            case R.id.b_enquiry:
                Intent intent= new Intent(MainActivity.this,BalanceEnquiry.class);
                intent.putExtra("accnu",name.getText().toString());
                startActivity(intent);
                break;
            case R.id.stop_cheque:
                startActivity(new Intent(MainActivity.this,StopCheque.class));
                break;
            case R.id.change_pin:
                startActivity(new Intent(MainActivity.this,ChangePIN.class));
                break;
            case R.id.f_tips:
                startActivity(new Intent(MainActivity.this,FinacialTips.class));
                break;
            case R.id.cheque_placement:
                startActivity(new Intent(MainActivity.this,PlaceCheque.class));
                break;
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}

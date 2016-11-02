package com.smartboma.development.m_bankingapp.UI;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.LinearLayout;

import com.smartboma.development.m_bankingapp.adapters.FinanceAdapter;
import com.smartboma.development.m_bankingapp.R;
import com.smartboma.development.m_bankingapp.models.Finances;

import java.util.ArrayList;
import java.util.List;

public class FinacialTips extends AppCompatActivity {
RecyclerView recyclerView;
    FinanceAdapter adapter;
    String content;
    List<Finances> list= new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finacial_tips);

        Toolbar toolbar=(Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Financial Tips");
        for (int i=1;i<=20;i++)
        {
            Finances finances= new Finances("Financial tip "+i,
                    "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Morbi molestie nisi dui.");
            list.add(finances);
        }

        recyclerView=(RecyclerView) findViewById(R.id.recview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL));
        adapter=new FinanceAdapter(list,this);
        recyclerView.setAdapter(adapter);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}

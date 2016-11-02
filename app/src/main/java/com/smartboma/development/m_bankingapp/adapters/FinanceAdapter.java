package com.smartboma.development.m_bankingapp.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.smartboma.development.m_bankingapp.R;
import com.smartboma.development.m_bankingapp.models.Finances;

import java.util.List;

/**
 * Created by Mwatha on 02/11/2016.
 */

public class FinanceAdapter extends RecyclerView.Adapter<FinanceAdapter.ViewHolder> {
    List<Finances> financesList;
private  Context context;

    public FinanceAdapter(List<Finances> financesList, Context context) {
        this.financesList = financesList;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.financelayout, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(FinanceAdapter.ViewHolder holder, int position) {

        Finances finances=financesList.get(position);
        holder.header.setText(finances.getHeader());
        holder.content.setText(finances.getContent());
    }

    @Override
    public int getItemCount() {
        return financesList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView header,content;
        public ViewHolder(View itemView) {
            super(itemView);
            header=(TextView) itemView.findViewById(R.id.finance_tip_heading);
            content=(TextView)itemView.findViewById(R.id.finance_contents);
        }
    }
}

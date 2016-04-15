package com.bishwajit.transactions;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by bishwajit on 4/14/2016.
 */
public class RecyclerAdapterPending extends RecyclerView.Adapter<RecyclerAdapterPending.RecyclerViewHolder> {

    Color c = new Color();
    ArrayList<TransactionDetails> list;
    Context ctx;

    public RecyclerAdapterPending(ArrayList<TransactionDetails> list, Context ctx) {
        this.list = list;
        this.ctx = ctx;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        // inflating the view
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pending_not_originator, parent, false);
        RecyclerViewHolder rvh = new RecyclerViewHolder(view, ctx, list);
        return rvh;

    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {

        // setting up the values of the UI elements from the ith transaction details
        TransactionDetails t = list.get(position);
        holder.title.setText(t.getTitle());
        String amt ="₹ " + t.getAmount();
        holder.amount.setText(amt);
        holder.time.setText("10:00 AM");

        // the originator user for the transaction migh change his mind and cancle the transaction
        if(t.getOriginator())
        {
            holder.pay.setText("CANCEL");
            holder.pay.setBackgroundColor(holder.pay.getContext().getResources().getColor(R.color.colorAccent));
            holder.decline.setVisibility(View.INVISIBLE);
        }
        else
            holder.pay.setText("PAY");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public static class RecyclerViewHolder extends RecyclerView.ViewHolder {

        // The UI elements for Pending transactions
        TextView time, amount, title;
        Button decline, pay;
        ArrayList<TransactionDetails> list;
        Context ctx;

        // constructor
        public RecyclerViewHolder(View itemView , Context ctx, ArrayList<TransactionDetails> list)
        {
            super(itemView);
            this.list = list;
            this.ctx = ctx;
            decline = (Button) itemView.findViewById(R.id.decline);
            pay = (Button) itemView.findViewById(R.id.pay);
            time = (TextView) itemView.findViewById(R.id.time);
            amount = (TextView) itemView.findViewById(R.id.amount);
            title = (TextView) itemView.findViewById(R.id.title);

        }

    }
}

package com.bishwajit.transactions;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by bishwajit on 4/14/2016.
 */
public class RecyclerAdapterHistory extends RecyclerView.Adapter<RecyclerAdapterHistory.RecyclerViewHolderHistory> {

    ArrayList<TransactionDetails> list;
    Context ctx;

    public RecyclerAdapterHistory(ArrayList<TransactionDetails> list, Context ctx) {
        this.list = list;
        this.ctx = ctx;
    }


    @Override
    public RecyclerViewHolderHistory onCreateViewHolder(ViewGroup parent, int viewType) {

        // inflating the view
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.history, parent, false);
        RecyclerViewHolderHistory rvh = new RecyclerViewHolderHistory(view, ctx, list);
        return rvh;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolderHistory holder, int position) {

        // setting up the values of the UI elements from the ith transaction details
        TransactionDetails t = list.get(position);
        holder.title.setText(t.getTitle());
        String amt ="₹ " + t.getAmount();
        holder.amount.setText(amt);
        holder.time.setText("10:00 AM");
        String txId = "Txn. Id: " + String.valueOf(t.getId());
        holder.transactionID.setText(txId);

        String status = t.getStatus();

        // if transaction is completed show "tick" symbol else show "cross" symbol
        if(status.compareToIgnoreCase("COMPLETED") == 0)
            holder.profilePic.setImageDrawable(holder.profilePic.getContext().getResources().getDrawable(R.drawable.successful));
        else
            holder.profilePic.setImageDrawable(holder.profilePic.getContext().getResources().getDrawable(R.drawable.declined));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public static class RecyclerViewHolderHistory extends RecyclerView.ViewHolder {

        // The UI elements for history transactions
        TextView time, amount, title,transactionID;
        ImageView profilePic;
        ArrayList<TransactionDetails> list;
        Context ctx;

        // constructor
        public RecyclerViewHolderHistory(View itemView , Context ctx, ArrayList<TransactionDetails> list)
        {
            super(itemView);
            this.list = list;
            this.ctx = ctx;
            profilePic = (ImageView) itemView.findViewById(R.id.profilePic);
            time = (TextView) itemView.findViewById(R.id.time);
            amount = (TextView) itemView.findViewById(R.id.amount);
            title = (TextView) itemView.findViewById(R.id.title);
            transactionID = (TextView) itemView.findViewById(R.id.transactionID);

        }

    }
}
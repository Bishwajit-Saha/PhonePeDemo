package com.bishwajit.transactions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by bishwajit on 4/14/2016.
 */
public class JSONtoArray {

    String data;

    // PendingList pl, historyList hl
    ArrayList<TransactionDetails> pl, hl;

    // A list containing the 2 list
    ArrayList<ArrayList<TransactionDetails>> list;

    // constructor
    public JSONtoArray(String data) {
        this.data = data;

        // if data received is not empty
        if(data != null || data.length() != 0) {
            pl = new ArrayList<TransactionDetails>();
            hl = new ArrayList<TransactionDetails>();
            parseData();
        }
    }

    // takes each JSONObject from the data and parse it to a proper object
    private void parseData() {

        try {
            JSONObject jo = new JSONObject(data);
            JSONArray ja = jo.getJSONArray("transactionRequests");

            // check for empty transactionRequests
            if(ja.length() == 0)
                return;

            TransactionDetails t;

            /* the data received has JSONObjects sorted in ascending order
            of transaction Id, but here we use reverse so that most recent
            is shown before
             */
            for(int i = ja.length()-1 ; i >= 0; i--)
            {
                jo = ja.getJSONObject(i);
                t = new TransactionDetails();
                t.setAmount(jo.getLong("amount"));
                t.setId(jo.getLong("id"));
                t.setOriginator(jo.getBoolean("isOriginator"));
                t.setStatus(jo.getString("status"));
                t.setType(jo.getString("type"));

                // if the status is CREATED than it is a pending transaction and goes to pendingList
                if((t.getStatus().equalsIgnoreCase("CREATED")) )
                {
                    // if isOriginator is true then user is recipient else the user is payee
                    if(t.isOriginator)
                        t.setTitle("You are to receive from ABC");

                    else
                        t.setTitle("ABC has requested ");

                    pl.add(t);
                }
                // else goes to historyList
                else
                {
                    String s = "";
                    String status = t.getStatus();
                    if(status.compareToIgnoreCase("COMPLETED") == 0)
                    {
                        if(t.isOriginator)
                            s = "XYZ successfully paid you ";
                        else
                            s = "You successful paid XYZ ";
                    }
                    else if(status.compareToIgnoreCase("cancelled") == 0)
                    {
                        if(t.isOriginator)
                            s = "XYZ cancelled the payment of ";
                        else
                            s = "Your payment to XYZ got cancelled";
                    }
                    else if(status.compareToIgnoreCase("declined") == 0)
                    {
                        if(t.isOriginator)
                            s = "XYZ/Bank declined the payment ";
                        else
                            s = "Your payment got declined by bank";
                    }
                    t.setTitle(s);
                    hl.add(t);
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    // function returns the list containing the pendingList and HistoryList
    public ArrayList<ArrayList<TransactionDetails>> getList()
    {
        list = new ArrayList<ArrayList<TransactionDetails>>();
        list.add(pl);
        list.add(hl);
        return list;
    }

}



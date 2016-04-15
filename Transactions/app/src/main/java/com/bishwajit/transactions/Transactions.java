package com.bishwajit.transactions;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.ArrayList;

public class Transactions extends AppCompatActivity {

    RecyclerView pendingList, historyList;          // 2 recyclerViews for 2 lists
    LinearLayout pendingLayout, historyLayout;      // to check if one is empty then hide it
    String url;                                     // url which will be used to fetch data
    RequestQueue rq;                                // requestQueue for Volley
    ArrayList<ArrayList<TransactionDetails>> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transactions);

        // getting the userId from the intent extras
        int id = getIntent().getIntExtra("userId", 0);

        // referring to the UI elements
        pendingLayout = (LinearLayout) findViewById(R.id.pendingLayout);
        historyLayout = (LinearLayout) findViewById(R.id.historyLayout);

        pendingList = (RecyclerView) findViewById(R.id.pendingList);
        historyList = (RecyclerView) findViewById(R.id.historyList);

        // generating the url for the userId
        url = "http://stage.phonepe.com/transaction/request?userId=" + id ;
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        fetchData();
    }

    // function does the work to fetch the data from the server and passes the work to showTransactions
    private void fetchData() {

        rq = VolleySingleton.getsInstance().getmRequestQueue();
        StringRequest request = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Parse the received JSON data
                        JSONtoArray j2a = new JSONtoArray(response);
                        list = j2a.getList();
                        showTransactions();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // if no data is received or error occured
                        Toast.makeText(getApplicationContext(), "Error fetching data",
                                Toast.LENGTH_LONG).show();
                    }
                }
        );
        rq.add(request);
    }

    private void showTransactions() {

        // check is the list is null
        if(list.size() != 0)
        {
            ArrayList<TransactionDetails> pl = list.get(0);
            /*RecyclerAdapterPending will be used for this List
                If pl is null that means there is no pending transaction for the user
                so set the layout to INVISIBLE
             */
            if(pl == null)
                pendingLayout.setVisibility(View.INVISIBLE);
            else {
                RecyclerView recyclerView = (RecyclerView) findViewById(R.id.pendingList);
                RecyclerView.Adapter adapter = new RecyclerAdapterPending(pl, this);
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setHasFixedSize(false);
                recyclerView.setAdapter(adapter);
            }

            /*RecyclerAdapterHistory will be used for this List
                If hl is null that means there is no pending transaction for the user
                so set the layout to INVISIBLE
             */
            ArrayList<TransactionDetails> hl = list.get(1);
            if(hl == null)
                historyLayout.setVisibility(View.INVISIBLE);
            else {
                RecyclerView recyclerView2 = (RecyclerView) findViewById(R.id.historyList);
                RecyclerView.Adapter adapter2 = new RecyclerAdapterHistory(hl, this);
                RecyclerView.LayoutManager layoutManager2 = new LinearLayoutManager(this);
                recyclerView2.setLayoutManager(layoutManager2);
                recyclerView2.setHasFixedSize(false);
                recyclerView2.setAdapter(adapter2);
            }
        }
    }
}

package com.bishwajit.transactions;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    Button login;
    EditText userId;
    SharedPreferences sp;
    int id = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // referring to the UI elements
        login = (Button) findViewById(R.id.login);
        userId = (EditText) findViewById(R.id.userId);

        // using SharedPreference to get last logged userId
        sp = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        id = sp.getInt("userId", 0);
        if(id != 0)
            userId.setText(String.valueOf(id));

        // setting the onClickListener for the login button
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // editing the SharedPreference file
                SharedPreferences.Editor e = sp.edit();
                String s = userId.getText().toString();
                if(s.length() == 0)
                {
                    Toast.makeText(getApplicationContext(), "enter valid UserId", Toast.LENGTH_SHORT).show();
                    return;
                }
                try
                {
                    id = Integer.parseInt(s);
                }
                catch (Exception err)
                {
                    // parse error
                }
                e.putInt("userId", id);
                e.apply();

                // going to next Activity to show all transactions
                Intent go = new Intent(getApplicationContext(), Transactions.class);
                go.putExtra("userId", id);
                startActivity(go);
            }
        });

    }
}

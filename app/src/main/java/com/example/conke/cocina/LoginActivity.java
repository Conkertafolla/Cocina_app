
package com.example.conke.cocina;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.conke.cocina.SQL.SQLHelper;
import com.example.conke.cocina.SQL.SQLQueries;


public class LoginActivity extends AppCompatActivity {
    EditText password;
    EditText mailUser;
    boolean loadData;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
        //Create the local database
        SQLHelper conection = new SQLHelper(this,"db_order",null,1);
        SQLQueries queries  = new SQLQueries(this);
        loadData= queries.loadDefault();
        if(!loadData){
        queries.setDefaultValues();
        }


    }

    public void validateUser(View v){
       boolean isUser= false ;
       SQLQueries queries = new SQLQueries(this);
       mailUser = findViewById(R.id.input_email);
       password = findViewById(R.id.input_password);
       isUser=queries.validUser(mailUser.getText().toString(),password.getText().toString());

      if (isUser){
           goHome(mailUser.getText().toString());
      }else {
          mailUser.setText(" ");
          password.setText(" ");
          Toast.makeText(this,"ERROR usuario no valido",Toast.LENGTH_LONG).show();

      }

    }
    public void goHome(String userMail){
        Intent intent = new Intent(this,Home.class);
        intent.putExtra("userMail",userMail);
        startActivity(intent);

    }
}

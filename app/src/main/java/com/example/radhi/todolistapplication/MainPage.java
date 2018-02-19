package com.example.radhi.todolistapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import static com.example.radhi.todolistapplication.R.id.LinearLayoutApp;

public class MainPage extends AppCompatActivity {

    private String readFromFile(Context context) {

        String ret = "";

        try {
            StringBuffer buffer = new StringBuffer();
            FileInputStream fis = context.openFileInput("config.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
            String Read;
            if (fis!=null){
                while ((Read = reader.readLine()) !=null){
                    buffer.append(Read + "\n");
                }
            }
            fis.close();
            return buffer.toString();


        }
        catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        }

        return ret;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        LinearLayout ll = (LinearLayout) findViewById(R.id.LinearLayoutApp);


        Button button1 = (Button) findViewById(R.id.AddToDoButton);
        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(MainPage.this, AddToDo.class));
                finish();
            }

        });

        String b = readFromFile(getApplicationContext());
        String [] filewords = b.split("\n");
        int sizes = filewords.length;




        for(int i = 0; i<sizes; i++){
            CheckBox ch = new CheckBox(this);
            ch.setId(i);
            ch.setText(filewords[i]);
            ch.setOnClickListener(getOnClickDoSomething(ch));
            ll.addView(ch);

        }
        /*if(ch.isChecked()){
            ll.removeView(ch);
        }*/


    }

    View.OnClickListener getOnClickDoSomething(final Button button){
        return new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Log.d("ON_CLICK","CheckBox ID" + button.getId() + "Text" + button.getText().toString());



        }


        };

    }



}

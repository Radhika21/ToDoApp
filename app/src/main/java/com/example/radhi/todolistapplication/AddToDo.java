package com.example.radhi.todolistapplication;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import android.content.Context;

public class AddToDo extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_do);

        Button button = (Button) findViewById(R.id.DoneButton);
        button.setOnClickListener(this);
        Button button1 = (Button) findViewById(R.id.AddAnotherButton);
        button1.setOnClickListener(this);

    }

    private void writeToFile(String data, Context context){


        try {
            FileOutputStream fos = context.openFileOutput("config.txt",Context.MODE_PRIVATE);
            fos.write(data.getBytes());
            fos.close();
            /*OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput("config.txt", Context.MODE_PRIVATE));
            outputStreamWriter.append(data);
            outputStreamWriter.close();*/
        }
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }
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
    public void onClick(View v){

        switch (v.getId()){

            case R.id.AddAnotherButton:
                EditText newItem = (EditText) findViewById(R.id.TextBox);
                String a = newItem.getText().toString();
                String filecontents = readFromFile(getApplicationContext());
                filecontents = filecontents + a;
                writeToFile(filecontents, getApplicationContext() );

                /*try {
                    Lists.AddItem(a);
                } catch (IOException e) {
                    e.printStackTrace();
                }*/
                newItem.setText("", TextView.BufferType.EDITABLE);

                break;

            case R.id.DoneButton:
                EditText newItem1 = (EditText) findViewById(R.id.TextBox);
                String c = newItem1.getText().toString();
                String filecontentss = readFromFile(getApplicationContext());
                filecontentss = filecontentss + c;
                writeToFile(filecontentss, getApplicationContext() );
                /*try {
                    Lists.AddItem(c);
                } catch (IOException e) {
                    e.printStackTrace();
                }*/

                startActivity(new Intent(AddToDo.this, MainPage.class));

                break;

            default:
                break;


        }

}}

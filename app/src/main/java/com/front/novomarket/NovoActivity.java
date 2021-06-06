package com.front.novomarket;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class NovoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novo);
        final Intent intentMain=new Intent(this,MainActivity.class);
        Thread timer= new Thread(){
            @Override
            public void run(){
                try {
                    sleep(5000);
                }catch (InterruptedException ex){

                }finally {
                    startActivity(intentMain);
                    finish();
                }
            }
        };
        timer.start();
    }
}
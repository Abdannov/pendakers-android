package com.pendakers.Activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;

import com.pendakers.Activity.HomeActivity;
import com.pendakers.Manager.Const;
import com.pendakers.Manager.PrefManager;
import com.pendakers.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        ActionBar actionBar;
        actionBar = getSupportActionBar();

        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#00a859"));
        actionBar.setBackgroundDrawable(colorDrawable);

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                PrefManager prf = new PrefManager(MainActivity.this);
                if(!prf.getString(Const.TOKEN).equals("")){
                    startActivity(new Intent(getApplicationContext(),HomeActivity.class));
                }else{
                    startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                }
                finish();
            }
        }, 3000);
    }
}
package com.cumulations.broadcastdummy;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    CustomBroadcastReceiver customBroadcastReceiver;

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        customBroadcastReceiver = new CustomBroadcastReceiver();
        textView = findViewById(R.id.textView);
    }

    @Override
    protected void onStart() {
        super.onStart();
        IntentFilter intentFilter = new IntentFilter("com.custom.broadcast.test1");
        registerReceiver(customBroadcastReceiver, intentFilter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(customBroadcastReceiver);
    }

    public void blue(View view) {
        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
        intent.putExtra("color", "blue");
        startActivityForResult(intent, 1);
    }

    public void red(View view) {
        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
        intent.putExtra("color", "red");
        startActivityForResult(intent, 1);
    }

    public void yellow(View view) {
        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
        intent.putExtra("color", "yellow");
        startActivityForResult(intent, 2);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == 10) {

            String message = null;
            String parent = null;
            if (data != null) {
                message = data.getExtras().getString("MESSAGE", "DEFAULT MSZ");
                parent = data.getExtras().getString("PARENT", "UNDEFINED");
            }

            textView.setText(message);
            try {
                if (parent != null && parent.equals("red")) {
                    Toast.makeText(this, "RED Success.", Toast.LENGTH_SHORT).show();
                } else if (parent != null && parent.equals("blue")) {
                    Toast.makeText(this, "BLUE Success.", Toast.LENGTH_SHORT).show();
                }
            } catch (Exception e) {
                Toast.makeText(this, "" + e.getMessage(), Toast.LENGTH_SHORT).show();
            }

        }

        if (requestCode == 1 && resultCode == 0) {
            textView.setText(null);
            Toast.makeText(this, "Didn't fill the form.", Toast.LENGTH_SHORT).show();
        }

        if (requestCode == 2 && resultCode == 10) {
            String message = null;
            if (data != null) {
                message = data.getExtras().getString("MESSAGE", "DEFAULT MSZ");
            }
            textView.setText(message);
            Toast.makeText(this, "Yellow Success.", Toast.LENGTH_SHORT).show();
        }

        if (requestCode == 2 && resultCode == 0) {
            textView.setText(null);
            Toast.makeText(this, "Didn't fill the form.", Toast.LENGTH_SHORT).show();
        }

    }
}

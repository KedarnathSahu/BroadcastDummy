package com.cumulations.broadcastdummy;

import android.content.Intent;
import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {
    EditText editText;
    ConstraintLayout containerTwo;

    String parent=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        editText = (EditText) findViewById(R.id.editText);
        containerTwo = (ConstraintLayout) findViewById(R.id.constraintLayout);

        try {
            String color = getIntent().getExtras().getString("color", "gray");
            changeBackground(color);
        }catch (Exception e){
            Toast.makeText(this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }

    public void submit(View view) {
        String message = editText.getText().toString();
        Intent intent = new Intent();
        intent.putExtra("MESSAGE", message);
        intent.putExtra("PARENT",parent);
        setResult(10, intent);
        finish();
    }

    private void changeBackground(String color) {

        if (color.equals("red")) {
            parent="red";
            containerTwo.setBackgroundColor(Color.RED);
        }

        if (color.equals("yellow")) {
            containerTwo.setBackgroundColor(Color.YELLOW);
        }

        if (color.equals("blue")) {
            parent="blue";
            containerTwo.setBackgroundColor(Color.BLUE);
        }

        if (color.equals("gray")) {
            containerTwo.setBackgroundColor(Color.GRAY);
        }
    }

}

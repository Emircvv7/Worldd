// src/main/java/com/example/myapplication/MainActivity.java
package com.example.worldd;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonSend = findViewById(R.id.buttonSend);
        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = ((EditText) findViewById(R.id.editTextEmail)).getText().toString();
                String subject = ((EditText) findViewById(R.id.editTextSubject)).getText().toString();
                String message = ((EditText) findViewById(R.id.editTextMessage)).getText().toString();

                Intent intent = new Intent(MainActivity.this, com.example.worldd.SecondActivity.class);
                intent.putExtra("email", email);
                intent.putExtra("subject", subject);
                intent.putExtra("message", message);
                startActivity(intent);
            }
        });
    }
}

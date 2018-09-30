package com.example.meenal.petconnect;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.util.Log;

import java.util.Arrays;
import java.util.List;

public class Forget extends AppCompatActivity{
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget);
        Button send = (Button) findViewById(R.id.send);
        Button cancel = (Button) findViewById(R.id.cancel);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Forget.this, Login.class);
                startActivity(intent);
            }
        });

        send.setOnClickListener(new View.OnClickListener() {
            @SuppressWarnings("unchecked")
            @Override
            public void onClick(View view) {
                Boolean errorFlag = false;
                EditText username = (EditText) findViewById(R.id.username);

                //checks username
                if (username.getText().toString().isEmpty()) {
                    Toast.makeText(Forget.this, "Can't Send: Username still required", Toast.LENGTH_SHORT).show();
                    errorFlag = true;
                }
                if (!errorFlag) {
                    Boolean error = false;

                    Model model = Model.getInstance();

                    username.setError(null);

                    String uid = username.getText().toString();
                    User lookup = model.getUserByUsername(uid);

                    if (TextUtils.isEmpty(uid)) {
                        username.setError(getString(R.string.error_field_required));
                    } else if (!uid.equals(lookup.getUsername()) && !uid.equals("user")) {
                        username.setError("Error Invalid Username");
                        error = true;
                    }
                    if (uid.equals(lookup.getUsername()) && !error) {
                        Log.i("SendMailActivity", "Send Button Clicked.");

                        String fromEmail = "petconnect8132@gmail.com";
                        String fromPassword = "81328132";
                        String toEmails = lookup.getEmail();
                        List<String> toEmailList = Arrays.asList(toEmails
                                .split("\\s*,\\s*"));
                        Log.i("SendMailActivity", "To List: " + toEmailList);
                        String emailSubject = "PetConnect Password";
                        String emailBody = "Password: " + lookup.getPassword();
                        new SendMailTask(Forget.this).execute(fromEmail,
                                fromPassword, toEmailList, emailSubject, emailBody);
                        Toast.makeText(Forget.this, "email sent", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Forget.this, GoogleSignInActivity.class);
                        startActivity(intent);

                    } else {
                        Toast.makeText(Forget.this, "Username not found", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

    }



}

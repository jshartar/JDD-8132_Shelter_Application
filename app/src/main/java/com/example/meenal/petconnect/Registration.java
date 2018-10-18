package com.example.meenal.petconnect;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Spinner;
import persistence.ManagementFacade;

public class Registration extends AppCompatActivity {
    private Spinner accountTypeSpinner;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Button register = (Button) findViewById(R.id.button_register);
        Button cancel = (Button) findViewById(R.id.button_cancel);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Registration.this, Login.class);
                startActivity(intent);
            }
        });


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Boolean errorFlag = false;
                EditText username = (EditText) findViewById(R.id.editText_username);
                EditText password1 = (EditText) findViewById(R.id.editText_password);
                EditText password2 = (EditText) findViewById(R.id.editText_confirmPassword);
                EditText firstName = (EditText) findViewById(R.id.editText_firstName);
                EditText lastName = (EditText) findViewById(R.id.editText_lastName);
                EditText email = (EditText) findViewById(R.id.editText_email);
                EditText phone = (EditText) findViewById(R.id.editText_phone);

                //password confirmation check
                if (!password1.getText().toString().equals(password2.getText().toString())) {
                    Toast.makeText(Registration.this, "Can't Register: Your passwords do not match", Toast.LENGTH_SHORT).show();
                    errorFlag = true;
                }
                //checks username, both password fields, and email for empty fields
                if (username.getText().toString().isEmpty() || password1.getText().toString().isEmpty() ||
                        password2.getText().toString().isEmpty() || email.getText().toString().isEmpty()) {
                    Toast.makeText(Registration.this, "Can't Register: Username, password, and email required", Toast.LENGTH_SHORT).show();
                    errorFlag = true;
                }

                //checks if both username and password are > 6 chars
                if (username.getText().toString().length() < 6 || password1.getText().toString().length() < 6 ||
                        password2.getText().toString().length() < 6) {
                    Toast.makeText(Registration.this, "Can't Register: Username and password must be at least 6 characters", Toast.LENGTH_SHORT).show();
                    errorFlag = true;
                }

                //user is registered and added to the model
                if (!errorFlag) {
                    Model model = Model.getInstance();
                    ManagementFacade mf = ManagementFacade.getInstance();
                    User _user = new User(username.getText().toString(), firstName.getText().toString(),
                            lastName.getText().toString(), password2.getText().toString(),
                            email.getText().toString(), "0000000000", "admin" );


                    if (phone.getText().toString().isEmpty()) {
                        _user.setPhoneNumber("0000000000");
                    } else {
                        _user.setPhoneNumber(phone.getText().toString().replaceAll("\\D+", ""));
                        //uses regex to pull non digits out of the string, if not null
                    }

                    model.addUser(_user);
                    model.setCurrentUser(_user);
                    mf.addUser(_user);
                    Intent intent = new Intent(Registration.this, Application.class);
                    startActivity(intent);

                }

            }
        });
    }
}

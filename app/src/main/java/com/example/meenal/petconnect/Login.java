package com.example.meenal.petconnect;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;

public class Login extends AppCompatActivity {
    private final ArrayList<User> loginRecord = new ArrayList<>();
    /**
     *updates login records
     * @param username username of user
     * @param password user's password
     */
    private void updateLoginRecord(String username, String password) {
        loginRecord.add(new User(username, password));
    }
    /**
     *counts login attempts
     * @param username user's username
     * @param password user's password
     * @return attempts
     */
    private int countAttempts(String username, String password) {
        int count = 0;
        for (User u: loginRecord) {
            if (u.getUsername().equals(username) && u.getPassword().equals(password)) {
                count++;
            }
        }
        return count;
    }
    /**
     *tries to login
     */
    private void attemptLogin() {
        Boolean error = false;

        EditText username = (EditText) findViewById(R.id.name);
        EditText password = (EditText) findViewById(R.id.pass);

        Model model = Model.getInstance();

        // Reset errors.
        username.setError(null);
        password.setError(null);

        // Store values at the time of the login attempt.
        String uid = username.getText().toString();
        String pass = password.getText().toString();
        User lookup = model.getUserByUsername(uid);


        // Check for a valid password, if the user entered one.
        if (TextUtils.isEmpty(password.getText()) || !pass.equals(lookup.getPassword()) &
                !pass.equals("pass")) {
            password.setError(getString(R.string.error_incorrect_password));
            error = true;
        } else if (TextUtils.isEmpty(pass)) {
            password.setError(getString(R.string.error_field_required));
        }

        // Check for a valid uid.
        if (TextUtils.isEmpty(uid)) {
            username.setError(getString(R.string.error_field_required));
        } else if (!uid.equals(lookup.getUsername()) && !uid.equals("user")) {
            username.setError("Error Invalid Username");
            error = true;
        }

        if (uid.equals(lookup.getUsername()) && pass.equals(lookup.getPassword()) & !error) {
            model.setCurrentUser(model.getUserByUsername(username.getText().toString()));
            Intent intent = new Intent(Login.this, MainActivity.class);
            startActivity(intent);
        } else if (username.getText().toString().equals("user") && password.getText().toString().equals("pass")) {
            User defaultUser = (new User("user", "Default", "User", "pass", "petconnect8132@gmail.com",
                    "000000000", "Admin"));
            model.addUser(defaultUser);
            model.setCurrentUser(defaultUser);
            Intent intent = new Intent(Login.this, MainActivity.class);
            startActivity(intent);
        } else if (error) {
            updateLoginRecord(uid, pass);
            Toast.makeText(Login.this, "Login Failed: " + Integer.toString(4 - countAttempts(uid,pass)) + " try(s) remaining", Toast.LENGTH_SHORT).show();
        }


    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

//        EditText username = (EditText) findViewById(R.id.name);
        final EditText password = (EditText) findViewById(R.id.pass);

        Button login = (Button) findViewById(R.id.login);
        Button register = (Button) findViewById(R.id.register);
        Button forget = (Button) findViewById(R.id.forgetPass);

        password.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });

        forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, Forget.class);
                startActivity(intent);
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, Registration.class);
                startActivity(intent);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText username = (EditText) findViewById(R.id.name);
                EditText password = (EditText) findViewById(R.id.pass);
                Model model = Model.getInstance();
                String uid = username.getText().toString();
                String pass = password.getText().toString();

                User lookup = model.getUserByUsername(username.getText().toString());
                if (countAttempts(uid, pass) > 2) {
                    Toast.makeText(Login.this, "User Account Locked", Toast.LENGTH_SHORT).show();
                    model.addBannedUser(lookup);
                    lookup.ban();
                } else {
                    attemptLogin();
                }
            }
        });
/*
  If this wasn't here logout could be over ridden with the back button and would be a glaring
  security flaw
 */
    }
    @Override
    public void onBackPressed() {
        startActivity(new Intent(Login.this, GoogleSignInActivity.class));
    }
}

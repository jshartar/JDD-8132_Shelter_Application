package com.example.meenal.petconnect;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class UserDetails extends AppCompatActivity{
    @SuppressLint("SetTextI18n")
    public void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_userdetails);
        super.onCreate(savedInstanceState);

        final Model model = Model.getInstance();
        User user = model.getCurrentUser();
        Button ban = (Button) findViewById(R.id.button_ban);
        Button unban = (Button) findViewById(R.id.button_unban);

        TextView name_text = (TextView) findViewById(R.id.text_view_name);
        TextView email_text = (TextView) findViewById(R.id.text_view_email);
        TextView phone_text = (TextView) findViewById(R.id.text_view_phone);
        TextView type_text = (TextView) findViewById(R.id.text_view_lockout);
        TextView admin_text = (TextView) findViewById(R.id.text_view_admin);
        TextView username_text = (TextView) findViewById(R.id.text_view_username);
        TextView pass_text = (TextView) findViewById(R.id.text_view_password);

        name_text.setText(user.getFullName());
        email_text.setText(user.getEmail());
        phone_text.setText(user.getPhoneNumber());
        username_text.setText(user.getUsername());
        pass_text.setText(user.getPassword());

        if (user.getAdmin().equalsIgnoreCase("admin")) {
            admin_text.setText("Yes");
        } else {
            admin_text.setText("No");
        }

        if (user.getLockoutStatus()) {
            type_text.setText("Yes");
        } else {
            type_text.setText("No");
        }

        ban.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                Toast.makeText(UserDetails.this, "User Account Locked", Toast.LENGTH_SHORT).show();
                TextView type_text = (TextView) findViewById(R.id.text_view_lockout);
                User user1 = model.getCurrentUser();
                user1.ban();
                model.addBannedUser(user1);
                type_text.setText("Yes");

            }
        });

        unban.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                Toast.makeText(UserDetails.this, "User Account Unlocked", Toast.LENGTH_SHORT).show();
                TextView type_text = (TextView) findViewById(R.id.text_view_lockout);

                User user1 = model.getCurrentUser();
                user1.unban();
                model.removeBannedUser(user1);
                type_text.setText("No");

            }
        });

    }
}

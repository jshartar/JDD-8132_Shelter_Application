package com.example.meenal.petconnect;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import persistence.ManagementFacade;
public class Admin extends AppCompatActivity {
    private final Model model = Model.getInstance();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        //get button ids
        //Button generate = (Button) findViewById(R.id.generate);
        Button back = (Button) findViewById(R.id.back);
        //Button viewUsers = (Button) findViewById(R.id.viewUsers);
        Button delete = (Button) findViewById(R.id.button_delete);

//        generate.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                ManagementFacade mf = ManagementFacade.getInstance();
//
//                String[] lostNameList = new String[]{"A Tale of 2 Cities", "A Hitchhiker's Guide to the Galaxy",
//                        "The Lord of the Rings", "The Things They Carried",
//                        "The Three Body Problem"};
//                String[] foundNameList = new String[]{"1984", "Brave New World",
//                        "Animal Farm","Dune","The Princess Bride"};
//
//                User user1 = new User("user1", "Eren", "Jaegar", "aot", "user1@gatech.edu", "0123456789", "user");
//                User user2 = new User("user2", "Mikasa", "Ackerman", "aot", "user2@gatech.edu", "0123456789", "admin");
//                User user3 = new User("user3", "Levi", "Ackerman", "aot", "user3@gatech.edu", "0123456789", "admin");
//                User user4 = new User("user4", "Reiner", "Braun", "aot", "user4@gatech.edu", "0123456789", "user");
//                model.addUser(user1);
//                model.addUser(user2);
//                model.addUser(user3);
//                model.addUser(user4);
//                mf.addUser(user1);
//                mf.addUser(user2);
//                mf.addUser(user3);
//                mf.addUser(user4);
//
//
//
//                Toast.makeText(Admin.this, "Sample data successfully generated", Toast.LENGTH_SHORT).show();
//            }
//        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                model.nuclearMeltdown();
                Toast.makeText(Admin.this, "Data erased", Toast.LENGTH_SHORT).show();
            }
        });

//        viewUsers.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(Admin.this, ViewUsers.class);
//                startActivity(intent);
//
//            }
//        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Admin.this, MainActivity.class);
                startActivity(intent);

            }
        });
    }
}

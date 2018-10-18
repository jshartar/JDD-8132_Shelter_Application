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


        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                model.nuclearMeltdown();
                Toast.makeText(Admin.this, "Data erased", Toast.LENGTH_SHORT).show();
            }
        });



        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Admin.this, MainActivity.class);
                startActivity(intent);

            }
        });
    }
}

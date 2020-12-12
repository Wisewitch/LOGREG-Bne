package com.e.logreg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

   Button btn_register, btn_reg_vissza;
    EditText et_reg_email, et_reg_fnev, et_reg_pass, et_reg_tnev;
   DBHelper adatbazis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        init();
        listeners();
    }
   /* private void ellenorzes() {

        // minden mező ki van-e töltve
    }
*/

    // regisztrál a beírt adatokkal..

    private void adatrogzites() {
        String email = et_reg_email.getText().toString().trim();
        String felhnev  = et_reg_fnev.getText().toString().trim();

        String jelszo = et_reg_pass.getText().toString().trim();
        String teljesnev  = et_reg_tnev.getText().toString().trim();


        if (email.isEmpty() || felhnev.isEmpty() ) {
            Toast.makeText( this, "Az email vagy a megadása kötelező!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (jelszo.isEmpty()) {
            Toast.makeText( this, "A jegy megadása kötelező!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (teljesnev.isEmpty()) {
            Toast.makeText( this, "A teljes megadása kötelező!", Toast.LENGTH_SHORT).show();
            return;
        }

        // sikeres reg toast

        if (adatbazis.adatrogzites(email, felhnev, jelszo, teljesnev)) {
            Toast.makeText( this, "Sikeres rögzítés!", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText( this, "Sikertelen volt az rögzítés!", Toast.LENGTH_SHORT).show();
        }

    }

    private void listeners() {
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                adatrogzites();
                // Kell egy toast!!! - sikeres adatrögzítés - ide kell vagy a metódusba?

            }
        });

        btn_reg_vissza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent vissza = new Intent (RegisterActivity.this, MainActivity.class);
                startActivity(vissza);
                finish();
            }
        });
    }

       private void  init(){
           btn_register = findViewById(R.id.btn_register);
           btn_reg_vissza = findViewById(R.id.btn_reg_vissza);
           et_reg_email = findViewById(R.id.et_reg_email);
           et_reg_fnev = findViewById(R.id.et_reg_fnev);
           et_reg_pass = findViewById(R.id.et_reg_pass);
           et_reg_tnev = findViewById(R.id.et_reg_tnev);

           adatbazis = new DBHelper( RegisterActivity.this);
       }


   }

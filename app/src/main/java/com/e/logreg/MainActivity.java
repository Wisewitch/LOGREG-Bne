package com.e.logreg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button btn_login, btn_reg_main;
    EditText et_fnev, et_pass;
    DBHelper adatbazis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        listeners();
    }

    // adatellenőrzés - - ellenőrizni kell, hogy nem üresek a mezők - üres mező  vagy hibás adat toast
    // belépés

    private void login() {
        String fnev = et_fnev.getText().toString().trim();
        String jelszo = et_pass.getText().toString().trim();

       // nem üres mezők

        if (fnev.isEmpty()) {
            Toast.makeText( this, "A felhaszbnálónév megadása kötelező!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (jelszo.isEmpty()) {
            Toast.makeText( this, "A jelszó megadása kötelező!", Toast.LENGTH_SHORT).show();
            return;
        }

        // sikeres belépés

        if (!adatbazis.loginCheck(fnev, jelszo)) {
            Toast.makeText(this, "Hibás felhasználónév vagy password!", Toast.LENGTH_SHORT).show();
            return;
        }
        else {
            Intent bejelentkezes = new Intent(MainActivity.this, LoggedInActivity.class);
            startActivity(bejelentkezes);
            finish();
        }
    }

    private void listeners() {

        btn_reg_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent register = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(register);
                finish();
            }
        });

        // Adatellenőrzés és belépés

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login(); // belépés, ho ok
            }
        });

    } // listeners vége

    private void init() {
        btn_login = findViewById(R.id.btn_login);
        btn_reg_main = findViewById(R.id.btn_reg_main);
        et_fnev = findViewById(R.id.et_fnev);
        et_pass = findViewById(R.id.et_pass);

        adatbazis = new DBHelper( MainActivity.this);
   }
}
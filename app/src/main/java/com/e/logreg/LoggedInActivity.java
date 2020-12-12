package com.e.logreg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.StringBuilderPrinter;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class LoggedInActivity extends AppCompatActivity {

    Button btn_logout;
    TextView text_Felhnev_full;

    DBHelper adatbazis;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logged_in);

        init();
        listeners();
        kiiras();
    }

    private void kiiras() {
        Cursor adatok = adatbazis.loggedin();

        StringBuilder builder = new StringBuilder();
        while (adatok.moveToNext()) {
            builder.append("Üdvözöllek ").append(adatok.getString(0)).append("\n\n");

            text_Felhnev_full.setText(builder.toString());
            Toast.makeText(this, "Sikeres bejelentkezés", Toast.LENGTH_SHORT).show();
        }
    }



   /*/ private void kiiras() {
        Cursor adatok = adatbazis.detaQuery(password);
        if (adatok == null) {
            Toast.makeText(this, "Hiba történt a lekérdezés során!", Toast.LENGTH_SHORT).show();
        }
        if (adatok.getCount() == 0);
        {
            Toast.makeText(this, "Még nincs felvéve adat!", Toast.LENGTH_SHORT).show();
            return;
        }

        //Toast.makeText( this, "Sikeres adat lekérdezés", Toast.LENGTH_SHORT).show();
     //    text_Felhnev_full.setText("Név: ");
        // Text vievba be kell olvasni a felh teljes nevét !!
    }*/



    private void listeners() {
        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent kilepes = new Intent(LoggedInActivity.this, MainActivity.class);
                startActivity(kilepes);
                finish();
            }
        });
    }


    private void init() {
        btn_logout = findViewById(R.id.btn_logout);
        text_Felhnev_full = findViewById(R.id.text_Felhnev_full);

        adatbazis = new DBHelper( LoggedInActivity.this);

    }
}
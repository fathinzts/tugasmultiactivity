package com.example.multiactivityf1d016026;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper databaseHelper;
    ListView listView;
    Button btnTambah;

    public static String nim_mhs="";
    public static boolean cekAksi = false;

    ArrayList<String> nama = new ArrayList<>();
    ArrayList<String> nim = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseHelper = new DatabaseHelper(this);
        listView = findViewById(R.id.listMhs);
        btnTambah = findViewById(R.id.btnTambah);

        Cursor data = databaseHelper.getDataMahasiswa();


        while (data.moveToNext()) {
            nama.add(data.getString(2));
            nim.add(data.getString(1));
        }

        final ArrayAdapter adapter = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1, nama);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                nim_mhs = nim.get(position);
                Intent i = new Intent(getApplicationContext(),UpdateActivity.class);
                startActivity(i);
                cekAksi=true;
            }
        });

        btnTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cekAksi=false;
                Intent i = new Intent(getApplicationContext(),UpdateActivity.class);
                startActivity(i);
            }
        });

    }
}

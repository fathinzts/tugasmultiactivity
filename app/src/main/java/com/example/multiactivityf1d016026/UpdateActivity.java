package com.example.multiactivityf1d016026;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {
    EditText nim,nm,alamat,nohp;
    DatabaseHelper databaseHelper;
    Button btnEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        getSupportActionBar().setTitle("Update Mahasiswa");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        databaseHelper = new DatabaseHelper(this);
        nim = findViewById(R.id.nim);
        nm = findViewById(R.id.nama);
        alamat = findViewById(R.id.alamat);
        nohp = findViewById(R.id.no_hp);
        btnEdit = findViewById(R.id.btnEdit);

        if (MainActivity.cekAksi) {
            Cursor data = databaseHelper.getDetailMahasiswa(MainActivity.nim_mhs);
            while (data.moveToNext()) {
                nim.setText(data.getString(1));
                nm.setText(data.getString(2));
                alamat.setText(data.getString(3));
                nohp.setText(data.getString(4));
            }
            nim.setEnabled(false);
            btnEdit.setText("Update");
        }else {
            nim.setEnabled(true);
            btnEdit.setText("Save");
        }

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MainActivity.cekAksi) {
                    databaseHelper.updateDataMahasiswa(nim.getText().toString(), nm.getText().toString(), alamat.getText().toString(), nohp.getText().toString());
                    Toast.makeText(getApplicationContext(), "Data berhasil di-update", Toast.LENGTH_LONG).show();
                }else {
                    databaseHelper.addDataMahasiswa(nim.getText().toString(), nm.getText().toString(), alamat.getText().toString(), nohp.getText().toString());
                    Toast.makeText(getApplicationContext(), "Data berhasil ditambah", Toast.LENGTH_LONG).show();
                }
                Intent i = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);

            }
        });
    }
}

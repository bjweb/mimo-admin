package com.bjweb.brunelljosny.mimoadmin;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Liste_transaction extends AppCompatActivity {

    public static final int REQUEST_ID_MULTIPLE_PERMISSIONS = 1;
    ListView listView;
    public static SQLiteHelper sqLiteHelper;
    final String[] from = new String[] {"_id","sender","statut","date","montant","trans_id"};
    final int[] to = new int[] {R.id.idi,R.id.sender, R.id.statut, R.id.date, R.id.montant, R.id.trans};
    private SimpleCursorAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_transaction);

        checkAndRequestPermissions();
        listView = findViewById(R.id.list);

        sqLiteHelper = new SQLiteHelper(this, "mimo.sqlite", null, 1);
        sqLiteHelper.queryData("CREATE TABLE IF NOT EXISTS liste(_id INTEGER PRIMARY KEY AUTOINCREMENT, sender VARCHAR, statut VARCHAR, date VARCHAR, montant VARCHAR, trans_id VARCHAR)");

        Cursor cursor = sqLiteHelper.getData("SELECT * FROM liste ORDER BY _id DESC");

        adapter = new SimpleCursorAdapter(this, R.layout.liste, cursor, from, to, 0);
        adapter.notifyDataSetChanged();

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new ListView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> a, View v, int i, long l) {
                final TextView trans = v.findViewById(R.id.trans);
            }
        });

    }

    private void checkAndRequestPermissions() {
        int internet = ContextCompat.checkSelfPermission(this, Manifest.permission.INTERNET);
        int sms = ContextCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_SMS);
        List<String> listPermissionsNeeded = new ArrayList<>();

        if (internet != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.INTERNET);
        }
        if (sms != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.RECEIVE_SMS);
        }
        if (!listPermissionsNeeded.isEmpty())
        {
            ActivityCompat.requestPermissions(this,listPermissionsNeeded.toArray
                    (new String[listPermissionsNeeded.size()]),REQUEST_ID_MULTIPLE_PERMISSIONS);
        }
    }
}

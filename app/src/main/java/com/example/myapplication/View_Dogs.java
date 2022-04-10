package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class View_Dogs extends AppCompatActivity {

    EditText name, contact, dob;
    Button insert, update, delete, view;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_dogs);
        getSupportActionBar().setTitle("Data Of Pet Dogs");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        name = findViewById(R.id.name);
        contact = findViewById(R.id.contact);
        dob = findViewById(R.id.dob);

        insert= findViewById(R.id.btnInsert);
        delete = findViewById(R.id.btnDelete);
        update = findViewById(R.id.btnUpdate);
        view =findViewById(R.id.btnView);

        DB = new DBHelper(this);
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameTXT= name.getText().toString();
                String contactTXT = contact.getText().toString();
                String dobTXT = dob.getText().toString();

                Boolean checkinsertdata = DB.insertuserdata(nameTXT, contactTXT, dobTXT);
                if(checkinsertdata == true)
                {
                    Toast.makeText(View_Dogs.this, "New Entry Inserted", Toast.LENGTH_SHORT).show();

                }
                else{
                    Toast.makeText(View_Dogs.this, "New Entry Not Inserted", Toast.LENGTH_SHORT).show();
                }
            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameTXT= name.getText().toString();
                String contactTXT = contact.getText().toString();
                String dobTXT = dob.getText().toString();

                Boolean checkupdatedata = DB.updateuserdata(nameTXT, contactTXT, dobTXT);
                if(checkupdatedata == true)
                {
                    Toast.makeText(View_Dogs.this, "  Entry Updated", Toast.LENGTH_SHORT).show();

                }
                else{
                    Toast.makeText(View_Dogs.this, "Entry Not Updated", Toast.LENGTH_SHORT).show();
                }
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameTXT= name.getText().toString();

                Boolean checkdeletedata = DB.deleteuserdata(nameTXT );
                if(checkdeletedata == true)
                {
                    Toast.makeText(View_Dogs.this, "  Entry Deleted", Toast.LENGTH_SHORT).show();

                }
                else{
                    Toast.makeText(View_Dogs.this, "Entry Not Deleted", Toast.LENGTH_SHORT).show();
                }
            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res= DB.getdata();

                if(res.getCount() ==  0 )
                {
                    Toast.makeText(View_Dogs.this, "No Entry Exists", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext())
                {
                    buffer.append("Society ID : " +res.getString(0)+ "\n" );
                    buffer.append("Society Name : " +res.getString(1)+ "\n" );
                    buffer.append("Wing Name : " +res.getString(2)+ "\n" );
                    buffer.append("Descrption : " +res.getString(3)+ "\n\n" );
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(View_Dogs.this);
                builder.setCancelable(true);
                builder.setTitle("User Entry ");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });
    }
}
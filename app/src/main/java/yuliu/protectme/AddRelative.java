package yuliu.protectme;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

//import android.support.v7.app.AppCompatActivity;

public class AddRelative extends AppCompatActivity {

    private static final int REQUEST_CALL = 1;
    DatabaseHelper myDB;
    Button btnAdd,btnView;
    EditText nameText, phoneText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_relative);
        nameText = (EditText) findViewById(R.id.name);
        phoneText = (EditText) findViewById(R.id.phone);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnView = (Button) findViewById(R.id.btnView);
        myDB = new DatabaseHelper(this);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameStr= nameText.getText().toString();
                String phoneStr = phoneText.getText().toString();
                if(nameStr.length() != 0 && phoneStr.length() != 0){
                    AddData(nameStr);
                    AddData(phoneStr);
                    nameText.setText("");
                    phoneText.setText("");
                }else{
                    Toast.makeText(AddRelative.this, "Name and phone number cannot be empty!", Toast.LENGTH_LONG).show();
                }
            }
        });

        btnView.setOnClickListener(new View.OnClickListener() {//view contacts in the database
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddRelative.this, ViewContacts.class);
                startActivity(intent);
            }
        });


    }

    public void AddData(String newData) {
        boolean success = myDB.addData(newData);

        if(success==true){
            Toast.makeText(this, "Data added!", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this, "Oops!:(", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        int action, keycode;
        action = event.getAction();
        keycode = event.getKeyCode();

        switch (keycode)
        {
            case KeyEvent.KEYCODE_VOLUME_UP:
                {
                if(KeyEvent.ACTION_UP == action){
                    Intent callIntent = new Intent(Intent.ACTION_CALL);
                    callIntent.setData(Uri.parse("tel:"));
                    if (ActivityCompat.checkSelfPermission(AddRelative.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(AddRelative.this, new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL);

                    } else {
                        callIntent.setData(Uri.parse("tel:"));
                        startActivity(callIntent);
                    }
                    startActivity(callIntent);
                }
            }
            case KeyEvent.KEYCODE_VOLUME_DOWN:
                if(KeyEvent.ACTION_DOWN == action){
                    Intent callIntent = new Intent(Intent.ACTION_CALL);
                    callIntent.setData(Uri.parse("tel:"));
                    if (ActivityCompat.checkSelfPermission(AddRelative.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(AddRelative.this, new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL);

                    } else {
                        callIntent.setData(Uri.parse("tel:"));
                        startActivity(callIntent);
                    }
                    startActivity(callIntent);
                }
        }
        return super.dispatchKeyEvent(event);
    }
}


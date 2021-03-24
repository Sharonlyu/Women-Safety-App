package yuliu.protectme;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;


public class ViewContacts extends AppCompatActivity {

    com.dan.naari.DatabaseHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewlistcontents_layout);

        ListView listView = (ListView) findViewById(R.id.listView);
        myDB = new com.dan.naari.DatabaseHelper(this);
        ArrayList<String> list = new ArrayList<>();
        Cursor data = myDB.getListContents();
        if(data.getCount() == 0){
            Toast.makeText(this, "No contacts!", Toast.LENGTH_LONG).show();
        }else{
            while(data.moveToNext()){
                list.add(data.getString(1));
                ListAdapter listAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1, list);
                listView.setAdapter(listAdapter);
            }
        }


    }
}

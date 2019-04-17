package com.example.messagerie;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class List extends AppCompatActivity {

    ListView list ;
    BDmessag db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        getActionBar().setTitle("LIST");
        list=findViewById(R.id.list);

        db=new BDmessag (this);
        ArrayList<Message>  messageArrayList =db.getAllMessag();

        AdapterClass adapter =new AdapterClass (this,messageArrayList);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long l) {

                Message message = (Message) parent.getItemAtPosition(position);
                int id = message.getId();

                Intent intent = new Intent(List.this,UpdateMess.class);
                intent.putExtra("id",id);
                startActivity(intent);

    }
});
    }
}
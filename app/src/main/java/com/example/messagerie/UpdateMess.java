package com.example.messagerie;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class UpdateMess extends AppCompatActivity {
    EditText mess,sender,time;
    Button edit;
    int id;
    BDmessag db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_mess);
        getActionBar().setTitle("SEND MESSAGE");
        final Intent intent = getIntent();
        id = intent.getIntExtra("id",0);

        db = new BDmessag(this);
        final Message message =db.getMessagById(id);


        mess.setText(message.getMess());
        sender.setText(message.getSender());
        time.setText(message.getTime());

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String msg = mess.getText().toString();
                String snd = sender.getText().toString();

                Message message = new Message(msg,snd,id);
                db.UpdateMessag(message);
                Toast.makeText(UpdateMess.this, "contact updated ...!", Toast.LENGTH_SHORT).show();

                Intent intent1 = new Intent(UpdateMess.this,List.class);
                startActivity(intent1);
            }
        });

    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.delete,menu);
        return super.onCreateOptionsMenu(menu);
    }
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.delete)
        {
            showPopUp();
        }
        return super.onOptionsItemSelected(item);
    }

    private void showPopUp() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setTitle("Press Yes to confirm")
                .setMessage("you want to delete this message !!")
                .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        db.DeleteMessag(id);
                        Toast.makeText(UpdateMess.this, "message deleted", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(UpdateMess.this,List.class);
                        startActivity(intent);
                    }

                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }



}

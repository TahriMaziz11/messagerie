package com.example.messagerie;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class SendMess extends AppCompatActivity {
EditText mess,sender,time_T;
Button send;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_mess);
        getActionBar().setTitle("SEND MESSAGE");
        time_T=findViewById(R.id.time);
        mess=findViewById(R.id.message) ;
        sender=findViewById(R.id.sender);
        send=findViewById(R.id.send);

        time_T.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar time=Calendar.getInstance();
                int hour =time.get(Calendar.HOUR_OF_DAY);
                int minute =time.get(Calendar.MINUTE);
                TimePickerDialog timePicker ;
                timePicker =new TimePickerDialog(SendMess.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timeP, int hourOfDay, int minute) { //timeP
                      time_T.setText(hourOfDay+":"+minute);
                    }
                },hour,minute,true);
              timePicker.setTitle("Select Time");
              timePicker.show();
            }
        });

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg=mess.getText().toString();
                String sender_1=sender.getText().toString();
                String time=time_T.getText().toString();
          Message message =new Message(msg,sender_1,time);
          BDmessag db=new BDmessag(SendMess.this);
          db.AjoutMessag(message);
                Toast.makeText(SendMess.this, " message is sent !!", Toast.LENGTH_SHORT).show();
                Intent intent =new Intent(SendMess.this,List.class);

                startActivity(intent);


            }
        });

    }
}

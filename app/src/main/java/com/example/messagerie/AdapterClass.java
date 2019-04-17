package com.example.messagerie;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class AdapterClass extends ArrayAdapter<Message> {

    Context ctx;

    public AdapterClass( Context context, List<Message> objects) {
        super(context, R.layout.model, objects);
        this.ctx=context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView= LayoutInflater.from(ctx).inflate(R.layout.model,parent,false);

        TextView time = convertView.findViewById(R.id.time);
        TextView message1 = convertView.findViewById(R.id.message1);
        TextView sender = convertView.findViewById(R.id.sender1);

        Message message = getItem(position);
        message1.setText(message.getMess());
        sender.setText(message.getSender());



        return convertView;
    }
}




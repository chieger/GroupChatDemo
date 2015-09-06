package com.example.charleshieger.groupchatdemo;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.parse.ParseObject;
import com.parse.ParseUser;

import java.util.List;


public class myAdapter extends ArrayAdapter<ParseObject> {
    public myAdapter (Context context, List<ParseObject> mChats) {
        super(context, 0, mChats);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ParseObject chat = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.chat_item, parent, false);
        }

        TextView tvMyUsername = (TextView) convertView.findViewById(R.id.tvMyUsername);
        TextView tvYourUsername = (TextView) convertView.findViewById(R.id.tvYourUsername);
        TextView tvChatField = (TextView) convertView.findViewById(R.id.tvChatField);

        String text = chat.getString("text");
        tvChatField.setText(text);

        ParseUser user = (ParseUser) chat.getParseObject("user");
        String myUsername = ParseUser.getCurrentUser().getUsername();
        String yourUsername = chat.getString("username");

        if (user != null) {
            if (user.equals(ParseUser.getCurrentUser())) {
                tvMyUsername.setVisibility(View.VISIBLE);
                tvYourUsername.setVisibility(View.GONE);
                tvMyUsername.setText(myUsername);
                tvChatField.setGravity(Gravity.RIGHT | Gravity.CENTER_VERTICAL);
                tvChatField.setPadding(0, 0, 16, 0);

            } else {
                tvMyUsername.setVisibility(View.GONE);
                tvYourUsername.setVisibility(View.VISIBLE);
                tvYourUsername.setText(yourUsername);
                tvChatField.setGravity(Gravity.LEFT | Gravity.CENTER_VERTICAL);
                tvChatField.setPadding(16, 0, 0, 0);
            }
        }
        return convertView;
    }
}

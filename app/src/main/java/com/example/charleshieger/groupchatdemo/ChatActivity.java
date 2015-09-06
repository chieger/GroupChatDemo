package com.example.charleshieger.groupchatdemo;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

public class ChatActivity extends AppCompatActivity {

    List<ParseObject> mChats;
    myAdapter adapter;
    ListView lvChatList;

    private Handler handler = new Handler();
    private boolean mFirstLoad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        mChats = new ArrayList<>();


        adapter = new myAdapter(this, mChats);
        lvChatList = (ListView) findViewById(R.id.lvChatList);
        lvChatList.setAdapter(adapter);

        lvChatList.setTranscriptMode(1);
        mFirstLoad = true;

        updateChats();

        handler.postDelayed(runnable, 500);
    }

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
        updateChats();

            handler.postDelayed(this, 500);
        }
    };

    public void onSend(View v) {
        EditText etChatField = (EditText) findViewById(R.id.etNewChatField);
        String newChat = etChatField.getText().toString();

        ParseObject chat = new ParseObject("Chat");
        chat.put("text", newChat);
        chat.put("user", ParseUser.getCurrentUser());
        chat.put("username", ParseUser.getCurrentUser().getUsername());
        chat.saveInBackground();

        etChatField.setText("");

        updateChats();

    }

    public void updateChats() {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Chat");

        query.orderByAscending("createdAt");

        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> chats, ParseException e) {
                if (e == null) {
                    mChats.clear();
                    mChats.addAll(chats);
                    adapter.notifyDataSetChanged();

                    if(mFirstLoad) {
                        lvChatList.setSelection(adapter.getCount());
                        mFirstLoad = false;
                    }


                } else {
                    Log.d("message", "Error: " + e.getMessage());
                }
            }
        });
    }

}

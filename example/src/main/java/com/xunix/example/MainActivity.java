package com.xunix.example;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.xunix.android_chat.Bean;
import com.xunix.android_chat.ChatActivity;

public class MainActivity extends ChatActivity{
    private static final Integer myMessage=1;
    private static final Integer yourMessage=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Pinkie Pie");
        sendMessage(new Bean("hello,my name is Pinkie Pie",yourMessage));
        sendMessage(new Bean("hello,my name is Apple Jack",myMessage));
    }
}

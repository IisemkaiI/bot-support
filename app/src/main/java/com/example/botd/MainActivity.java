package com.example.botd;

import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private EditText etMessage;
    private Button btnSend, btnMoodTracker;
    private List<Message> messages;
    private MessageAdapter adapter;
    private String userId = "user1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerViewMessages);
        etMessage = findViewById(R.id.etMessage);
        btnSend = findViewById(R.id.btnSend);

        messages = new ArrayList<>();
        adapter = new MessageAdapter(messages);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        messages.add(new Message("Привет я бот поддержки. Как ты себя чувствуешь?", false));
        adapter.notifyDataSetChanged();

        btnSend.setOnClickListener(v -> sendMessage());
    }

    private void sendMessage() {
        String text = etMessage.getText().toString().trim();
        if (text.isEmpty()) return;

        messages.add(new Message(text, true));
        adapter.notifyDataSetChanged();
        etMessage.setText("");

        new Handler().postDelayed(() -> {
            String response = BotLogic.getResponse(text, userId);

            messages.add(new Message(response, false));
            adapter.notifyDataSetChanged();
            recyclerView.scrollToPosition(messages.size() - 1);
        }, 1000);
    }
}
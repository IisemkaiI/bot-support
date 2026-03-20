package com.example.botd;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MessageViewHolder> {

    private static final int TYPE_BOT = 0;
    private static final int TYPE_USER = 1;

    private List<Message> messageList;

    public MessageAdapter(List<Message> messages) {
        this.messageList = messages;
    }

    @Override
    public int getItemViewType(int position) {
        Message msg = messageList.get(position);
        return msg.isFromUser() ? TYPE_USER : TYPE_BOT;
    }

    @Override
    public MessageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int layoutId;

        if (viewType == TYPE_USER) {
            layoutId = R.layout.item_message_user;
        } else {
            layoutId = R.layout.item_message_bot;
        }

        View view = LayoutInflater.from(parent.getContext())
                .inflate(layoutId, parent, false);

        return new MessageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MessageViewHolder holder, int position) {
        Message message = messageList.get(position);
        holder.messageText.setText(message.getText());
    }

    @Override
    public int getItemCount() {
        return messageList.size();
    }

    static class MessageViewHolder extends RecyclerView.ViewHolder {

        TextView messageText;

        MessageViewHolder(View itemView) {
            super(itemView);
            messageText = itemView.findViewById(R.id.tvMessage);
        }
    }
}

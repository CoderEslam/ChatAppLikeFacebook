package com.doubleclick.chatapp33;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import de.hdodenhof.circleimageview.CircleImageView;

public class FriendMyViewHolder extends RecyclerView.ViewHolder {


    CircleImageView profileImageUrl;
    TextView username,nghenghiep;
    public FriendMyViewHolder(@NonNull View itemView) {
        super(itemView);
        profileImageUrl  = itemView.findViewById(R.id.profileImageShowBanBe);
        username  = itemView.findViewById(R.id.usernameShowBanBe);
        nghenghiep  = itemView.findViewById(R.id.profession);
    }
}


package com.doubleclick.chatapp33;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.doubleclick.chatapp33.Utills.banBe;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.squareup.picasso.Picasso;

public class ShowAllUserChatActivity extends AppCompatActivity {

    FirebaseRecyclerOptions<banBe> options;
    FirebaseRecyclerAdapter<banBe, FriendMyViewHolder> adapter;
    Toolbar toolbar;
    RecyclerView recyclerView;
    FirebaseAuth mAuth;
    FirebaseUser mUser;
    DatabaseReference mRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all_user_chat);

        toolbar = findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Tin Nhắn");
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        mRef = FirebaseDatabase.getInstance().getReference().child("Friends");

        LoadFriends("");

    }

    private void LoadFriends(String s) {
        // tao 1 cau lenh
        Query query = mRef.child(mUser.getUid()).orderByChild("username").startAt(s).endAt(s+"\uf8ff");
        options = new FirebaseRecyclerOptions.Builder<banBe>().setQuery(query,banBe.class).build();
        adapter = new FirebaseRecyclerAdapter<banBe, FriendMyViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull FriendMyViewHolder holder, final int position, @NonNull banBe model) {
                Picasso.get().load(model.getProfileImageUrl()).into(holder.profileImageUrl);
                holder.username.setText(model.getUsername());
                holder.nghenghiep.setText(model.getNghenghiep());

                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
//                        Intent intent = new Intent(Danh_Sach_Ban_Be.this,ChatActivity.class);
//                        intent.putExtra("OtherUserID", getRef(position).getKey().toString());
//                        startActivity(intent);
                        Intent intent = new Intent(ShowAllUserChatActivity.this,ChatActivity.class);
                        intent.putExtra("OtherUserID", getRef(position).getKey().toString());
                        startActivity(intent);

                    }
                });
            }

            @NonNull
            @Override
            public FriendMyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.signle_view_friend,parent,false);
                return new FriendMyViewHolder(view);
            }
        };
        adapter.startListening();
        recyclerView.setAdapter(adapter);
    }
}
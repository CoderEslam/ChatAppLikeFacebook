package com.doubleclick.chatapp33;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity2 extends AppCompatActivity {

    FirebaseAuth mAuth;
    DatabaseReference mRef;
    FirebaseUser mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        // khoi tao chay man hinh logo cua phan mem len ( nhom QUAN Thong Bao )
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        mRef = FirebaseDatabase.getInstance().getReference().child("Users");



        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if(mUser!= null)
                {
                    mRef.child(mUser.getUid()).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if(snapshot.exists()){
                                Intent intent = new Intent(MainActivity2.this,MainActivity.class);
                                startActivity(intent);
                                finish();
                            }else{
                                Intent intent = new Intent(MainActivity2.this,SetupActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
                else if(mUser == null){
                    Intent intent = new Intent(MainActivity2.this,LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        };
        // thuc hien chuyen doi man hinh login sau 3100 ms
        Handler handler = new Handler();
        handler.postDelayed(runnable,2100);
    }
}
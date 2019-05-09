package com.example.firebase;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    private TextView textView;
    private FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
    private DatabaseReference mrootreference=firebaseDatabase.getReference();
    private DatabaseReference mchildReference=mrootreference.child("message");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView=findViewById(R.id.mgsText);
        //textView.setText("Firebase Message will appear here...");
    }

    @Override
    protected void onStart() {
        super.onStart();
        mchildReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String message=dataSnapshot.getValue(String.class);
                Log.i("shagul",message);
                textView.setText(message);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.i("Shagul",databaseError.toString());

            }
        });
    }
}

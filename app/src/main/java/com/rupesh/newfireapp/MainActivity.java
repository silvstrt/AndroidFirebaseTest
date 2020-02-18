package com.rupesh.newfireapp;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private FirebaseDatabase fireDatabase;
    private DatabaseReference fireRef;
    private ListView listView;
    private ArrayList<String> username = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Instantiate Firebase Database
        fireDatabase = FirebaseDatabase.getInstance();

        // Reference the location that we want to write to such as "MyMessage"
        fireRef = fireDatabase.getReference("Users");






        // Create a list view
        listView = (ListView) findViewById(R.id.list_view_main);

        // Next thing is to get value from Firebase and store in some kind of data structure
        // For this we have to use ArrayList
        // Then we need to have ArrayAdapter
        // ArrayAdapter creates connection between the ArrayList(which holds the data) and the ListView
        // (where data is supposed to be displayed)


        // First create the adapter
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, username);

        // Setup the adapter to that ListView
        listView.setAdapter(arrayAdapter);


        // Whenever tha value changes, that should be reflected in our database as well
        // So we are tracking child values, thus, addChildEventListener
        fireRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                String value = dataSnapshot.getValue(String.class);
                username.add(value);
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                username.remove(value);
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });










        // Adding ValueEventListener to the reference, it constantly listens to the reference
        // so whenever the value of that reference changes, we get notified of that particular
        // change

        /*fireRef.addValueEventListener(new ValueEventListener() {

            // What do we want to do when the data is changed
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                textView = findViewById(R.id.textView_main);
                textView.setText(value);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });*/


        // Adding another ValueEventListener

       /* fireRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Map<String, String> map = (Map<String, String>) dataSnapshot.getValue();
                String name = map.get("Name");
                String contact = map.get("Contact");

                Log.v("Name", name);
                Log.v("Contact", contact);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });*/

    }



    // Writing data to Firebase Database using button clicked
    public void buttonClicked(View view) {

        //EditText editTextName = findViewById(R.id.editText_main_name);
        //EditText editTextContact = findViewById(R.id.editText_main_contact);

        // Get reference to parent
        //fireRef = fireDatabase.getReference("Users");

        // Get reference to child and set the value
        //fireRef.child("Names").setValue(editTextName.getText().toString());


        // Adding 'push' compared to above code will retain the previous value and add the new value
        //fireRef.child("Tom").push().setValue(editTextName.getText().toString());


        // Get the reference to the child/ second level data which is Users in this case
        //String secondLevelUsers = editTextName.getText().toString();
        //fireRef = fireDatabase.getReference("Users").child(secondLevelUsers);


        // Then access the child data and set the values
        //fireRef.child("Name").setValue(editTextName.getText().toString());
        //fireRef.child("Contact").setValue(editTextContact.getText().toString());

    }
}

package com.dinhhuy.onthith;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class cart_real extends AppCompatActivity {
    static FirebaseDatabase database = FirebaseDatabase.getInstance("https://onthith-default-rtdb.asia-southeast1.firebasedatabase.app");
    static DatabaseReference myRef = database.getReference("cart");

    private static List<Plain> listPlant;
    private ListView lvMain;
    private cartAdapter cartAdapter;
    private static ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_real);
        listPlant = new ArrayList<Plain>();

        TextView btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(cart_real.this,List_Main.class);
                startActivity(intent);
            }
        });

        lvMain = findViewById(R.id.lvMain);

        cartAdapter = new cartAdapter(this, R.layout.item_cart, listPlant);
        lvMain.setAdapter(cartAdapter);
        progressDialog = new ProgressDialog(this);
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot data: snapshot.getChildren()) {
                    Plain plant = data.getValue(Plain.class);
                    listPlant.add(plant);
                }
                cartAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    static public void removeItem(Plain plain) {
        progressDialog.show();
        myRef.child(plain.getId() + "").removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    progressDialog.dismiss();
                } else {
                    Log.d("chua xoa roi", "onComplete: chua xong nef");
                }
            }
        });
    }
}



// listPlant = new ArrayList<Plain>();
//        cartAdapter = new cart_adapter();
//
//        rcvListCart = findViewById(R.id.rcvListPlant);
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
//        rcvListCart.setLayoutManager(linearLayoutManager);
//
//        rcvListCart.setAdapter(cartAdapter);
//
//        // Call data Async
//        myRef.addValueEventListener(new ValueEventListener() {
//@Override
//public void onDataChange(@NonNull DataSnapshot snapshot) {
//        for (DataSnapshot data: snapshot.getChildren()) {
//        Plain plant = data.getValue(Plain.class);
//        listPlant.add(plant);
//        }
//
//        cartAdapter.setData(listPlant, new cart_adapter.IClickRemoveListener() {
//@Override
//public void onClickRemoveItem(Button btnAdd, Plain plant) {
//        myRef.child(plant.getId() + "").removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
//@Override
//public void onComplete(@NonNull Task<Void> task) {
//        if (task.isSuccessful()) {
//        Toast.makeText(cart_real.this, "Remove Completed" , Toast.LENGTH_SHORT).show();
//        }
//        else {
//        Toast.makeText(
//        getApplicationContext(),
//        "SOS"
//        + " Please try again later",
//        Toast.LENGTH_LONG)
//        .show();
//        }
//        }
//        });
//        }
//        });
//        }
//
//@Override
//public void onCancelled(@NonNull DatabaseError error) {
//
//        }
//        });
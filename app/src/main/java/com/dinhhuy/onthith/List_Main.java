package com.dinhhuy.onthith;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class List_Main extends AppCompatActivity {

    private RecyclerView rcvList;
    private PlainAdapter plainAdapter;
    private List<Plain> arrPlant;
    FirebaseDatabase database = FirebaseDatabase.getInstance("https://onthith-default-rtdb.asia-southeast1.firebasedatabase.app");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_main);

        rcvList = findViewById(R.id.rcv_listPlant);
        arrPlant = new ArrayList<Plain>();
        arrPlant.add(new Plain(1, "Purple Shadow Plants", R.drawable.removebg, 25.0));
        arrPlant.add(new Plain(2, "Violet Shadow Plants", R.drawable.removebgpre, 25.0));
        arrPlant.add(new Plain(3, "Green Plants", R.drawable.removebg, 25.0));
        arrPlant.add(new Plain(4, "Green Leaf Plants", R.drawable.removebg, 9.0));

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        rcvList.setLayoutManager(linearLayoutManager);

        plainAdapter = new PlainAdapter();
        plainAdapter.setData(arrPlant, new PlainAdapter.IClickAddToCard() {
            @Override
            public void onClickAddToCard(Button btnAdd, Plain plain) {
                plain.setQuantity(1);
                DatabaseReference myRef = database.getReference("cart");
                String pathObject = String.valueOf(plain.getId());
                myRef.child(pathObject).setValue(plain).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(List_Main.this, "Add success", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(List_Main.this, "Add Fail", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        rcvList.setAdapter(plainAdapter);


        // See the list
        TextView tvSee = findViewById(R.id.txtSeeList);
        tvSee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(List_Main.this, cart_real.class);
                startActivity(intent);
            }
        });
    }

}
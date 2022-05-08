package com.dinhhuy.onthith;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class cartAdapter extends BaseAdapter {
    FirebaseDatabase database = FirebaseDatabase.getInstance("https://onthith-default-rtdb.asia-southeast1.firebasedatabase.app");
    DatabaseReference myRef = database.getReference("cart");
    private Context context;
    private List<Plain> listPlan;
    private int layout;

    public cartAdapter(Context context, int layout, List<Plain> listPlan) {
        this.context = context;
        this.listPlan = listPlan;
        this.layout = layout;
    }

    @Override
    public int getCount() {
        return listPlan.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        view = inflater.inflate(layout, null);

        TextView txtHeader = view.findViewById(R.id.txtName);
        ImageView imgMain = view.findViewById(R.id.imgMain);

        Plain plain = listPlan.get(i);
        txtHeader.setText(plain.getName());
        imgMain.setImageResource(plain.getResouurceImg());

        Button btnRemove = view.findViewById(R.id.button3);
        btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cart_real.removeItem(listPlan.get(i));
                Intent intent = new Intent(context,cart_real.class);
                context.startActivity(intent);
            }
        });

        return view;
    }
}

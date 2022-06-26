package com.example.savitrijewellersadmin.order;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import com.bumptech.glide.Glide;
import com.example.savitrijewellersadmin.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class OrderhistoryAdaptor extends ArrayAdapter<Order> {

    // constructor for our list view adapter.
    public Context context;
    private final FirebaseFirestore db = FirebaseFirestore.getInstance();
    private final FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private DocumentReference docrefprice;


    public OrderhistoryAdaptor(@NonNull Context context, ArrayList<Order> dataModalArrayList) {
        super(context, 0, dataModalArrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // below line is use to inflate the
        // layout for our item of list view.
        View listitemView = convertView;
        if (listitemView == null) {
            listitemView = LayoutInflater.from(getContext()).inflate(R.layout.order_history_list, parent, false);
        }

        // after inflating an item of listview item
        // we are getting data from array list inside
        // our modal class.
        Order order = getItem(position);

        // initializing our UI components of list view item.




        TextView   amt = listitemView.findViewById(R.id.order_history_amt);

        ImageView image =listitemView.findViewById(R.id.order_history_image);

        TextView   orderno = listitemView.findViewById(R.id.order_history_orderno);
        TextView     status = listitemView.findViewById(R.id.order_history_status);
        TextView   date = listitemView.findViewById(R.id.order_history_date);
        TextView    count = listitemView.findViewById(R.id.order_history_itemcount);
        TextView    ins = listitemView.findViewById(R.id.order_history_ins);


        amt.setText("₹ "+order.getPaymentamt());
        orderno.setText(order.getOrderno());
        status.setText(order.getOrderstatus());
        date.setText(order.getDate());
        count.setText("Item(s) "+String.valueOf(order.getCount()));

        String instraction=order.getCouriername();

        if (instraction.isEmpty()){
            ins.setVisibility(View.GONE);
        }else {
            ins.setVisibility(View.VISIBLE);
            ins.setText(order.getCouriername()+" "+order.getTrackingid());
        }









        listitemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // on the item click on our list view.
                // we are displaying a toast message.
                //context.startActivity(new Intent(context, Exo_player.class));
            //    Intent intent=new Intent(v.getContext(),Order_history_details.class);
            //    intent.putExtra("itemid",order.getItemid());


            //    v.getContext().startActivity(intent);


                Intent intent=new Intent(v.getContext(),Order_history_details.class);
                intent.putExtra("itemid",order.getItemid());


                v.getContext().startActivity(intent);


                //  Toast.makeText(getContext(), "Item clicked is : " + dataModal.getName(), Toast.LENGTH_SHORT).show();
            }
        });
















        return listitemView;
    }
}
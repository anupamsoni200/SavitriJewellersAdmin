package com.example.savitrijewellersadmin.order;

import android.content.Context;
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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class OrderdetailAdaptor extends ArrayAdapter<Cart> {

    // constructor for our list view adapter.
    public Context context;
    private final FirebaseFirestore db = FirebaseFirestore.getInstance();
    private final FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private DocumentReference docrefprice;


    public OrderdetailAdaptor(@NonNull Context context, ArrayList<Cart> dataModalArrayList) {
        super(context, 0, dataModalArrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // below line is use to inflate the
        // layout for our item of list view.
        View listitemView = convertView;
        if (listitemView == null) {
            listitemView = LayoutInflater.from(getContext()).inflate(R.layout.order_detail_item, parent, false);
        }

        // after inflating an item of listview item
        // we are getting data from array list inside
        // our modal class.
        Cart cart = getItem(position);

        // initializing our UI components of list view item.


        TextView name = listitemView.findViewById(R.id.od_item_name);

        ImageView image = listitemView.findViewById(R.id.od_item_image);

        TextView weight = listitemView.findViewById(R.id.od_item_weight);
        TextView metal = listitemView.findViewById(R.id.od_item_metal);
        TextView metalname = listitemView.findViewById(R.id.od_item_metalname);
        TextView purity = listitemView.findViewById(R.id.od_item_purity);
        TextView goldprice = listitemView.findViewById(R.id.od_item_itemprice);
        TextView makingprice = listitemView.findViewById(R.id.od_item_making_charge);
        TextView discount = listitemView.findViewById(R.id.od_item_discount);
        TextView grandprice = listitemView.findViewById(R.id.od_item_grandtotal);



        name.setText(cart.getItemid());
        weight.setText(String.valueOf(cart.getWeight())+" g");
        makingprice.setText("₹ "+String.valueOf(cart.getMakingcharge()));
        discount.setText("₹ "+String.valueOf(cart.getDiscount()));
        goldprice.setText("₹ "+String.valueOf(cart.getItemprice()));
        grandprice.setText("₹ "+String.valueOf(cart.getTotalprice()));
        metal.setText(cart.getType());
        purity.setText(cart.getPurity());

        metalname.setText(cart.getType());

        Glide.with(getContext()).load(cart.getImage()).placeholder(R.drawable.cargo).into(image);









        return listitemView;
    }
}
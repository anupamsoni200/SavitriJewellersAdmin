package com.example.savitrijewellersadmin.order;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.savitrijewellersadmin.MainActivity;
import com.example.savitrijewellersadmin.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class OrderActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseUser mCurrentUser;
    ArrayList<Order> dataModalArrayList;
    private DocumentReference docRef;
    private FirebaseFirestore db;

    private GridView orderlist;
    private OrderhistoryAdaptor orderhistoryAdaptor;


    private DocumentReference docrefprice;

    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        mAuth = FirebaseAuth.getInstance();
        mCurrentUser = mAuth.getCurrentUser();
        db = FirebaseFirestore.getInstance();

        dataModalArrayList = new ArrayList<>();


        // place_order =findViewById(R.id.cart_place_order);

        orderlist = findViewById(R.id.order_history_list);


        progressBar = findViewById(R.id.user_order_history_progressbar);


        Showdetails();


    }

    private void Showdetails() {


        db.collection("OrderReceive").orderBy("date", Query.Direction.DESCENDING).get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        // after getting the data we are calling on success method
                        // and inside this method we are checking if the received
                        // query snapshot is empty or not.
                        if (!queryDocumentSnapshots.isEmpty()) {
                            // if the snapshot is not empty we are hiding
                            // our progress bar and adding our data in a list.
                            List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                            for (DocumentSnapshot d : list) {
                                // after getting this list we are passing
                                // that list to our object class.

                                Order order = d.toObject(Order.class);

                                String itemid = d.getId();
                                order.setItemid(itemid);
                                dataModalArrayList.add(order);

                                //  Toast.makeText(User_order_history.this, d.getString("method"), Toast.LENGTH_SHORT).show();


                            }
                            // after that we are passing our array list to our adapter class.

                            OrderhistoryAdaptor adapter = new OrderhistoryAdaptor(OrderActivity.this, dataModalArrayList);

                            // after passing this array list to our adapter
                            // class we are setting our adapter to our list view.

                            orderlist.setAdapter(adapter);

                        } else {
                            // if the snapshot is empty we are displaying a toast message.
                            Toast.makeText(OrderActivity.this, "No Order History Found", Toast.LENGTH_SHORT).show();
                        }

                        progressBar.setVisibility(View.GONE);
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                // we are displaying a toast message
                // when we get any error from Firebase.
                Toast.makeText(OrderActivity.this, "Fail to load data..", Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public void onBackPressed() {

        Intent intent = new Intent(OrderActivity.this, MainActivity.class);
        startActivity(intent);

    }
}
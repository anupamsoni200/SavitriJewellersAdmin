package com.example.savitrijewellersadmin.order;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import com.example.savitrijewellersadmin.MainActivity;
import com.example.savitrijewellersadmin.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Order_history_details extends AppCompatActivity {


    private TextView order_id;
    private TextView order_place;
    private TextView order_total;
    private TextView order_total_word;
    private TextView order_payment_method;
    private TextView order_name;
    private TextView order_address;
    private TextView order_phone_no;
    private TextView order_courier_name;
    private TextView order_tracking_no;
    private TextView order_price_heading;
    private TextView order_total_item_price;
    private TextView order_total_item_discount;
    private TextView order_grand_price;
    private TextView order_total_item_making_charge;

    private TextView order_status;
    private TextView order_user_req;

    private Button order_cancel;
    private Button order_exchange;

    private String itemid;


    private FirebaseFirestore db;
    private DocumentReference orderdocRef;
    private DocumentReference userdocRef;

    private FirebaseAuth mAuth;
    private FirebaseUser mCurrentUser;
    private ArrayList<Cart> dataModalArrayList;
    private ExpandableHeightGridView orderitemlist;
    private OrderdetailAdaptor orderdetailAdaptor;

    private Integer purchaseTotal;
    private Integer makingtotal;
    private Integer discounttotal;
    private double totalprice;
    private Integer makingcharge;

    private ProgressBar progressBar;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_history_details);


        Intent intent = getIntent();
        itemid = intent.getStringExtra("itemid");

        mAuth = FirebaseAuth.getInstance();
        mCurrentUser = mAuth.getCurrentUser();
        db = FirebaseFirestore.getInstance();

        dataModalArrayList = new ArrayList<>();

        orderitemlist=findViewById(R.id.od_item_list);
        orderitemlist.setExpanded(true);

        progressBar=findViewById(R.id.order_history_detail_progressbar);


        order_id=findViewById(R.id.od_orderid);
        order_place=findViewById(R.id.od_order_date);
        order_total=findViewById(R.id.od_order_amt);
        order_total_word=findViewById(R.id.od_order_amt_word);
        order_payment_method=findViewById(R.id.od_order_paymentmode);
        order_status=findViewById(R.id.od_order_status);
        order_user_req=findViewById(R.id.od_order_request);


        order_name=findViewById(R.id.od_user_name);
        order_address=findViewById(R.id.od_user_address);
        order_phone_no=findViewById(R.id.od_phone_no);


        order_courier_name=findViewById(R.id.od_courier_name);
        order_tracking_no=findViewById(R.id.od_tracking_number);



        order_price_heading=findViewById(R.id.od_price_detail_heading);
        order_total_item_price=findViewById(R.id.od_total_price);
        order_total_item_discount=findViewById(R.id.od_total_discount);
        order_total_item_making_charge=findViewById(R.id.od_total_making_charge);
        order_grand_price=findViewById(R.id.od_grand_final_price);


        order_cancel=findViewById(R.id.od_cancel_button);
        order_exchange=findViewById(R.id.od_exchange_button);


        order_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                usercancelreq();
            }
        });


        order_exchange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                usercancelexchnage();
            }
        });

        ShowDetail();
        ShowUserDetail();
        Orderitemlist();
    }

    private void usercancelexchnage() {

        Map<String, Object> city = new HashMap<>();

        city.put("userreq","Exchange");
        DocumentReference userpayref = db.collection("users").document(mCurrentUser.getPhoneNumber()).collection("OrderReceive").document(itemid);
        DocumentReference adminpayref = db.collection("OrderReceive").document(itemid);


        userpayref
                .update(city)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "DocumentSnapshot successfully updated!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error updating document", e);
                    }
                });


        adminpayref
                .update(city)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "DocumentSnapshot successfully updated!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error updating document", e);
                    }
                });


        Intent intent=new Intent(Order_history_details.this, MainActivity.class);
        startActivity(intent);
        finish();

    }

    private void usercancelreq() {

        Map<String, Object> city = new HashMap<>();

        city.put("userreq","Cancel");
        DocumentReference userpayref = db.collection("users").document(mCurrentUser.getPhoneNumber()).collection("OrderReceive").document(itemid);
        DocumentReference adminpayref = db.collection("OrderReceive").document(itemid);


        userpayref
                .update(city)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "DocumentSnapshot successfully updated!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error updating document", e);
                    }
                });


        adminpayref
                .update(city)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "DocumentSnapshot successfully updated!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error updating document", e);
                    }
                });


        Intent intent=new Intent(Order_history_details.this,MainActivity.class);
        startActivity(intent);
        finish();

    }

    private void ShowUserDetail() {
       // userdocRef= db.collection("OrderReceive").document(itemid).collection("usersdetails");

        db.collection("OrderReceive").document(itemid).collection("usersdetails")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {

                                order_name.setText(document.getString("name"));
                                order_address.setText(document.getString("address")+" \n"+document.getString("city")+" ,"+document.getString("state")+" \n"+document.getString("pincode"));
                                order_phone_no.setText(document.getString("phone"));

                            }
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });


    }

    private void ShowDetail() {


        orderdocRef= db.collection("OrderReceive").document(itemid);

        orderdocRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document != null) {


                        order_id.setText(document.getString("orderno"));
                        order_place.setText(document.getString("date"));
                        order_total.setText("₹ "+document.getString("paymentamt")+" ("+document.getLong("count").intValue()+"item(s))");

                        order_price_heading.setText("Price Details ("+document.getLong("count").intValue()+" Items(s))");

                        String return_val_in_english =  Currency.convertToIndianCurrency(document.getString("paymentamt"));
                        String amtword=return_val_in_english.substring(0, 1).toUpperCase() + return_val_in_english.substring(1).toLowerCase();
                        order_total_word.setText(amtword);


                        order_payment_method.setText(document.getString("method")+"\nTransaction Id : "+document.getString("transactionid"));

                        order_courier_name.setText(document.getString("couriername"));
                        order_tracking_no.setText(document.getString("trackingid"));

                        order_status.setText(document.getString("orderstatus"));
                        order_user_req.setText(document.getString("userreq"));


                    } else {
                        Log.d("LOGGER", "No such document");
                    }
                } else {
                    Log.d("LOGGER", "get failed with ", task.getException());
                }
            }
        });


    }


    private void Orderitemlist() {
        // below line is use to get data from Firebase
        // firestore using collection in android.
        db.collection("OrderReceive").document(itemid).collection("OrderItems").get()
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

                                Cart cart = d.toObject(Cart.class);


                                dataModalArrayList.add(cart);

                                //  Toast.makeText(User_order_history.this, d.getString("method"), Toast.LENGTH_SHORT).show();


                            }
                            // after that we are passing our array list to our adapter class.

                            orderdetailAdaptor = new OrderdetailAdaptor(Order_history_details.this, dataModalArrayList);

                            // after passing this array list to our adapter
                            // class we are setting our adapter to our list view.

                            orderitemlist.setAdapter(orderdetailAdaptor);

                            updateUI();

                        } else {
                            // if the snapshot is empty we are displaying a toast message.
                            Toast.makeText(Order_history_details.this, "No Order History Found", Toast.LENGTH_SHORT).show();
                        }

                         progressBar.setVisibility(View.GONE);
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                // we are displaying a toast message
                // when we get any error from Firebase.
                Toast.makeText(Order_history_details.this, "Fail to load data..", Toast.LENGTH_SHORT).show();
            }
        });
    }



    public void updateUI() {

        orderdetailAdaptor.notifyDataSetChanged();












        if (!dataModalArrayList.isEmpty()) {

            // Update and display purchase total.
            purchaseTotal = 0;
            makingtotal=0;
            discounttotal=0;
            makingcharge=0;
            NumberFormat formatter = NumberFormat.getCurrencyInstance();

            for (Cart item : dataModalArrayList) {

                userdocRef= db.collection("Price").document(item.getType());

                userdocRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if (document != null) {





                                int cal_item_metal_price=document.getLong("price").intValue()/10;


                                double cal_item_weight_price=cal_item_metal_price* item.getWeight();


                                double cal_without_discount=cal_item_weight_price+ item.getMakingcharge();
                                double cal_item_final_price=cal_item_weight_price+ item.getMakingcharge()-item.getDiscount();



                                //  int intcal_item_weight_price = (int)cal_item_weight_price;
                                int intcal_item_final_price = (int)cal_item_final_price;

                                makingcharge+=item.getMakingcharge();

                              //  int without_discount=(int)cal_without_discount;





                                discounttotal+=item.getDiscount();
                                makingtotal+=item.getMakingcharge();
                                totalprice+=cal_without_discount;

                                purchaseTotal+=intcal_item_final_price;






                                String stringTotal = "₹ " + purchaseTotal;


                                order_grand_price.setText("₹ " +purchaseTotal);
                                order_total_item_price.setText("₹ " +totalprice);
                                order_total_item_discount.setText("₹ " +discounttotal);
                                order_total_item_making_charge.setText("₹ " +makingcharge);


















                            } else {
                                Log.d("LOGGER", "No such document");
                            }
                        } else {
                            Log.d("LOGGER", "get failed with ", task.getException());
                        }
                    }
                });











            }



        } else {

        }
    }


    @Override
    public void onBackPressed()
    {

        Intent intent=new Intent(Order_history_details.this, OrderActivity.class);
        startActivity(intent);
        finish();
    }

}
package com.example.savitrijewellersadmin.price;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.savitrijewellersadmin.MainActivity;
import com.example.savitrijewellersadmin.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class PriceActivity extends AppCompatActivity {

    private TextInputLayout goldprice,silverprice;
    private Button submit;
    private FirebaseFirestore db;
    private DocumentReference goldref,silverref;
    private FirebaseAuth mAuth;
    private FirebaseUser mCurrentUser;
    private TextView displaygold,displaysilver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_price);

        mAuth = FirebaseAuth.getInstance();
        mCurrentUser = mAuth.getCurrentUser();
        db = FirebaseFirestore.getInstance();

        displaygold=findViewById(R.id.display_gold_price);
        displaysilver=findViewById(R.id.display_silver_price);

        goldprice=findViewById(R.id.gold_price);
        silverprice=findViewById(R.id.silver_price);

        submit=findViewById(R.id.price_submit_button);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                String goldfinalprice=goldprice.getEditText().getText().toString();
                String silverfinalprice=silverprice.getEditText().getText().toString();

                if (goldfinalprice.isEmpty()){

                    Toast.makeText(PriceActivity.this, "Please Enter Gold Price",
                            Toast.LENGTH_LONG).show();
                }else if(silverfinalprice.isEmpty())
                {Toast.makeText(PriceActivity.this, "Please Enter Silver Price",
                        Toast.LENGTH_LONG).show();

                }else {

                    Map<String, Object> silveramt = new HashMap<>();
                    silveramt.put("price", Integer.parseInt(silverfinalprice));
                    db.collection("Price").document("Silver")
                            .update(silveramt)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Log.d(TAG, "DocumentSnapshot successfully written!");

                                    silverprice.getEditText().setText("");
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.w(TAG, "Error writing document", e);
                                }
                            });




                    Map<String, Object> goldramt = new HashMap<>();
                    goldramt.put("price", Integer.parseInt(goldfinalprice));
                    db.collection("Price").document("Gold")
                            .update(goldramt)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Log.d(TAG, "DocumentSnapshot successfully written!");


                                    goldprice.getEditText().setText("");
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.w(TAG, "Error writing document", e);
                                }
                            });


                    Intent intent=new Intent(PriceActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }




            }
        });

        goldrate();


    }

    private void goldrate() {
        goldref = db.collection("Price").document("Gold");
        silverref = db.collection("Price").document("Silver");

        goldref.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document != null) {


                        displaygold.setText("₹ "+document.getLong("price").intValue()+" /10 gram");

                        //    int cal_item_metal_price = document.getLong("price").intValue() / 10;



                    } else {
                        Log.d("LOGGER", "No such document");
                    }
                } else {
                    Log.d("LOGGER", "get failed with ", task.getException());
                }

            }
        });



        silverref.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document != null) {


                        displaysilver.setText("₹ "+document.getLong("price").intValue()+" /10 gram");

                        //    int cal_item_metal_price = document.getLong("price").intValue() / 10;



                    } else {
                        Log.d("LOGGER", "No such document");
                    }
                } else {
                    Log.d("LOGGER", "get failed with ", task.getException());
                }

            }
        });


    }

}
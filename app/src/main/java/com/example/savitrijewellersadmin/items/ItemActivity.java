package com.example.savitrijewellersadmin.items;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.savitrijewellersadmin.MainActivity;
import com.example.savitrijewellersadmin.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import id.zelory.compressor.Compressor;

public class ItemActivity extends AppCompatActivity {

    private ImageView chooseimage;
    private AutoCompleteTextView item_menu,item_category;
    private FirebaseFirestore db;
    private DocumentReference docRef;
    private FirebaseAuth mAuth;
    private FirebaseUser mCurrentUser;
    private ImageView catimage;
    private TextInputLayout colorcode,category,name;
    private final int PICK_IMAGE_REQUEST = 22;
    FirebaseStorage storage;
    StorageReference storageReference;
    private String imagelink;

    private String imageUrl;

    private Button cat_submit;
    private ImageView cat_image;
    private Uri filePath;

    private static final int PICK_IMAGE = 1;
    Button chooserBtn, uploaderBtn;
    TextView alert;
    private Uri ImageUri;
    ArrayList ImageList = new ArrayList();
    private int upload_count = 0;
    private ProgressDialog progressDialog;
    ArrayList urlStrings;

    private Button item_submit;
    private String itemidno;
    List<String> subjects;
    ArrayAdapter<String> catadapter;

    private TextInputLayout itemname,itempurity,itemmaking,itemdiscount,itemweight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);




        mAuth = FirebaseAuth.getInstance();
        mCurrentUser = mAuth.getCurrentUser();

        db = FirebaseFirestore.getInstance();




        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
        itemname=findViewById(R.id.item_name);
        itempurity=findViewById(R.id.item_purity);
        itemmaking=findViewById(R.id.item_makingcharge);
        itemdiscount=findViewById(R.id.item_discount);
        itemweight=findViewById(R.id.item_weight);



        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("ddMMyyyy");
        String date = df.format(c.getTime());
        Random rand = new Random();
        int min = 1000, max = 9999;
// nextInt as provided by Random is exclusive of the top value so you need to add 1
        int randomNum = rand.nextInt((max - min) + 1) + min;



        itemidno=date + String.valueOf(randomNum);

        chooseimage = findViewById(R.id.item_img1);
        chooseimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                startActivityForResult(intent, PICK_IMAGE_REQUEST);
            }
        });

        item_submit = findViewById(R.id.item_submit);
        item_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                urlStrings = new ArrayList<>();
                progressDialog = ProgressDialog.show(ItemActivity.this, "Loading Group","Please wait!!", true);
//                alert.setText("If Loading Takes to long press button again");
                StorageReference ImageFolder = FirebaseStorage.getInstance().getReference().child("JewelleryImage");

                for (upload_count = 0; upload_count < ImageList.size(); upload_count++) {



                    Uri IndividualImage = (Uri) ImageList.get(upload_count);
                    final StorageReference ImageName = ImageFolder.child("Jewellery"  + UUID.randomUUID().toString());

                    ImageName.putFile(IndividualImage).addOnSuccessListener(
                            new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                @Override
                                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                    ImageName.getDownloadUrl().addOnSuccessListener(
                                            new OnSuccessListener<Uri>() {
                                                @Override
                                                public void onSuccess(Uri uri) {
                                                    urlStrings.add(String.valueOf(uri));


                                                    if (urlStrings.size() == ImageList.size()) {
                                                        storeLink(urlStrings);
                                                    }


                                                }
                                            }
                                    );
                                }
                            }
                    );


                }


            }
        });

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, COUNTRIES);
        item_menu = findViewById(R.id.item_type);
        item_menu.setAdapter(adapter);


        item_menu.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View arg1, int pos,
                                    long id) {
                categoryselect();

            }
        });



        item_category=findViewById(R.id.item_category);

        subjects = new ArrayList<>();
         catadapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, subjects);
        catadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        item_category.setAdapter(catadapter);








    }

    private static final String[] COUNTRIES = new String[] {
            "Gold", "Silver"
    };


    private void categoryselect(){


        catadapter.clear();
        db.collection("Category")
                .whereEqualTo("type", item_menu.getText().toString())
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                              //  Log.d(TAG, document.getId() + " => " + document.getData());

                               // String category=document.getString("category");



                                String subject = document.getString("category");
                                subjects.add(subject);







                            }
                            catadapter.notifyDataSetChanged();
                        } else {
                           // Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST) {
            if (resultCode == RESULT_OK) {


                if (data.getClipData() != null) {

                    int countClipData = data.getClipData().getItemCount();
                    int currentImageSlect = 0;

                    while (currentImageSlect < countClipData) {

                        ImageUri = data.getClipData().getItemAt(currentImageSlect).getUri();



                        ImageList.add(ImageUri);
                        currentImageSlect = currentImageSlect + 1;
                    }

                 //   alert.setVisibility(View.VISIBLE);
                 //   alert.setText("You have selected" + ImageList.size() + "Images");
                 //   chooserBtn.setVisibility(View.GONE);


                } else {
                    Toast.makeText(this, "Please Select Multiple Images", Toast.LENGTH_SHORT).show();
                }

            }
        }
    }

    private void storeLink(ArrayList<String> urlStrings) {

        HashMap<String, Object> hashMap = new HashMap<>();


        for (int i = 0; i <urlStrings.size() ; i++) {

                     hashMap.put("img"+i, urlStrings.get(i));


        }



        hashMap.put("type",item_menu.getText().toString());
        hashMap.put("category",item_category.getText().toString());
        hashMap.put("name",itemname.getEditText().getText().toString());
        hashMap.put("discount",Integer.parseInt(itemdiscount.getEditText().getText().toString()));
        hashMap.put("makingcharge",Integer.parseInt(itemmaking.getEditText().getText().toString()));
        hashMap.put("purity",itempurity.getEditText().getText().toString());
        hashMap.put("weight",Double.parseDouble(itemweight.getEditText().getText().toString()));
        hashMap.put("date",String.valueOf(new SimpleDateFormat("dd-MM-yyyy HH:mm").format(Calendar.getInstance().getTime())));



        db.collection("Jewellery").document(itemidno)
                .set(hashMap)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                      //  Log.d(TAG, "DocumentSnapshot successfully written!");

                        progressDialog.dismiss();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                      //  Log.w(TAG, "Error writing document", e);
                    }
                });


        Intent intent=new Intent(ItemActivity.this,MainActivity.class);
        startActivity(intent);
    }
}
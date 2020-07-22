package com.elnaranjo.pibi2goserver;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.elnaranjo.pibi2goserver.Common.Common;
import com.elnaranjo.pibi2goserver.Model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;

public class SignIn extends AppCompatActivity {

    EditText edtPhone,edtPaswword;
    Button btnSignIn;

    FirebaseDatabase db;
    DatabaseReference users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        edtPaswword = (MaterialEditText)findViewById(R.id.edtPassword);
        edtPhone = (MaterialEditText)findViewById(R.id.edtPhone);
        btnSignIn = (Button)findViewById(R.id.btnSignIn);

        //Iniciemos el firebase
        db = FirebaseDatabase.getInstance();
        users = db.getReference("User");

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signInUser(edtPhone.getText().toString(),edtPaswword.getText().toString());
            }
        });


    }

    private void signInUser(String phone, String password) {
        final ProgressDialog mDialog = new ProgressDialog(SignIn.this);
        mDialog.setMessage("Espere por favor....");
        mDialog.show();
        final String localphone = phone;
        final String localpassword = password;
        users.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.child(localphone).exists()){
                    mDialog.dismiss();
                    User user = snapshot.child(localphone).getValue(User.class);
                    user.setPhone(localphone);
                    if (Boolean.parseBoolean(user.getIsStaff()))
                    {
                        if(user.getPassword().equals(localpassword))
                        {
                          Intent login = new Intent(SignIn.this,Home.class);
                          Common.currentUser = user;
                          startActivity(login);
                          finish();

                        }
                        else
                            Toast.makeText(SignIn.this, "Password incorrecto", Toast.LENGTH_SHORT).show();
                    }
                    else
                        Toast.makeText(SignIn.this, "Ingrese con cuenta de usuario", Toast.LENGTH_SHORT).show();
                }
                else{
                    mDialog.dismiss();
                    Toast.makeText(SignIn.this, "Usuario incorrecto", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
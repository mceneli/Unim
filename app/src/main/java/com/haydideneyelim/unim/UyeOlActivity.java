package com.haydideneyelim.unim;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class UyeOlActivity extends AppCompatActivity {
    private FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uye_ol);

        auth = FirebaseAuth.getInstance();
        Button uyeButton = findViewById(R.id.uyeOlButton);

        uyeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText uyeEmail = findViewById(R.id.emailText);
                EditText uyeParola = findViewById(R.id.parolaText);

                String email = uyeEmail.getText().toString();
                String parola = uyeParola.getText().toString();

                if(TextUtils.isEmpty(email)){
                    Toast.makeText(getApplicationContext(),"Lütfen emailinizi giriniz",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(parola)){
                    Toast.makeText(getApplicationContext(),"Lütfen parolanızı giriniz",Toast.LENGTH_SHORT).show();
                }
                if (parola.length()<6){
                    Toast.makeText(getApplicationContext(),"Parola en az 6 haneli olmalıdır",Toast.LENGTH_SHORT).show();
                }

                auth.createUserWithEmailAndPassword(email,parola)
                        .addOnCompleteListener(UyeOlActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()) {
                            Toast.makeText(UyeOlActivity.this, "Yetkilendirme Hatası",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            startActivity(new Intent(UyeOlActivity.this, MainActivity.class));
                            finish();
                        }

                    }
                });

            }
        });



    }
}

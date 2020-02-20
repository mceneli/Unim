package com.haydideneyelim.unim;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class YeniParolaActivity extends AppCompatActivity {
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yeni_parola);

        final EditText emailSifirla = findViewById(R.id.emailSifirlaText);
        Button yeniparolaButton = findViewById(R.id.yeniParolaButton);

        auth = FirebaseAuth.getInstance();

        yeniparolaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mail = emailSifirla.getText().toString();

                if (TextUtils.isEmpty(mail)) {
                    Toast.makeText(getApplication(), "Lütfen email adresinizi giriniz", Toast.LENGTH_SHORT).show();
                    return;
                }

                auth.sendPasswordResetEmail(mail).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(YeniParolaActivity.this, "Yeni parola için gerekli bağlantı adresinize gönderildi!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(YeniParolaActivity.this, "Mail gönderme hatası!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });

    }
}

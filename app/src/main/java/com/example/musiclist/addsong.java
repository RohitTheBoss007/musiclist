package com.example.musiclist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class addsong extends AppCompatActivity {

    private DatabaseReference reference;
    private EditText title,desc;
    private Button but2;

    private ProgressDialog loader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addsong);
        title = findViewById(R.id.title);
        desc = findViewById(R.id.desc);
        but2 = findViewById(R.id.but2);

        reference = FirebaseDatabase.getInstance().getReference().child("msongs");

        loader = new ProgressDialog(this);

        but2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mtitle = title.getText().toString().trim();
                String mdesc = desc.getText().toString().trim();
                String mid =  reference.push().getKey();

                if(TextUtils.isEmpty(mtitle)){
                    Toast.makeText(getApplicationContext(),"Title can not be empty",Toast.LENGTH_SHORT).show();
                    return;
                }

                loader.setMessage("Adding song to list...");
                loader.setCanceledOnTouchOutside(false);
                loader.show();

                model mod = new model(mtitle,mdesc,mid);
                reference.child(mid).setValue(mod).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(getApplicationContext(),
                                    "Song added to list!",
                                    Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(addsong.this, MainActivity.class);
                            startActivity(intent);

                        }
                        else
                        {
                            String er = task.getException().toString();
                            Toast.makeText(getApplicationContext(),er,Toast.LENGTH_SHORT).show();
                        }

                        loader.dismiss();
                    }
                });




            }
        });
    }
}

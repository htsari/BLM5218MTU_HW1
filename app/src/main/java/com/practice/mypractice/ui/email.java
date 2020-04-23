package com.practice.mypractice.ui;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.practice.mypractice.R;

import java.util.Objects;

public class email extends AppCompatActivity {

    EditText To, Subject, Message, attachText;
    Button send, attachment;
    Uri selectedImage = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email);
        setTitle("Send Email");
        init();

        attachment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_GET_CONTENT);
                i.addCategory(Intent.CATEGORY_OPENABLE);
                i.setType("*/*");
                startActivityForResult(i, 1);
            }
        });

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Intent.ACTION_SEND);

                String[] strTo = { To.getText().toString() };

                intent.putExtra(Intent.EXTRA_EMAIL, strTo);
                intent.putExtra(Intent.EXTRA_SUBJECT, Subject.getText().toString());
                intent.putExtra(Intent.EXTRA_TEXT, Message.getText().toString());

                if (selectedImage != null) {
                    intent.putExtra(Intent.EXTRA_STREAM, selectedImage);
                }

                intent.setType("message/rfc822");

                intent.setPackage("com.google.android.gm");

                startActivity(intent);

            }
        });
    }

    void init() {
        To = findViewById(R.id.To);
        Subject = findViewById(R.id.Subject);
        Message = findViewById(R.id.Message);
        attachText = findViewById(R.id.attachText);
        send = findViewById(R.id.sendEmail);
        attachment = findViewById(R.id.attachment);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK
                && null != data) {
            selectedImage = data.getData();
            attachText.setText(Objects.requireNonNull(data.getData()).getPath());

        }
    }
}

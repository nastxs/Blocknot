package com.example.blocknot;

import static android.app.Notification.PRIORITY_HIGH;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.blocknot.Models.Notes;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class NotesTakerActivity extends AppCompatActivity {

    EditText editText_title;
    EditText editText_notes;
    ImageView imageView_save;
    Notes notes;

    private NotificationManager notificationManager;
    private static final int NOTIFY_ID = 1;
    private static final String CHANNEL_ID = "CHANNEL_ID";

    boolean isOldNote = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_taker);

        imageView_save = findViewById(R.id.imageView_save);
        editText_title = findViewById(R.id.editText_title);
        editText_notes = findViewById(R.id.editText_notes);
        notes = new Notes();
        try {


            notes = (Notes) getIntent().getSerializableExtra("old_notes");
            editText_title.setText(notes.getTitle());
            editText_notes.setText(notes.getNotes());
            isOldNote = true;
        }catch(Exception e)
        {
            e.printStackTrace();
        }

        imageView_save.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {


                String title = editText_title.getText().toString();
                String description = editText_notes.getText().toString();
                if(description.isEmpty())
                {
                    Toast.makeText(NotesTakerActivity.this, "Please enter description", Toast.LENGTH_SHORT).show();
                    return;
                }
                SimpleDateFormat formater = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss ");
                Date date = new Date();

                if(!isOldNote) {
                    notes = new Notes();
                }
                notes.setTitle(title);
                notes.setNotes(description);
                notes.setDate(formater.format(date));


                Intent intent = new Intent();
                intent.putExtra("note", notes);
                setResult(Activity.RESULT_OK, intent);
                finish();


            }



        });


    }





}
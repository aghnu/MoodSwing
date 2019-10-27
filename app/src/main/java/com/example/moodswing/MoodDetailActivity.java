package com.example.moodswing;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.io.Serializable;

public class MoodDetailActivity extends AppCompatActivity implements Serializable {
    MoodEvent moodEvent;
    private int moodType;
    private DateJar date;
    private TimeJar time;
    // optional fields
    private String reason;
    private Integer socialSituation;

    TextView dateText;
    TextView timeText;
    TextView moodText;
    TextView descriptionText;

    ImageButton delButton;
    ImageButton editButton;

    FirestoreUserDocCommunicator communicator;
    String UID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mood_details);
        Intent moodIntent = getIntent();
        communicator = (FirestoreUserDocCommunicator) moodIntent.getSerializableExtra("communicator");
        UID = moodIntent.getStringExtra("UID");

        //moodEvent = communicator.grabMood(UID);

        moodType = moodEvent.getMoodType();
        date = moodEvent.getDate();
        time = moodEvent.getTime();
        reason = moodEvent.getReason();
        socialSituation = moodEvent.getSocialSituation();

        dateText = findViewById(R.id.dateText);
        timeText = findViewById(R.id.timeText);
        moodText = findViewById(R.id.moodText);
        descriptionText = findViewById(R.id.descriptionText);
        delButton = findViewById(R.id.deleteButton);
        editButton = findViewById(R.id.editButton);


        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MoodDetailActivity.this, EditMoodActivity.class);
                intent.putExtra("communicator", (Serializable) communicator);
                intent.putExtra("UID",UID);
                startActivity(intent);
            }
        });
    }
}

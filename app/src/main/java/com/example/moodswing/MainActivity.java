package com.example.moodswing;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.moodswing.Fragments.MoodDetailFragment;
import com.example.moodswing.customDataTypes.FirestoreUserDocCommunicator;
import com.example.moodswing.Fragments.FollowingFragment;
import com.example.moodswing.Fragments.HomeFragment;
import com.example.moodswing.Fragments.profileFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {

    private static final int MOOD_HISTORY_SCREEN = 1;
    private static final int FOLLOWING_SCREEN = 2;

    private int currentScreenPointer;

    private Button moodHistoryBtn;
    private Button followingBtn;
    private FloatingActionButton profileBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);


        // link all elements
        moodHistoryBtn = findViewById(R.id.nav_homeBtn);
        followingBtn = findViewById(R.id.nav_followingBtn);
        profileBtn = findViewById(R.id.nav_profile);

        // listeners
        moodHistoryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentScreenPointer != MOOD_HISTORY_SCREEN) {
                    currentScreenPointer = MOOD_HISTORY_SCREEN;
                    toMoodHistory();
                }
            }
        });

        followingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentScreenPointer != FOLLOWING_SCREEN) {
                    currentScreenPointer = FOLLOWING_SCREEN;
                    toFollowing();
                }
            }
        });

        profileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openProfileFragment();
            }
        });

        // other action that need to be init
        toMoodHistory(); // default view -> moodHistory
        currentScreenPointer = MOOD_HISTORY_SCREEN;
    }

    private void toMoodHistory(){
        moodHistoryBtn.setBackgroundColor(getResources().getColor(R.color.colorAccent));
        followingBtn.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        FragmentTransaction fragTrans = getSupportFragmentManager().beginTransaction();
        fragTrans.replace(R.id.fragment_placeHolder, new HomeFragment());
        // fragTrans.setCustomAnimations(android.R.animator.fade_in,android.R.animator.fade_out);
        // will add animation and back stack later
        fragTrans.commit();
    }

    private void toFollowing() {
        followingBtn.setBackgroundColor(getResources().getColor(R.color.colorAccent));
        moodHistoryBtn.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        FragmentTransaction fragTrans = getSupportFragmentManager().beginTransaction();
        fragTrans.replace(R.id.fragment_placeHolder, new FollowingFragment());
        // fragTrans.setCustomAnimations(android.R.animator.fade_in,android.R.animator.fade_out);
        // will add back stack and animation later
        fragTrans.commit();
    }

    private void toDetailedView(int moodPosition) {
        FragmentTransaction fragTrans = getSupportFragmentManager().beginTransaction();
        fragTrans.replace(R.id.fragment_placeHolder, new MoodDetailFragment());
        fragTrans.commit();
    }

    public void openProfileFragment(){
        new profileFragment().show(getSupportFragmentManager(), "profile");
    }

    public void signOut() {
        FirestoreUserDocCommunicator.destroy();
        finishAffinity();
        startActivity(new Intent(this, LoginActivity.class));
    }

}


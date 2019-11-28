package com.example.moodswing.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.moodswing.NewMoodActivity;
import com.example.moodswing.R;
import com.example.moodswing.customDataTypes.FirestoreUserDocCommunicator;
import com.example.moodswing.customDataTypes.MoodEvent;
import com.example.moodswing.customDataTypes.UserJar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import static com.example.moodswing.customDataTypes.MoodEventUtility.FOLLOWING_MODE;
import static com.example.moodswing.customDataTypes.MoodEventUtility.MOODHISTORY_MODE;

public class MapDetailAdapterFragment extends Fragment {

    private FrameLayout MapDetailAdapterLayout;
    private int mode;
    private String ID;
    private FirestoreUserDocCommunicator communicator;

    public MapDetailAdapterFragment(){}

    public MapDetailAdapterFragment(int mode, String ID){
        this.mode = mode;
        this.ID = ID;
        this.communicator = FirestoreUserDocCommunicator.getInstance();
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_detailed_map, container, false);
        // the view is created after this

        MapDetailAdapterLayout = root.findViewById(R.id.MapDetailedFrag_placeholder);

        switch (mode){
            case MOODHISTORY_MODE:
                getFragmentManager().beginTransaction()
                        .replace(MapDetailAdapterLayout.getId(), new MoodDetailFragment(communicator.getMoodPosition(ID),this))
                        .commitAllowingStateLoss();
                break;
            case FOLLOWING_MODE:
                getFragmentManager().beginTransaction()
                        .replace(MapDetailAdapterLayout.getId(), new MoodDetailFollowingFragment(communicator.getUserJarPosition(ID),this))
                        .commitAllowingStateLoss();
                break;
        }

        return root;
    }
}

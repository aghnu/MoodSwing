package com.example.moodswing.navigationFragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moodswing.GoogleMapActivity;
import com.example.moodswing.MainActivity;
import com.example.moodswing.NewMoodActivity;
import com.example.moodswing.R;
import com.example.moodswing.customDataTypes.DateJar;
import com.example.moodswing.customDataTypes.FirestoreUserDocCommunicator;
import com.example.moodswing.customDataTypes.MoodAdapter;
import com.example.moodswing.customDataTypes.MoodEvent;
import com.example.moodswing.customDataTypes.TimeJar;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    // communicator
    private FirestoreUserDocCommunicator communicator;

    // UI elements
    private Button MapButton;
    private Button addButton;
    private Button delButton;

    // temp button
    private Button lgoButton;

    // recyclerView related
    private RecyclerView.LayoutManager recyclerViewLayoutManager;
    private RecyclerView.Adapter moodListAdapter;
    private ArrayList<MoodEvent> moodDataList;
    private RecyclerView moodList;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // inflate fragment layout
        View root = inflater.inflate(R.layout.mood_history_screen, container, false);
        // note: write your code after this msg

        // init
        communicator = FirestoreUserDocCommunicator.getInstance();
        addButton = root.findViewById(R.id.addMoodButton);
        delButton = root.findViewById(R.id.delMoodButton);
        MapButton = root.findViewById(R.id.mapViewButton);
        moodList = root.findViewById(R.id.mood_list);

        // temp Button
        lgoButton = root.findViewById(R.id.buttonTempLogout);

        // construct recyclerView
        recyclerViewLayoutManager = new LinearLayoutManager(getContext());
        moodDataList = new ArrayList<>();
        moodListAdapter = new MoodAdapter(moodDataList);
        moodList.setLayoutManager(recyclerViewLayoutManager);
        moodList.setAdapter(moodListAdapter);

        // setup realTime listener
        communicator.initMoodEventsList(moodList);

        // setup listeners
        MapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), GoogleMapActivity.class));
            }
        });
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                startActivity(new Intent(getActivity(), NewMoodActivity.class));
                communicator.addMoodEvent(new MoodEvent(communicator.generateMoodID(),1, new DateJar(1998,2,27), new TimeJar(12,45)));
            }
        });
//        delButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                for(int i = 0; i < moodListAdapter.getItemCount(); i++){
//                    if (moodDataList.get(i).isSelected()) {
//                        communicator.removeMoodEvent(moodDataList.get(i).getUniqueID());
//                    }
//                }
//            }
//        });
        lgoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).openProfileFragment();
            }
        });

        return root;
    }
}
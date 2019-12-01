package com.example.clientpoker;


import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.Vector;


/**
 * A simple {@link Fragment} subclass.
 */
public class QuestionsFragment extends Fragment {


    private static final String TAG = "VoteFragment";
    private Vector<String> names = new Vector<>(Arrays.
            asList("0", "1", "2", "3",
                    "5", "8", "13", "20",
                    "40", "100", "?", "coffee"));
    private String vote;
    private View view;
    private String logUser;

    @Override
    public void onResume() {
        super.onResume();

    }

    TextView projectText;
    TextView questioText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, "Fragment Vote started");
        this.vote = null;
        view = inflater.inflate(R.layout.fragment_questions, container, false);
        initRecyclerView();
        view.findViewById(R.id.voteBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (vote != null) {
                    // TODO: save the vote into the DB

                } else {
                    displayToast("You did not vote yet!");
                }
            }
        });
        Button list = (Button) view.findViewById(R.id.listBtn);
        list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.fragment_container, new ActiveQuestionsFragment()).addToBackStack(null).commit();
            }
        });
        return view;
    }

    private void initRecyclerView() {
        Log.d(TAG, "Init RecyclerView grid");
        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewGrid);
        GridLayoutManager layoutManager = new GridLayoutManager(view.getContext(), 4);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        GridAdapter adapter = new GridAdapter(view.getContext(), names,
                new GridAdapter.ClickListener() {
                    @Override
                    public void onPositionClicked(int position) {
                        String msg = "Your vote: ";
                        Context context = view.getContext();

                        // which button is pressed
                        String btn = names.get(position);
                        String coffee = names.lastElement();
                        if (btn.equals(coffee)) {
                            displayToast(msg + coffee);
                            vote = coffee;
                        } else {
                            displayToast(msg + btn);
                            vote = btn;
                        }
                    }
                });
        recyclerView.setAdapter(adapter);
    }

    public void displayToast(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }
}

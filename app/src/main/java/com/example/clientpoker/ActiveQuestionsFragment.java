package com.example.clientpoker;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.LinkedList;


/**
 * A simple {@link Fragment} subclass.
 */
public class ActiveQuestionsFragment extends Fragment implements ActiveAdapter.onTaskListener {

    private final LinkedList<String> mWordList = new LinkedList<>();
    private RecyclerView mRecyclerView;
    private ActiveAdapter mAdapter;

    public ActiveQuestionsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_active_questions, container, false);
        for (int i = 0; i < 20; i++) {
            mWordList.addLast("Word " + i);
        }
        mRecyclerView = (RecyclerView) view.findViewById(R.id.active_recycler);
        mAdapter = new ActiveAdapter(getContext(), mWordList, this);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        return view;
    }

    @Override
    public void onTaskClick(int position) {
        FragmentTransaction fr = getFragmentManager().beginTransaction();
        fr.replace(R.id.fragment_container, new ResultFragment()).addToBackStack(null).commit();
    }
}

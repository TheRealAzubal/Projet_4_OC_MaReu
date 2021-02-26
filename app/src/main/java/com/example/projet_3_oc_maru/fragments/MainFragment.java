package com.example.projet_3_oc_maru.fragments;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.projet_3_oc_maru.di.DI;
import com.example.projet_3_oc_maru.models.Meeting;
import com.example.projet_3_oc_maru.R;
import com.example.projet_3_oc_maru.service.MeetingApiService;
import com.example.projet_3_oc_maru.ui.MyMeetingsRecyclerViewAdapter;
import java.util.List;

public class MainFragment extends Fragment implements MyMeetingsRecyclerViewAdapter.OnCallbackAdapterToMainFragment {
    MeetingApiService mApiService;
    RecyclerView mRecyclerView;
    MyMeetingsRecyclerViewAdapter mAdapter;

    public MainFragment() {
        // Required empty public constructor
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mApiService = DI.getMeetingApiService();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        Context context = view.getContext();
        mRecyclerView = (RecyclerView) view;
        mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        initList();
        return view;
    }

    private void initList() {
        List<Meeting> mMeetings = mApiService.getMeetings();
        mAdapter =new MyMeetingsRecyclerViewAdapter(mMeetings);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnCallbackAdapterToMainFragment(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void shareCallbackAdapterToMainFragment(Meeting meeting) {
        mApiService.deleteMeeting(meeting);
        mAdapter.notifyDataSetChanged();
    }

}
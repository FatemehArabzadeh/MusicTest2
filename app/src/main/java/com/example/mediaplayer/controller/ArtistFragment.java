package com.example.mediaplayer.controller;


import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mediaplayer.R;
import com.example.mediaplayer.adapter.ArtistAdapter;
import com.example.mediaplayer.model.Artist;
import com.example.mediaplayer.repository.ArtistRepository;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ArtistFragment extends Fragment {


    public static final String FRAGMENT_STATE_KEY = "fragment state key";
   // private List mArtistList=new ArrayList();
    private List mArtistList;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArtistAdapter mArtistAdapter;

    public static ArtistFragment newInstance(State state) {

        Bundle args = new Bundle();
        args.putString(FRAGMENT_STATE_KEY, state.toString());
        ArtistFragment fragment = new ArtistFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public ArtistFragment() {
        // Required empty public constructor
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mArtistList = ArtistRepository.getInstance(getContext()).getArtists();
        /*for (int i=0;i<12;i++){
            Artist album=new Artist();
            album.setArtistName("k"+i);
            mArtistList.add(album);
        }*/
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_artist, container, false);
        initUI(view);

        mArtistAdapter= new ArtistAdapter( getContext(),mArtistList);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

updateUI();


        // Inflate the layout for this fragment
        return view;
    }
    public void updateUI() {


        mRecyclerView.setAdapter(mArtistAdapter);
        mArtistAdapter.setArtists(mArtistList);
        mArtistAdapter.notifyDataSetChanged();



    }

    private void initUI(View view) {
        mRecyclerView=view.findViewById(R.id.id_recyclerview_artist);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
        mLayoutManager= mRecyclerView.getLayoutManager();
    }



}



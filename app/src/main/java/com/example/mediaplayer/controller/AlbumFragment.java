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
import com.example.mediaplayer.adapter.AlbumAdapter;
import com.example.mediaplayer.model.Album;
import com.example.mediaplayer.repository.AlbumRepository;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class AlbumFragment extends Fragment {


    public static final String FRAGMENT_STATE_KEY = "fragment state key";
    //private List mAlbumList=new ArrayList();
    private List mAlbumList;
private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private AlbumAdapter mAlbumAdapter;

    public static AlbumFragment newInstance(State state) {

        Bundle args = new Bundle();
        args.putString(FRAGMENT_STATE_KEY, state.toString());
        AlbumFragment fragment = new AlbumFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public AlbumFragment() {
        // Required empty public constructor
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

           mAlbumList = AlbumRepository.getInstance(getContext()).getAlbums();
/*for (int i=0;i<12;i++){
    Album album=new Album();
    album.setAlbumName("k"+i);
    mAlbumList.add(album);
}*/
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_album, container, false);

        initUI(view);
        mAlbumAdapter= new AlbumAdapter( getContext(),mAlbumList);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        updateUI();




        // Inflate the layout for this fragment
        return view;
    }

    public void updateUI() {


        mRecyclerView.setAdapter(mAlbumAdapter);
        mAlbumAdapter.setAlbums(mAlbumList);
        mAlbumAdapter.notifyDataSetChanged();



    }

    private void initUI(View view) {
        mRecyclerView=view.findViewById(R.id.id_recyclerview_album);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
        mLayoutManager= mRecyclerView.getLayoutManager();
    }



}



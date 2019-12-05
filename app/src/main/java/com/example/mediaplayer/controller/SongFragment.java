package com.example.mediaplayer.controller;


import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mediaplayer.R;
import com.example.mediaplayer.model.Song;
import com.example.mediaplayer.repository.SongRepository;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

import java.io.IOException;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class SongFragment extends Fragment {


    public static final String FRAGMENT_STATE_KEY = "fragment state key";
    //  private List mSongList=new ArrayList();
    private List mSongList;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private SongAdapter mSongAdapter;
    private MediaPlayer mediaPlayer;
    private ImageView mImageViewSong;
    private TextView mTextViewArtist;
    private TextView mTextViewSong;
    private BottomSheetBehavior bottomSheetBehavior;
    FrameLayout frameLayout;
    CardView cardView;



    public static SongFragment newInstance(State state) {

        Bundle args = new Bundle();
        args.putString(FRAGMENT_STATE_KEY, state.toString());
        SongFragment fragment = new SongFragment();
        fragment.setArguments(args);
        return fragment;
    }

    /*public void setBottomSheetCallBack(BottomSheetCallBack bottomSheetCallBack) {
        this.bottomSheetCallBack = bottomSheetCallBack;
    }
*/
    public SongFragment() {
        // Required empty public constructor
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mSongList = SongRepository.getInstance(getContext()).getSongs();
      // mediaPlayer = new MediaPlayer();
      // mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);

       /* for (int i=0;i<12;i++){
            Song album=new Song();
            album.setName("k"+i);
            mSongList.add(album);
        }*/

       /* try {
            mediaPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }*/

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_song, container, false);
        initUI(view);
        mSongAdapter = new SongAdapter(getContext(), mSongList);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);


    /*  mSongAdapter.textView.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              try {
                  mediaPlayer.prepare();
              } catch (IOException e) {
                  e.printStackTrace();
              }
              mediaPlayer.start();
              Toast.makeText(getActivity(),"jhjghj",Toast.LENGTH_SHORT).show();

          }
      });*/
        // Inflate the layout for this fragment
        updateUI();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    public void updateUI() {


        mRecyclerView.setAdapter(mSongAdapter);
        mSongAdapter.setSongs(mSongList);
        mSongAdapter.notifyDataSetChanged();


    }

    private void initUI(View view) {
        mRecyclerView = view.findViewById(R.id.id_recyclerview_song);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

    }


    public class SongAdapter extends RecyclerView.Adapter<SongAdapter.SongHolder> {

        private Context mContext;
        private List<Song> mItems;

        public void setSongs(List<Song> items) {
            mItems = items;
        }

        public SongAdapter(Context context, List<Song> items) {
            mContext = context;
            mItems = items;

        }

        @NonNull
        @Override
        public SongHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            View view = inflater.inflate(R.layout.item_song, parent, false);
            return new SongHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull SongHolder holder, int position) {
            holder.bindSongُ(mItems.get(position));
        }

        @Override
        public int getItemCount() {
            return mItems.size();
        }

        public class SongHolder extends RecyclerView.ViewHolder {


            private Song mSong;

            public SongHolder(@NonNull final View itemView) {
                super(itemView);
                mImageViewSong = itemView.findViewById(R.id.id_image_item_song);
                mTextViewArtist = itemView.findViewById(R.id.id_artistName_item_song);
                mTextViewSong = itemView.findViewById(R.id.id_songName_item_song);
                cardView=itemView.findViewById(R.id.MyCardView);
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
SongRepository.getInstance(mContext).stop();
SongRepository.getInstance(mContext).play(mSong);
                    }
                });
                /*itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        if(bottomSheetCallBack != null){

                        }
//Toast.makeText(getActivity(),"dfsg",Toast.LENGTH_SHORT).show();
                    }


                });*/
            }

            public void bindSongُ(Song song) {
                mSong = song;
                mTextViewSong.setText(mSong.getName());
                mTextViewArtist.setText(mSong.getSongArtist());

                mTextViewSong.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {



                    }
                });

            }
        }

    }



    @Override
    public void onStop() {
        super.onStop();
        mediaPlayer.release();
    }
}






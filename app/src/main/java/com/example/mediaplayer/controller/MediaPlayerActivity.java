package com.example.mediaplayer.controller;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.Adapter;
import android.widget.FrameLayout;


import com.example.mediaplayer.R;
import com.example.mediaplayer.repository.SongRepository;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.tabs.TabLayout;

import static com.google.android.material.bottomsheet.BottomSheetBehavior.PEEK_HEIGHT_AUTO;

public class MediaPlayerActivity extends AppCompatActivity implements SongRepository.BottomSheetCallBack {

    private ViewPager mViewPager;
    private Adapter mAdapter;
    private BottomSheetBehavior bottomSheetBehavior;
    private FrameLayout frameLayout;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mediaplayer);
        frameLayout=findViewById(R.id.root_bottomSheet);
        bottomSheetBehavior=BottomSheetBehavior.from(frameLayout);
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
        bottomSheetBehavior.setPeekHeight(250);
        Toolbar toolbar=findViewById(R.id.id_mytoolbar);
        setSupportActionBar(toolbar);
       // final UUID uuid_user= (UUID) getIntent().getSerializableExtra(EXTRA_LOGIN_FRAGMENT_ID);
        //Toolbar toolbar = findViewById(R.id.toolbar);
        // setActionBar(toolbar);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);

        mViewPager =  findViewById(R.id.pager);



        mViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {

            @Override
            public Fragment getItem(int position) {
                switch (position) {
                    case 0:
                    { SongFragment tab1 = SongFragment.newInstance(State.SONG);

                        return tab1;}
                    case 1:
                    {ArtistFragment tab2 = ArtistFragment.newInstance(State.ARTIST);
                        //tab2.setBottomSheetCallBack(MediaPlayerActivity.this);

                        return tab2;}
                    case 2:
                    {AlbumFragment tab3 = AlbumFragment.newInstance(State.ALBUM);
                        //tab1.setBottomSheetCallBack(MediaPlayerActivity.this);

                        return tab3;}
                    default:
                        return null;
                }

            }

            @Override
            public int getCount() {
                return 3;
            }
        });

        mViewPager.setOffscreenPageLimit(2);
        ////////////////////////////////////////////////////////////

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {




            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mViewPager.getAdapter().notifyDataSetChanged();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mViewPager.setAdapter(mViewPager.getAdapter());
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPager.setCurrentItem(tab.getPosition());
                // ((TabsFragment) ((PagerAdapter) viewPager.getAdapter()).getItem(tab.getPosition())).updateUI();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


    }

    public static Intent newIntent(Context context){
        Intent intent = new Intent(context, MediaPlayerActivity.class);
        return intent;
    }

    @Override
    public void onAttachFragment(@NonNull Fragment fragment) {
        super.onAttachFragment(fragment);
    }


    @Override
    public void showBottomSheet() {
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
    }

}

package com.code_breakers.rythm;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.Toast;

import com.code_breakers.rythm.adapters.DrawerAdapter;
import com.code_breakers.rythm.adapters.DrawerItems;
import com.code_breakers.rythm.preferences.setSharedPreferences;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class Dashboard_NavDrawer extends Fragment {

    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;
    public RecyclerView drawerList;
    public DrawerAdapter drawerAdapter;

    private boolean mUserLearnedDrawer;
    private boolean mFromSavedInstanceState;
    private static final String KEY_USER_LEAREND_DRAWER="user_learnd_drawer";
    private View containerView;
    ImageView userIcon;
    public Dashboard_NavDrawer() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mUserLearnedDrawer = Boolean.valueOf(setSharedPreferences.readFromPreferences(getActivity(),KEY_USER_LEAREND_DRAWER,"false"));
        if(savedInstanceState != null)
            mFromSavedInstanceState = true;


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout = inflater.inflate(R.layout.fragment_dashboard__nav_drawer, container, false);
        drawerList = (RecyclerView)layout.findViewById(R.id.drawer_list);
        drawerAdapter = new DrawerAdapter(getActivity(),DrawerItems.getItem());
        drawerList.setAdapter(drawerAdapter);
        drawerList.setLayoutManager(new LinearLayoutManager(getActivity()));
        return layout;
    }


    public void setUp(int fragmentId,DrawerLayout drawerLayout, final Toolbar toolbar) {
        mDrawerLayout=drawerLayout;
        containerView = getActivity().findViewById(fragmentId);
        mDrawerToggle = new ActionBarDrawerToggle(getActivity(),drawerLayout,toolbar,(R.string.drawer_opem),R.string.drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                if(!mUserLearnedDrawer){
                    mUserLearnedDrawer = true;
                    setSharedPreferences.saveToPreferences(getActivity(),KEY_USER_LEAREND_DRAWER,mUserLearnedDrawer+"");
                }
                getActivity().invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                getActivity().invalidateOptionsMenu();
            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
                if(slideOffset<0.4) {
                    toolbar.setAlpha(1-slideOffset);
                }
            }
        };

        if(!mUserLearnedDrawer && !mFromSavedInstanceState){
            mDrawerLayout.openDrawer(containerView);
        }

        mDrawerLayout.setDrawerListener(mDrawerToggle);

        mDrawerLayout.post(new Runnable() {
            @Override
            public void run() {
                mDrawerToggle.syncState();
            }
        });

        //Setting up user icon
        userIcon = (ImageView)getView().findViewById(R.id.user_icon);
        //Toast.makeText(getActivity(),"Length of icon = "+(64*this.getResources().getDisplayMetrics().density),Toast.LENGTH_LONG).show();
        Bitmap imageBitmap= BitmapFactory.decodeResource(getResources(), R.drawable.ic_user_icon);
        RoundedBitmapDrawable roundedBitmapDrawable=
                RoundedBitmapDrawableFactory.create(getResources(), imageBitmap);
        roundedBitmapDrawable.setCornerRadius((5*80*this.getResources().getDisplayMetrics().density));
        roundedBitmapDrawable.setAntiAlias(true);
        userIcon.setImageDrawable(roundedBitmapDrawable);

    }

    /*public static List<DrawerItems> getItem() {
        List<DrawerItems> data = new ArrayList<>();
        int[] iconId = {R.drawable.ic_person,R.drawable.ic_user_icon,R.drawable.ic_logout,R.drawable.ic_createc};
        String[] itemName = {"Circles","Updates","Events","Settings"};
        for(int i=0;i<itemName.length && i<iconId.length;i++){
            DrawerItems current = new DrawerItems();
            current.drawerItemName = itemName[i];
            current.drawerListIconId = iconId[i];
            data.add(current);
        }
        return data;
    }*/
}

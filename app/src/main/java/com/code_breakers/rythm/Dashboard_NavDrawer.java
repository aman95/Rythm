package com.code_breakers.rythm;


import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.code_breakers.rythm.adapters.DrawerAdapter;
import com.code_breakers.rythm.adapters.DrawerItems;
import com.code_breakers.rythm.listners.RecyclerItemClickListener;
import com.code_breakers.rythm.preferences.setSharedPreferences;
import com.parse.ParseUser;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;


/**
 * A simple {@link Fragment} subclass.
 */
public class Dashboard_NavDrawer extends Fragment {

    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;
    public RecyclerView drawerList;
    public DrawerAdapter drawerAdapter;

    /**
     * A pointer to the current callbacks instance (the Activity).
     */
    private NavigationDrawerCallbacks mCallbacks;

    private int mCurrentSelectedPosition = 0;
    private boolean mUserLearnedDrawer;
    private boolean mFromSavedInstanceState;
    private static final String KEY_USER_LEARNED_DRAWER = "user_learnd_drawer";
    private View containerView;

    TextView drawerUsername , drawerEmail;


    public Dashboard_NavDrawer() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mUserLearnedDrawer = Boolean.valueOf(setSharedPreferences.readFromPreferences(getActivity(), KEY_USER_LEARNED_DRAWER, "false"));
        if (savedInstanceState != null)
            mFromSavedInstanceState = true;


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View layout = inflater.inflate(R.layout.fragment_dashboard__nav_drawer, container, false);
        drawerList = (RecyclerView) layout.findViewById(R.id.drawer_list);

        drawerList.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(), drawerList, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                //Toast.makeText(view.getContext(), "Position = " + position, Toast.LENGTH_SHORT).show();
                selectItem(position);
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        }));


        drawerAdapter = new DrawerAdapter(getActivity(), DrawerItems.getItem());
        drawerList.setAdapter(drawerAdapter);
        drawerList.setLayoutManager(new LinearLayoutManager(getActivity()));
        return layout;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mCallbacks = (NavigationDrawerCallbacks) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException("Activity must implement NavigationDrawerCallbacks.");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallbacks = null;
    }

    private void selectItem(int position) {
        mCurrentSelectedPosition = position;
//        if (mDrawerListView != null) {
//            mDrawerListView.setItemChecked(position, true);
//        }
        if (mDrawerLayout != null) {
            mDrawerLayout.closeDrawer(containerView);
        }
        if (mCallbacks != null) {
            mCallbacks.onNavigationDrawerItemSelected(position);
        }
    }


    public void setUp(int fragmentId, DrawerLayout drawerLayout, final Toolbar toolbar) {
        mDrawerLayout = drawerLayout;
        containerView = getActivity().findViewById(fragmentId);
        mDrawerToggle = new ActionBarDrawerToggle(getActivity(), drawerLayout, toolbar, (R.string.drawer_opem), R.string.drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                if (!mUserLearnedDrawer) {
                    mUserLearnedDrawer = true;
                    setSharedPreferences.saveToPreferences(getActivity(), KEY_USER_LEARNED_DRAWER, mUserLearnedDrawer + "");
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
                if (slideOffset < 0.4) {
                    toolbar.setAlpha(1 - slideOffset);
                }
            }
        };

        if (!mUserLearnedDrawer && !mFromSavedInstanceState) {
            mDrawerLayout.openDrawer(containerView);
        }

        mDrawerLayout.setDrawerListener(mDrawerToggle);

        mDrawerLayout.post(new Runnable() {
            @Override
            public void run() {
                mDrawerToggle.syncState();
            }
        });

        //Adding shadow to navigation f\drawer
//        mDrawerLayout.setDrawerShadow(R.drawable.ic_drawer_shadow, GravityCompat.START);

        //Setting up user icon
        com.pkmmte.view.CircularImageView userIcon = (com.pkmmte.view.CircularImageView) containerView.findViewById(R.id.user_icon);

        drawerUsername = (TextView) containerView.findViewById(R.id.drawer_username);
        drawerEmail = (TextView)containerView.findViewById(R.id.drawer_email);

        drawerUsername.setText(ParseUser.getCurrentUser().getString("FullName"));
        drawerEmail.setText(ParseUser.getCurrentUser().getString("email"));
//        URL url = null;
//        try {
//            url = new URL("http://lorempixel.com/64/64/nature/");
//            Bitmap bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream());
//            userIcon.setImageBitmap(bitmap);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }


//        //Toast.makeText(getActivity(),"Length of icon = "+(64*this.getResources().getDisplayMetrics().density),Toast.LENGTH_LONG).show();
//        Bitmap imageBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_user_icon);
//        RoundedBitmapDrawable roundedBitmapDrawable =
//                RoundedBitmapDrawableFactory.create(getResources(), imageBitmap);
//        roundedBitmapDrawable.setCornerRadius((5 * 80 * this.getResources().getDisplayMetrics().density));
//        roundedBitmapDrawable.setAntiAlias(true);
//        userIcon.setImageDrawable(roundedBitmapDrawable);

    }


    /**
     * Callbacks interface that all activities using this fragment must implement.
     */
    public static interface NavigationDrawerCallbacks {
        /**
         * Called when an item in the navigation drawer is selected.
         */
        void onNavigationDrawerItemSelected(int position);
    }


}

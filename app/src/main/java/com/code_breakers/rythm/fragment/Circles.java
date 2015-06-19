package com.code_breakers.rythm.fragment;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.code_breakers.rythm.R;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Circles.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Circles#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Circles extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String TITLE = "Circles";
    private static final String ARG_PARAM2 = "param2";


    private int mPosition;
    private String mUsername;



    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Circles.
     */
    // TODO: Rename and change types and number of parameters
    public static Circles newInstance(int param1, String param2) {
        Circles fragment = new Circles();
        Bundle args = new Bundle();
        args.putInt(TITLE, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public Circles() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mPosition = getArguments().getInt(TITLE);
            mUsername = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        final View rootView = inflater.inflate(R.layout.fragment_circles, container, false);

        final TextView tv = (TextView)rootView.findViewById(R.id.textView);
        tv.setText("Position = "+mPosition+" Username: "+mUsername);

        ParseQuery<ParseUser> queryUser = ParseUser.getQuery();
        //queryUser.whereNotEqualTo("FullName",mUsername);
        queryUser.findInBackground(new FindCallback<ParseUser>() {
            @Override
            public void done(List<ParseUser> list, ParseException e) {
                if(e==null){
                    //
                    List<String> userDetail = new ArrayList<>(list.size());
                    for(int i = 0; i<list.size();i++) {
                        userDetail.add(i,list.get(i).getObjectId());
                    }
                    tv.setText(userDetail.toString());
                } else {
                    //
                    Toast.makeText(rootView.getContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
                }

            }
        });

        return rootView;
    }

    // TODO: Rename method, update argument and hook method into UI event
//    public void onButtonPressed(Uri uri) {
//        if (mListener != null) {
//            mListener.onFragmentInteraction(uri);
//        }
//    }
//
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

    }

    @Override
    public void onDetach() {
        super.onDetach();
//        mListener = null;
    }

    //
//
//
//    /**
//     * This interface must be implemented by activities that contain this
//     * fragment to allow an interaction in this fragment to be communicated
//     * to the activity and potentially other fragments contained in that
//     * activity.
//     * <p/>
//     * See the Android Training lesson <a href=
//     * "http://developer.android.com/training/basics/fragments/communicating.html"
//     * >Communicating with Other Fragments</a> for more information.
//     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

}

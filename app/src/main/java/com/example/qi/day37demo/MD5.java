package com.example.qi.day37demo;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Locale;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MD5.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MD5#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MD5 extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private View view;
    private EditText et;
    private TextView tv;

    public MD5() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MD5.
     */
    // TODO: Rename and change types and number of parameters
    public static MD5 newInstance(String param1, String param2) {
        MD5 fragment = new MD5();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_md5, container, false);
        view.findViewById(R.id.bt).setOnClickListener(this);
        et = ((EditText) view.findViewById(R.id.et));
        tv = ((TextView) view.findViewById(R.id.tv));
        return view;
    }


    @Override
    public void onClick(View view) {
        String string = et.getText().toString();
        if(!TextUtils.isEmpty(string)){
            try {
                MessageDigest md5 = MessageDigest.getInstance("MD5");
                byte[] digest = md5.digest(string.getBytes("UTF-8"));
                StringBuilder builder = new StringBuilder();
                for (byte b : digest) {
                    builder.append(String.format(Locale.CHINA,"%02X",b));
                }
                tv.setText(builder.toString());
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

        }
    }

}

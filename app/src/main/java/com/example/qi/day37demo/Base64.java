package com.example.qi.day37demo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.io.UnsupportedEncodingException;


public class Base64 extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private View view;
    private EditText editText;
    private TextView textView;

    public Base64() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Base64.
     */
    // TODO: Rename and change types and number of parameters
    public static Base64 newInstance(String param1, String param2) {
        Base64 fragment = new Base64();
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
        view = inflater.inflate(R.layout.fragment_base64, container, false);
        editText = ((EditText) view.findViewById(R.id.et));
        textView = ((TextView) view.findViewById(R.id.tv));
        view.findViewById(R.id.encry).setOnClickListener(this);
        view.findViewById(R.id.decode).setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.encry:
                String s = editText.getText().toString();
                if(!TextUtils.isEmpty(s)){
                    try {
                        String s1 = android.util.Base64.encodeToString(s.getBytes("UTF-8"), android.util.Base64.NO_WRAP);
                        textView.setText(s1);
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }

                }
                break;
            case R.id.decode:
                String s1 = textView.getText().toString();
                if(!TextUtils.isEmpty(s1)){
                    byte[] decode = android.util.Base64.decode(s1, android.util.Base64.NO_WRAP);
                    try {
                        editText.setText(new String(decode,"UTF-8"));
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }
                break;
        }
    }
}

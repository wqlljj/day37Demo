package com.example.qi.day37demo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;


public class Des extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private View view;
    private EditText editText;
    private EditText editText1;
    private TextView textView;
    private static final String DESede = "DESede";
    private static final String DES = "DES";
    public Des() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Des.
     */
    // TODO: Rename and change types and number of parameters
    public static Des newInstance(String param1, String param2) {
        Des fragment = new Des();
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
        view = inflater.inflate(R.layout.fragment_des, container, false);
        editText = ((EditText) view.findViewById(R.id.et));
        editText1 = ((EditText) view.findViewById(R.id.et1));
        textView = ((TextView) view.findViewById(R.id.tv));
        view.findViewById(R.id.encry).setOnClickListener(this);
        view.findViewById(R.id.decode).setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        String key = editText1.getText().toString();
        if(!TextUtils.isEmpty(key)) {
            try {
                byte[] bytes = Arrays.copyOf(key.getBytes("UTF-8"), key.length() > 8 ? 24 : 8);
                SecretKeySpec keySpec = new SecretKeySpec(bytes, bytes.length > 8 ? DESede : DES);
                Cipher cipher = Cipher.getInstance(bytes.length > 8 ? DESede : DES);
                switch (view.getId()) {
                    case R.id.encry:
                        String s = editText.getText().toString();
                        if(!TextUtils.isEmpty(s)){
                            cipher.init(Cipher.ENCRYPT_MODE,keySpec);
                            byte[] bytes1 = cipher.doFinal(s.getBytes("UTF-8"));
                            textView.setText(android.util.Base64.encodeToString(bytes1, Base64.NO_WRAP));
                        }
                        break;
                    case R.id.decode:
                        String s1 = textView.getText().toString();
                        if(!TextUtils.isEmpty(s1)){
                            cipher.init(Cipher.DECRYPT_MODE,keySpec);
                            byte[] bytes1 = cipher.doFinal(Base64.decode(s1, Base64.NO_WRAP));
                            editText.setText(new String(bytes1,"UTF-8"));
                        }
                        break;
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (NoSuchPaddingException e) {
                e.printStackTrace();
            } catch (BadPaddingException e) {
                e.printStackTrace();
            } catch (IllegalBlockSizeException e) {
                e.printStackTrace();
            } catch (InvalidKeyException e) {
                e.printStackTrace();
            }

        }
    }
}

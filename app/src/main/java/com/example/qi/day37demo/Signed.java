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
import android.widget.Toast;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;


public class Signed extends Fragment implements View.OnClickListener {
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
    private String privateKey = "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";
    private String publicKey = "MIICIjANBgkqhkiG9w0BAQEFAAOCAg8AMIICCgKCAgEArJVJoVrefzilN5dYWU9LTW9di9lHWFuhsOi9pj0bh0gl3WxUMwa/o/vudiu/BNfdOK6+UugMCXbPcNfMGSpMwqnSYPqHUnRlt788kR3XsghTy1esS2UprT/s86pJ49nreRr4q3LiRCsClI3zEL+5hg+toa5xZKUD00qqCwk/6InoXJOyJUMnanB9nyAhqAfyNZwD9yWSZbCQ3QA5+yoE7LrLt1hyCLlsaKlyO/qgPt+zeu/aDjanGcwhBgQjJpo/f2fEWOXfjdF+JUNcgGb4GqwvrE2zFK6Fi2xBzbP0D4quVZhhPbXYNPPlcrffj9vTmk5GwUGEYBSEp66LkCd7Xe3+ZBI6fGv3tFnCSgmUM6MhHiwDgJqvAfF+/OQFWP18G/Bco2+l37HMSzr9FIUGPHFTZ8/y4xVKgVLF8awLAy8mqfQqcbZG4fBKKcq8CcYRJtlMn3Xrd0R+jC4SN0P0XmkkfFPKOz21kYdds/bqW81ZF0ipNEsQueC0LBqZrXTI0T7KJpt16mPhGn6dP9CqDP/4Q0zTG+mk6BQrqnLVL+Smk7R9/j2uC/KbIb+Imvrn2KUPUFdXgqcNnuXq0ijRA2+fqk7hPHoRE4iXHsP0T8mUUy7rPsrwTzW3s2IQafJeNr5TmtAc73YBQc+f7C4s6JtB/ST+7Qnr/1gXeJ6icJsCAwEAAQ==";

    public Signed() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Signed.
     */
    // TODO: Rename and change types and number of parameters
    public static Signed newInstance(String param1, String param2) {
        Signed fragment = new Signed();
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
        view = inflater.inflate(R.layout.fragment_signed, container, false);
        editText = ((EditText) view.findViewById(R.id.et));
        textView = ((TextView) view.findViewById(R.id.tv));
        view.findViewById(R.id.encry).setOnClickListener(this);
        view.findViewById(R.id.decode).setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        try {
            //1.MD5
//            Signature signature = Signature.getInstance("MD5WithRSA");
            //2.SHA1
            Signature signature = Signature.getInstance("SHA1WithRSA");
            String s = editText.getText().toString();
            KeyFactory factory = KeyFactory.getInstance("RSA");
            if(!TextUtils.isEmpty(s)){
                switch (view.getId()){
                    case R.id.encry:
                        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(Base64.decode(privateKey, Base64.NO_WRAP));
                        signature.initSign(factory.generatePrivate(keySpec));
                        signature.update(s.getBytes("UTF-8"));
                        byte[] sign = signature.sign();
                        textView.setText(Base64.encodeToString(sign,Base64.NO_WRAP));
                        break;
                    case R.id.decode:
                        String s1 = textView.getText().toString();
                        if(!TextUtils.isEmpty(s1)){
                            X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(Base64.decode(publicKey, Base64.NO_WRAP));
                            signature.initVerify(factory.generatePublic(x509EncodedKeySpec));
                            signature.update(s.getBytes("UTF-8"));
                            if(signature.verify(Base64.decode(s1,Base64.NO_WRAP))){
                                Toast.makeText(getContext(),"通过",Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(getContext(),"失败",Toast.LENGTH_SHORT).show();
                            }
                        }
                        break;
                }
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (SignatureException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }
    }
}

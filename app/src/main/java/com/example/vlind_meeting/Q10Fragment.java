package com.example.vlind_meeting;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class Q10Fragment extends Fragment {

    private Button submit;
    private EditText mbti;
    private String user_mbti;
    FragmentListener fragmentListener;


    public Q10Fragment() {
        // Required empty public constructor
    }
    public static Q10Fragment newInstance(String param1, String param2) {
        Q10Fragment fragment = new Q10Fragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup viewgroup = (ViewGroup) inflater.inflate(R.layout.fragment_q10, container, false);
        submit = (Button) viewgroup.findViewById(R.id.submit);
        mbti = (EditText) viewgroup.findViewById(R.id.mbti);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user_mbti = mbti.getText().toString();
                if(user_mbti.getBytes().length <= 0){
                    AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());
                    builder.setTitle("알림창").setMessage("값을 입력해주세요");
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                }
                else {
                    System.out.println("ok!");
                    fragmentListener.set10(user_mbti);
                    fragmentListener.submit();
                }
            }
        });

        return viewgroup;
    }
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof FragmentListener) {
            fragmentListener = (FragmentListener) context; }
    }
    @Override
    public void onDetach() {
        super.onDetach();
        if (fragmentListener != null) {
            fragmentListener = null; }
    }

}


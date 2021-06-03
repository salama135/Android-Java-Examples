package com.example.android_java_examples.fragment;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.speech.RecognizerIntent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.example.android_java_examples.R;
import com.example.android_java_examples.helper.CompanyDBHelper;

import java.util.ArrayList;
import java.util.Locale;


/**
 * A simple {@link Fragment} subclass.
 */
public class VoiceFragment extends Fragment {
    EditText myText;
    int voiceCode = 1;
    ListView myList;
    ArrayAdapter<String> EmployeeAdapter;
    CompanyDBHelper companyDBHelper;

    public VoiceFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_voice, container, false);
        myList = v.findViewById(R.id.listview10);
        myText = v.findViewById(R.id.editText);
        companyDBHelper = new CompanyDBHelper(getActivity());
        ImageButton imageButtonVoice = v.findViewById(R.id.imageButtonVoice);
        EmployeeAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1);
        myList.setAdapter(EmployeeAdapter);
        companyDBHelper.createData();
//
//        myText.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                if(myText.getText().toString().trim() != "") {
//
//
//                    EmployeeAdapter.clear();
//                    Cursor matchedEmployees = myHelper.getEmployees(myText.getText().toString().trim());
//
//                    if (matchedEmployees != null) {
//                        while (!matchedEmployees.isAfterLast()) {
//                            EmployeeAdapter.add(matchedEmployees.getString(1));
//                            matchedEmployees.moveToNext();
//                        }
//                    } else {
//                        Toast.makeText(getActivity(), "No Matched Employees", Toast.LENGTH_LONG).show();
//                    }
//                }
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//
//            }
//        });
        imageButtonVoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                i.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                i.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
                startActivityForResult(i, voiceCode);
            }
        });
        return v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == voiceCode && resultCode == Activity.RESULT_OK){
            ArrayList<String> text = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            myText.setText(text.get(0));;
            EmployeeAdapter.clear();
            Cursor matchedEmployees = companyDBHelper.getEmployees(text.get(0));
            Log.e("voice", text.get(0));

            if(matchedEmployees != null){
                while(!matchedEmployees.isAfterLast()){
                    EmployeeAdapter.add(matchedEmployees.getString(0));
                    matchedEmployees.moveToNext();
                }
            }
            else{
                Toast.makeText(getActivity(), "No Matched Employees", Toast.LENGTH_LONG).show();
            }
        }
    }
}

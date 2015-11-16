package com.zicure.abacconnect.alumni.advanced.search;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.zicure.abacconnect.ApplicationContext;
import com.zicure.abacconnect.R;

/**
 * Created by DUMP129 on 9/29/2015.
 */
public class AlumniAdvancedSearchFragment extends Fragment implements View.OnClickListener {
    private Button btnAlumniAdvSearch;
    private EditText txtCompanyName, txtStdID, txtName;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_alumni_advanced_search, container, false);
        btnAlumniAdvSearch = (Button) v.findViewById(R.id.btnAlumniAdvSearch);
        txtCompanyName = (EditText) v.findViewById(R.id.txtCompanyName);
        txtStdID = (EditText) v.findViewById(R.id.txtStdID);
        txtName = (EditText) v.findViewById(R.id.txtName);

        btnAlumniAdvSearch.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View v) {
        String name, stdId, company;

        name = txtName.getText().toString();
        stdId = txtStdID.getText().toString();
        company = txtCompanyName.getText().toString();

        if (TextUtils.isEmpty(name) && TextUtils.isEmpty(stdId)) {
            txtName.setError("Please Enter Your Name.");
            txtStdID.setError("Please Enter Your Student ID.");
        } else {
            Toast.makeText(ApplicationContext.getInstance().getContext(), "Clicked", Toast.LENGTH_SHORT).show();
        }
    }
}
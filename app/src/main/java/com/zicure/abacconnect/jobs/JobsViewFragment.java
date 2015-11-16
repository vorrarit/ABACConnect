package com.zicure.abacconnect.jobs;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zicure.abacconnect.R;
import com.zicure.abacconnect.jobs.Jobss;

import java.util.List;

/**
 * Created by DUMP129 on 10/26/2015.
 */
public class JobsViewFragment extends Fragment {
    private TextView tvJobsViewPositionName, tvJobsViewCompanyName, tvJobsViewAddressName,
            tvJobsViewPosition, tvJobsViewContactTel, tvJobsViewKeyAccountDetail, tvJobsViewQualificationDetail;
    private String posName, company, address, date, jobNum, contactTel, keyAccountDetail, qualificationDetail;

    private View v;
    private List<Jobss> jobssList;
    private int position;

    public JobsViewFragment(View v, List<Jobss> listJobs, int position) {
        this.v = v;
        this.jobssList = listJobs;
        this.position = position;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.jobs_view, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tvJobsViewPositionName = (TextView) view.findViewById(R.id.tvJobsViewPositionName);
        tvJobsViewCompanyName = (TextView) view.findViewById(R.id.tvJobsViewCompanyName);
        tvJobsViewAddressName = (TextView) view.findViewById(R.id.tvJobsViewAddressName);
        tvJobsViewPosition = (TextView) view.findViewById(R.id.tvJobsViewPosition);
        tvJobsViewContactTel = (TextView) view.findViewById(R.id.tvJobsViewContactTel);
        tvJobsViewKeyAccountDetail = (TextView) view.findViewById(R.id.tvJobsViewKeyAccountDetail);
        tvJobsViewQualificationDetail = (TextView) view.findViewById(R.id.tvJobsViewQualificationDetail);

        setData();
    }

    private void setData() {
        posName = jobssList.get(position).job_position;
        tvJobsViewPositionName.setText(posName);

        address = jobssList.get(position).job_addr;
        tvJobsViewAddressName.setText(address);

        jobNum = jobssList.get(position).job_num;
        tvJobsViewPosition.setText(jobNum);

        contactTel = jobssList.get(position).job_tel;
        tvJobsViewContactTel.setText(contactTel);

        keyAccountDetail = jobssList.get(position).job_responsibility;
        tvJobsViewKeyAccountDetail.setText(keyAccountDetail);

        qualificationDetail = jobssList.get(position).job_qualification;
        tvJobsViewQualificationDetail.setText(qualificationDetail);
    }
}

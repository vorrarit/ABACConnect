package com.zicure.abacconnect.jobs.jobs_function;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.zicure.abacconnect.ApplicationContext;
import com.zicure.abacconnect.R;
import com.zicure.abacconnect.jobs.jobs_function.model.Child;
import com.zicure.abacconnect.jobs.jobs_function.model.Group;

import java.util.ArrayList;

/**
 * Created by DUMP129 on 11/2/2015.
 */
public class JobFunctionExpanListAdapter extends BaseExpandableListAdapter {
    private Context mContext;
    private ArrayList<Group> groups;

    public JobFunctionExpanListAdapter(Context mContext, ArrayList<Group> groups) {
        this.mContext = mContext;
        this.groups = groups;
    }

    @Override
    public int getGroupCount() {
        return groups.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        ArrayList<Child> chList = groups.get(groupPosition).getItems();
        return chList.size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groups.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        ArrayList<Child> childArrayList = groups.get(groupPosition).getItems();
        return childArrayList.get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        Group group = (Group) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.group_item, null);
        }

        TextView tvGroup = (TextView) convertView.findViewById(R.id.group_name);
        tvGroup.setTypeface(null, Typeface.BOLD);
        tvGroup.setText(group.getGrpName());
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        Child child = (Child) getChild(groupPosition, childPosition);
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.child_item, null);
        }

        CheckBox chkBoxJobFunction = (CheckBox) convertView.findViewById(R.id.chkBoxJobFunction);
        TextView tvJobFunction = (TextView) convertView.findViewById(R.id.tvJobFunction);

        chkBoxJobFunction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ApplicationContext.getInstance().getContext(), "Check", Toast.LENGTH_SHORT).show();
            }
        });

        tvJobFunction.setText(child.getName().toString());

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}

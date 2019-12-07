package com.mca.global.news;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

public class ExpandableListAdabpter extends BaseExpandableListAdapter {

    Context context;
    List<String> headerlist;
    HashMap<String,List<String>> childlist;
    public ExpandableListAdabpter(Context context, List<String> headerlist, HashMap<String, List<String>> childlist){
            this.context=context;
            this.headerlist=headerlist;
            this.childlist=childlist;
    }
    @Override
    public int getGroupCount() {
        return this.headerlist.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this.childlist.get(this.headerlist.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this.headerlist.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return this.childlist.get(this.headerlist.get(groupPosition)).get(childPosition);
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
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
       String headerTitle= (String) getGroup(groupPosition);
        if(convertView==null){
            LayoutInflater inflater= (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(R.layout.navigation_list_group,null);
        }
        TextView header=(TextView) convertView.findViewById(R.id.list_group);
        header.setTypeface(null, Typeface.BOLD);
        header.setText(headerTitle);
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
       final String childText=(String)getChild(groupPosition,childPosition);

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.navigation_list_item,null);
        }

        TextView txtListChild = (TextView) convertView
                .findViewById(R.id.list_item);
        txtListChild.setText(childText);
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}

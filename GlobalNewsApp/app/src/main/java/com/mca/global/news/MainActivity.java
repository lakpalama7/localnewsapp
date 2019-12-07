package com.mca.global.news;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.FrameLayout;


import com.google.android.material.navigation.NavigationView;
import com.mca.global.news.fragments.TechnolgoyFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    ExpandableListAdapter expandableListAdapter;
    ExpandableListView expandableListView;
    List<String> headerlist;
    HashMap<String, List<String>> childlist;
    NavigationView navigationView;
    Toolbar toolbar;
    FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.drawerlayout);
        navigationView = findViewById(R.id.navigationview);
        expandableListView = findViewById(R.id.expandablelistview);
        preparedDatalist();

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("News App");

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        toggle.syncState();

        frameLayout = findViewById(R.id.framelayout);
        expandableListAdapter = new ExpandableListAdabpter(this, headerlist, childlist);

        expandableListView.setAdapter(expandableListAdapter);

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                //groupPosition return position zero only.
                String newsMainCategory = headerlist.get(groupPosition);
                int position = headerlist.indexOf(newsMainCategory);
                setFragmentType(position, childPosition);
                return false;
            }
        });

    }

    private void preparedDatalist() {

        headerlist = new ArrayList<>();
        childlist = new HashMap<>();

        headerlist.add(getResources().getString(R.string.andhra));
        headerlist.add(getResources().getString(R.string.tamil));
        headerlist.add(getResources().getString(R.string.indian));
        headerlist.add(getResources().getString(R.string.global));

        List<String> ap = new ArrayList<>();
        ap.add(getResources().getString(R.string.technology));
        ap.add(getResources().getString(R.string.education));
        ap.add(getResources().getString(R.string.business));
        ap.add(getResources().getString(R.string.entertainment));
        ap.add(getResources().getString(R.string.health));
        ap.add(getResources().getString(R.string.sports));

        List<String> tamil = new ArrayList<>();
        tamil.add(getResources().getString(R.string.tamilnew));

        List<String> indian=new ArrayList<>();
        indian.add(getResources().getString(R.string.indiannews));

        List<String> world=new ArrayList<>();
        world.add(getResources().getString(R.string.worldnews));

        childlist.put(headerlist.get(0), ap);
        childlist.put(headerlist.get(1), tamil);
        childlist.put(headerlist.get(2), indian);
        childlist.put(headerlist.get(3), world);
    }


    private void setFragmentType(int position, int childPosition) {
        FragmentManager manager = getSupportFragmentManager();
        TechnolgoyFragment technolgoyFragment = new TechnolgoyFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("newsMainCategory", position);
        bundle.putInt("childPosition", childPosition);
       /* bundle.putString("groupPosition",newstype);
        bundle.putString("category",category);*/
        technolgoyFragment.setArguments(bundle);
        manager.beginTransaction().replace(R.id.framelayout, technolgoyFragment, "technolgoy").commit();
        drawerLayout.closeDrawers();
    }
}


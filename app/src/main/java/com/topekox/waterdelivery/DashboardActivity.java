package com.topekox.waterdelivery;

import android.os.Bundle;

import com.topekox.waterdelivery.databinding.ActivityDashboardBinding;

public class DashboardActivity extends DrawerBaseActivity {

    private ActivityDashboardBinding activityDashboardBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityDashboardBinding = ActivityDashboardBinding.inflate(getLayoutInflater());
        setContentView(activityDashboardBinding.getRoot());
        allocatedActivityTitle("Dashboard");
    }
}
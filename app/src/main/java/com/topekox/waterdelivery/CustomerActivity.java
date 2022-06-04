package com.topekox.waterdelivery;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.topekox.waterdelivery.databinding.ActivityDashboardBinding;

public class CustomerActivity extends DrawerBaseActivity {

    private ActivityDashboardBinding activityDashboardBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityDashboardBinding = ActivityDashboardBinding.inflate(getLayoutInflater());
        setContentView(activityDashboardBinding.getRoot());
        allocatedActivityTitle("Customer");
    }
}
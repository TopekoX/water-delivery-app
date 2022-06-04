package com.topekox.waterdelivery;

import android.os.Bundle;

import com.topekox.waterdelivery.databinding.ActivityOrderBinding;

public class OrderActivity extends DrawerBaseActivity {

    private ActivityOrderBinding activityOrderBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityOrderBinding = ActivityOrderBinding.inflate(getLayoutInflater());
        setContentView(activityOrderBinding.getRoot());
        allocatedActivityTitle("Orders");
    }
}
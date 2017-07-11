package com.example.dell.cheddar;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class HomeActivity extends AppCompatActivity {
    TabLayout tablayout;
    ViewPager viewpager;
    ImageView add;

    private int[] tabIcons={
            R.drawable.ic_perm_identity_white_24dp,
            R.drawable.ic_history_white_24dp
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        viewpager = (ViewPager) findViewById(R.id.pager);
        viewpager.setAdapter(new CustomAdapter(getSupportFragmentManager(),getApplicationContext()));

        tablayout = (TabLayout) findViewById(R.id.tabs);
        tablayout.setupWithViewPager(viewpager);
        tablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewpager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                viewpager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                viewpager.setCurrentItem(tab.getPosition());
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(getApplicationContext(), SendMoney.class);
                startActivity(intent);
            }
        });
        setTabIcons();



    }

    private class CustomAdapter extends FragmentPagerAdapter {
        private String fragments [] = {"Recipients", "History"};

        public CustomAdapter(FragmentManager supportFragmentManager, Context applicationContext) {
        super(supportFragmentManager);
        }

        @Override
        public Fragment getItem(int position) {
            switch(position){
                case 0:
                   return new Recipients();
                case 1:
                    return new TransactionHistory();
                default:
                    return null;

            }
        }

        @Override
        public int getCount() {
            return fragments.length;
        }

    }

    private void setTabIcons()
    {
        tablayout.getTabAt(0).setIcon(tabIcons[0]);
        tablayout.getTabAt(1).setIcon(tabIcons[1]);
    }
}

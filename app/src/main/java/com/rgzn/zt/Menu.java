package com.rgzn.zt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Menu extends AppCompatActivity {
    private BottomNavigationView bottomNav;

    private MenuFragment mMenuFragment;
    private RecordFragment mRecordFragment;
    private UserFragment mUserFragment;
    private TrainFragment mTrainFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        //初始化控件
        bottomNav = findViewById(R.id.bottomNav);

        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                bottomNav.getMenu().findItem(R.id.train).setIcon(R.drawable.nvfitness1);
                bottomNav.getMenu().findItem(R.id.record).setIcon(R.drawable.nvnote1);
                bottomNav.getMenu().findItem(R.id.home).setIcon(R.drawable.nvhome1);
                bottomNav.getMenu().findItem(R.id.user).setIcon(R.drawable.nvuser1);

                if (item.getItemId() == R.id.train) {
                    selectedFragment(0);
                    item.setIcon(R.drawable.nvfitness);
                } else if (item.getItemId() == R.id.record) {
                    selectedFragment(1);
                    item.setIcon(R.drawable.nvnote);
                } else if (item.getItemId() == R.id.home) {
                    selectedFragment(2);
                    item.setIcon(R.drawable.nvhome);
                } else if (item.getItemId() == R.id.user) {
                    selectedFragment(3);
                    item.setIcon(R.drawable.nvuser);
                }
                return true;
            }
        });




        //默认是中间
        selectedFragment(2);
        bottomNav.setSelectedItemId(R.id.home);
    }

    private void selectedFragment(int position)
    {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        hideFragment(transaction);
        if (position == 0) {
            if (mTrainFragment == null) {
                mTrainFragment = new TrainFragment();
                transaction.add(R.id.content, mTrainFragment);
            } else {
                transaction.show(mTrainFragment);
            }
        } else if (position == 1) {
            if (mRecordFragment == null) {
                mRecordFragment = new RecordFragment();
                transaction.add(R.id.content, mRecordFragment);
            } else {
                transaction.show(mRecordFragment);
                mRecordFragment.loadData();
            }
        } else if (position == 2) {
            if (mMenuFragment == null) {
                mMenuFragment = new MenuFragment();
                transaction.add(R.id.content, mMenuFragment);
            } else {
                transaction.show(mMenuFragment);
            }
        } else {
            if (mUserFragment == null) {
                mUserFragment = new UserFragment();
                transaction.add(R.id.content, mUserFragment);
            } else {
                transaction.show(mUserFragment);
            }
        }
        //最后设置提交     注意：这句话一定不能少！！！！！！
        transaction.commit();
    }

    private void hideFragment(FragmentTransaction transaction) {
        if (mMenuFragment != null) {
            transaction.hide(mMenuFragment);
        }
        if (mUserFragment != null) {
            transaction.hide(mUserFragment);
        }
        if (mRecordFragment != null) {
            transaction.hide(mRecordFragment);
        }
        if (mTrainFragment != null) {
            transaction.hide(mTrainFragment);
        }
    }
}
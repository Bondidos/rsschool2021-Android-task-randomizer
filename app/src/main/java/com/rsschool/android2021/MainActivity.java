package com.rsschool.android2021;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity implements FirstFragment.ActionPerformedListener, SecondFragment.ActionPerformedListener {
    /*
    Передача данных между фрагментами (Fragments) организована через интерфейсы и Activity в качестве "хоста"
     */
    @Override
    public void onActionPerformed(int min, int max) {
        openSecondFragment(min,max);
    }
    @Override
    public void onActionPerformed(int previousNumber) {
        openFirstFragment(previousNumber);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        openFirstFragment(0);
    }

    private void openFirstFragment(int previousNumber) {
        final Fragment firstFragment = FirstFragment.newInstance(previousNumber);
        final FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, firstFragment);
        transaction.commit();
    }

    private void openSecondFragment(int min, int max) {
        final Fragment secondFragment = SecondFragment.newInstance(min,max);
        final FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container,secondFragment);
        transaction.commit();
    }
}

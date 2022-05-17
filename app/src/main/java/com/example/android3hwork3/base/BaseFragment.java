package com.example.android3hwork3.base;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewbinding.ViewBinding;

public abstract class BaseFragment<VB extends ViewBinding> extends Fragment {

    protected VB binding;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupViews();
        setupRequest();
        setupObserve();
        setupListener();
    }

    protected  void setupViews(){
    }

    protected  void setupRequest(){
    }

    protected  void setupObserve(){
    }

    protected  void setupListener(){
    }
}

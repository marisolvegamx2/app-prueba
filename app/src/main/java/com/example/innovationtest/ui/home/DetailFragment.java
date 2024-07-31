package com.example.innovationtest.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.annotation.NonNull;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.innovationtest.data.model.Employee;
import com.example.innovationtest.databinding.FragmentDetailBinding;


public class DetailFragment extends Fragment {
    public static final String ARG_ID="var_id";
    private FragmentDetailBinding binding;
    int id;
    DetailViewModel homeViewModel ;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
         homeViewModel =
                new ViewModelProvider(this).get(DetailViewModel.class);

        binding = FragmentDetailBinding.inflate(inflater, container, false);

        View root = binding.getRoot();


            return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        if (getArguments() != null) {

            id = getArguments().getInt(ARG_ID);


        }
        homeViewModel.getEmployee(id);
        homeViewModel.getDetalle().observe(getViewLifecycleOwner(), new Observer<Employee>() {
            @Override
            public void onChanged(Employee employee) {
                binding.setDetail(employee);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
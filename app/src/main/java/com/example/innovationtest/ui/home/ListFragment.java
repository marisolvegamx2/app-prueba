package com.example.innovationtest.ui.home;


import static com.example.innovationtest.ui.home.DetailFragment.ARG_ID;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import androidx.fragment.app.Fragment;

import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.innovationtest.MainActivity;
import com.example.innovationtest.R;
import com.example.innovationtest.data.model.Employee;
import com.example.innovationtest.databinding.FragmentListBinding;

import java.util.List;
import java.util.prefs.AbstractPreferences;

public class ListFragment extends Fragment implements EmployeeAdapter.AdapterCallback {

    private ListViewModel mViewModel;
    private FragmentListBinding mBinding;
    private EmployeeAdapter mListAdapter;
    public static ListFragment newInstance() {
        return new ListFragment();
    }


    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mBinding= FragmentListBinding.inflate(inflater,
                container,true);
        mViewModel = new ViewModelProvider(this).get(ListViewModel.class);
        return    mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

       setupListAdapter();
       cargarLista();


    }
    private void setupListAdapter() {
        mListAdapter = new EmployeeAdapter(this);
        mBinding.elist.setLayoutManager(new LinearLayoutManager(getActivity()));

        mBinding.elist.setAdapter(mListAdapter);

    }
    public void cargarLista(){
        // lista de compra pendiente
        mViewModel.loadEmployees();
        mViewModel.getEmpleados().observe(getViewLifecycleOwner(), new Observer<List<Employee>>() {
            @Override
            public void onChanged(List<Employee> lista) {





                mListAdapter.setList(lista);
                    //mListAdapter.setProductoList(informesfinal);
                mListAdapter.notifyDataSetChanged();
                }



        });



    }


    @Override
    public void onClickEmp(String id) {
        try{
            
          //  FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

            Bundle bundle=new Bundle();
            bundle.putInt(ARG_ID,Integer.parseInt(id));

            NavHostFragment.findNavController(this).navigate(R.id.nav_detail,bundle);
        }catch (NumberFormatException ex){

        }
    }
}
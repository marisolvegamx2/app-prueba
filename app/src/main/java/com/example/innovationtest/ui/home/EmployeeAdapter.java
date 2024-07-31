package com.example.innovationtest.ui.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.innovationtest.R;
import com.example.innovationtest.data.model.Employee;
import com.example.innovationtest.databinding.EmployeeItemBinding;

import java.util.List;

public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.EmployeeViewHolder> {

    private List<Employee> list;
    private final AdapterCallback callback;


    public EmployeeAdapter(AdapterCallback callback) {


        this.callback = callback;
    }

    public void setList(List<Employee> list) {
       this.list = list;

    }


    @NonNull
    @Override
    public EmployeeAdapter.EmployeeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        EmployeeItemBinding binding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.employee_item, parent, false);
        return new EmployeeViewHolder(binding,callback);
    }



    @Override
    public void onBindViewHolder(@NonNull EmployeeViewHolder holder, int position) {
        // holder.binding.setViewModel(mViewModel);

            holder.binding.setDetalle(list.get(position));



    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    static class EmployeeViewHolder extends RecyclerView.ViewHolder {
        final EmployeeItemBinding binding;

        public EmployeeViewHolder(EmployeeItemBinding binding, AdapterCallback callback) {
            super(binding.getRoot());
            this.binding = binding;
            this.binding.btndet.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    callback.onClickEmp(binding.txtid.getText().toString());
                }
            });

        }


    }
    public interface AdapterCallback {

        void onClickEmp(String id);
    }


}

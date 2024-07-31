package com.example.innovationtest.ui.home;

import android.util.Log;

import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.innovationtest.data.model.Employee;
import com.example.innovationtest.data.model.EmployeesResponse;
import com.example.innovationtest.data.remote.ServiceGenerator;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListViewModel extends ViewModel {

    private MutableLiveData<List<Employee>> empleados;
    public void loadEmployees() {

        empleados=new MutableLiveData<>();
        final Call<EmployeesResponse> batch = ServiceGenerator.getApiService().getEmployees();
        batch.enqueue(new Callback<EmployeesResponse>() {
            @Override
            public void onResponse(Call<EmployeesResponse> call, Response<EmployeesResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    EmployeesResponse respuesta = response.body();
                    if(respuesta.getData()!=null){
                        empleados.setValue(respuesta.getData());
                    }

                }else
                    Log.e("ListViewModel", "algo salio mal en peticion");


            }

            @Override
            public void onFailure(Call<EmployeesResponse> call, Throwable t) {

            }
        });

    }

    public MutableLiveData<List<Employee>> getEmpleados() {
        return empleados;
    }
}
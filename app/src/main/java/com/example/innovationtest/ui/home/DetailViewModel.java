package com.example.innovationtest.ui.home;

import android.util.Log;

import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.innovationtest.data.model.Employee;
import com.example.innovationtest.data.model.EmployeeResponse;
import com.example.innovationtest.data.remote.ServiceGenerator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailViewModel extends ViewModel {

    private  MutableLiveData<Employee> detalle;

    public DetailViewModel() {

    }

    public MutableLiveData<Employee> getDetalle() {
        return detalle;
    }

    public void getEmployee(int id) {

        final Call<EmployeeResponse> batch = ServiceGenerator.getApiService().findEmployee(id);

        batch.enqueue(new Callback<EmployeeResponse>() {
            @Override
            public void onResponse(@Nullable Call<EmployeeResponse> call, @Nullable Response<EmployeeResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    EmployeeResponse respuesta= response.body();
                  if (respuesta.getData()!=null){
                      detalle.setValue(respuesta.getData());
                  }

                }else
                    Log.e("DetailViewModel", "algo salio mal en peticion");

            }

            @Override
            public void onFailure(@Nullable Call<EmployeeResponse> call, @Nullable Throwable t) {
                if (t != null) {
                    Log.e("DetailViewModel", "algo salio mal en peticion"+t.getMessage());
                    //listener.finalizar();
                }
            }
        });
    }
}
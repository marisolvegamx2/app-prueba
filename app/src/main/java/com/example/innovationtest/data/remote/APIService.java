package com.example.innovationtest.data.remote;

import androidx.lifecycle.LiveData;

import com.example.innovationtest.data.model.Employee;
import com.example.innovationtest.data.model.EmployeeResponse;
import com.example.innovationtest.data.model.EmployeesResponse;



import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface APIService {

    @GET("employees")
    Call<EmployeesResponse> getEmployees();

    @GET("employee/{id}")
    Call<EmployeeResponse> findEmployee(@Path("id") int id);

}


package com.example.crudsqlite.dao;

import static com.example.crudsqlite.api.ApiConstants.BASE_URL;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.example.crudsqlite.MainActivity;
import com.example.crudsqlite.api.ApiHandler;
import com.example.crudsqlite.model.Product;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class EmployeeDao {

    private static ApiHandler httpService;
    private static final String PROD_URL = "/product/";
    private static Context context;

    public static void setParams(Context _context) {
        if (httpService == null) {
            httpService = new ApiHandler(_context);
        }
        if (context == null) {
            context = _context.getApplicationContext();
        }
    }


    public static void addEmployee(Product product) {
        String url = BASE_URL + PROD_URL + "save";
        JSONObject json = new JSONObject();
        try {
            json.put("name", product.getName());
            json.put("email", product.getEmail());
            json.put("price", product.getPrice());
            json.put("quantity", product.getQuantity());
            httpService.postString(url, json, EmployeeDao::onAddSuccess, EmployeeDao::onAddError);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void onAddError(VolleyError volleyError) {
        Log.e("req - ", volleyError.toString());
        Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
    }

    private static void onAddSuccess(String result) {
        try {
            Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(context, MainActivity.class);
            context.getApplicationContext().startActivity(intent);
        }catch (Exception e){
        }

    }


    public static void updateEmployee(Product product) {
        String url = BASE_URL + PROD_URL + "save";
        JSONObject json = new JSONObject();
        try {
            json.put("name", product.getName());
            json.put("email", product.getEmail());
            json.put("price", product.getPrice());
            json.put("quantity", product.getQuantity());
            httpService.postString(url, json, EmployeeDao::onAddSuccess, EmployeeDao::onAddError);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void getEmployees(Listener<JSONArray> successHandler, ErrorListener errorHandler) {
        String url = BASE_URL + PROD_URL;
        httpService.getJsonArray(url, successHandler, errorHandler);
    }


    public static void deleteEmployee(int id, Listener successHandler, ErrorListener errorListener) {
        String url = BASE_URL + PROD_URL + "delete/" + id;
        httpService.delete(url, successHandler, errorListener);

    }


}

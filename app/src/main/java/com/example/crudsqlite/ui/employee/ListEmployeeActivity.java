package com.example.crudsqlite.ui.employee;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.example.crudsqlite.MainActivity;
import com.example.crudsqlite.R;
import com.example.crudsqlite.adapter.EmployeeAdapter;
import com.example.crudsqlite.api.ApiHandler;
import com.example.crudsqlite.dao.EmployeeDao;
import com.example.crudsqlite.model.Product;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ListEmployeeActivity extends AppCompatActivity {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_employee);

        EmployeeDao.setParams(getApplicationContext());

        EmployeeDao.getEmployees(this::onGetListSuccess, this::onGetListError);
        listView = findViewById(R.id.emp_list_view);
    }
    private void onGetListSuccess(JSONArray jsonArray) {
        int size = jsonArray.length();
        if (size > 0) {
            List<Product> products = new ArrayList<>(size);
            JSONObject data;
            Product product;
            System.out.println(jsonArray);
            for (int i = 0; i < size; i++) {
                try {
                    data = jsonArray.getJSONObject(i);
                    product = new Product();
                    product.setId(data.getInt("id"));
                    product.setName(data.getString("name"));
                    product.setEmail(data.getString("email"));
                    product.setPrice(data.getString("price"));
                    product.setQuantity(data.getString("quantity"));

                    products.add(product);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            EmployeeAdapter adapter = new EmployeeAdapter(this, products, new ApiHandler(this));
            listView.setAdapter(adapter);
        }

    }

    private void onGetListError(VolleyError error) {
        Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show();
    }

    public void goToHome(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
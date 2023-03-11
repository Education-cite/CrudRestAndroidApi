package com.example.crudsqlite.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.example.crudsqlite.R;
import com.example.crudsqlite.api.ApiHandler;
import com.example.crudsqlite.dao.EmployeeDao;
import com.example.crudsqlite.model.Product;
import com.example.crudsqlite.ui.employee.AddEmployeeActivity;

import java.util.List;

public class EmployeeAdapter extends BaseAdapter {

    private Context context;
    private List<Product> employees;

    private LayoutInflater inflater;
    private int curIdx;


    public EmployeeAdapter(Context context, List<Product> employeeList, ApiHandler db) {
        this.context = context;
        employees = employeeList;
    }


    @Override

    public int getCount() {
        return employees != null ? employees.size() : 0;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if (view == null) {
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.emp_list_item, viewGroup, false);
        }

        TextView nameText = view.findViewById(R.id.emp_list_name);
        TextView emailText = view.findViewById(R.id.emp_list_email);
        TextView priceText = view.findViewById(R.id.emp_list_price);
        TextView quantityText = view.findViewById(R.id.emp_list_quantity);
        ImageView editBtn = view.findViewById(R.id.emp_edit_btn);
        ImageView delBtn = view.findViewById(R.id.emp_del_btn);


        Product employee = employees.get(i);
        System.out.println(employee.toString());
        System.out.println("----------------emp ");
        nameText.setText(employee.getName());
        emailText.setText(employee.getEmail());
        priceText.setText(employee.getPrice());
        quantityText.setText(employee.getQuantity());

        editBtn.setOnClickListener(view12 -> {
            Intent intent = new Intent(context, AddEmployeeActivity.class);
            intent.putExtra("empId", employee.getId());
            intent.putExtra("empName", employee.getName());
            intent.putExtra("empEmail", employee.getEmail());
            intent.putExtra("empPrice", employee.getPrice());
            intent.putExtra("empQuantity", employee.getQuantity());
            context.startActivity(intent);
        });

        delBtn.setOnClickListener(view1 -> {
            EmployeeDao.deleteEmployee(employee.getId(), response -> {
                boolean deleted = response != null;
                System.out.println("Delete---" + employee.getId());
                if (deleted) {
                    employees.remove(i);
                    notifyDataSetChanged();
                }
                String message = deleted ? "Successfully deleted" : "Failed to delete";
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
            }, this::onDeleteError);
        });
        return view;
    }

    private void onDeleteError(VolleyError error) {
        Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
    }
}

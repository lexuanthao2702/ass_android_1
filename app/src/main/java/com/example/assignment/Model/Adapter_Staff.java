package com.example.assignment.Model;

import static android.app.Activity.*;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;

import com.example.assignment.Add_Staff;
import com.example.assignment.R;
import com.example.assignment.Staff;
import com.example.assignment.Welcom;

import java.util.ArrayList;

// BaseAdapter là một lớp trừu tượng trong android SDk
// cung cấp các phương pháp để hiện thị dử liệu trong listview hoặc GridView
public class Adapter_Staff extends BaseAdapter {
    private Context context;
    private ArrayList<DAO_Staff> list, listold;
    private ArrayList<DAO_Add> list1;
    private String selectedItemSpinner = "";

    public Adapter_Staff(Context context, ArrayList<DAO_Staff> list, ActivityResultLauncher<Intent> getdate) {
        this.context = context;
        this.list = list;
        this.listold = list;

    }

    @Override
    public int getCount() {
        return list != null ? list.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
        DAO_Staff dao_staff = (DAO_Staff) getItem(position);

        // ánh xạ
        TextView txtMaNV = view.findViewById(R.id.txt1);
        TextView txtName = view.findViewById(R.id.txt2);
        TextView txtPhongBan = view.findViewById(R.id.txt3);
        Button btnUpdate = view.findViewById(R.id.btnUpdate);
        Button btnDatele = view.findViewById(R.id.btnDatele);
        // lấy dử liệu
        txtMaNV.setText(list.get(position).getMaNV());
        txtName.setText(list.get(position).getName());
        txtPhongBan.setText(list.get(position).getPhongBan());
        // sự kiện nút Detele
        btnDatele.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.remove(position);
                notifyDataSetChanged();
            }
        });

        // tạo sự kiện nút sửa
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // tạo Dialog để gán với layout
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                // thành lập Dialog
                LayoutInflater inflater1 = ((Activity) context).getLayoutInflater();
                View view1 = inflater1.inflate(R.layout.staff_update, null);
                builder.setView(view1);
                // ánh xạ
                final EditText MaNVUpdate = view1.findViewById(R.id.MaNVUpdate);
                final EditText UsernameUpdate = view1.findViewById(R.id.UsernameUpdate);
                final Spinner spinner = view1.findViewById(R.id.sp);
                // kết nối dử liệu vào spinner
                Adapter_Add adapter = new Adapter_Add(context, sua());
                spinner.setAdapter(adapter);
                // click chọn item spinner
                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        selectedItemSpinner = ((DAO_Add) spinner.getItemAtPosition(position)).getName();

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
                builder.setTitle("Update Form");

                // xử lý button update
                builder.setPositiveButton("Update", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String MaNV = MaNVUpdate.getText().toString();
                        String name = UsernameUpdate.getText().toString();
                        // cập nhập vào list
                        list.get(position).setMaNV("Mã NV: " + MaNV);
                        list.get(position).setName("Họ Tên: " + name);
                        list.get(position).setPhongBan("Phòng Ban: "+selectedItemSpinner);
                        notifyDataSetChanged();
                    }

                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();


            }
        });
        return view;
    }

    // sự kiện tìm kiếm
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String s = constraint.toString();
                if (s.isEmpty()) {
                    list = listold;
                } else {
                    ArrayList<DAO_Staff> listSVS = new ArrayList<>();
                    for (DAO_Staff sv : listold) {
                        if (sv.getName().toLowerCase().contains(s.toLowerCase())) { // kiểm tra chữ hoa chữ thường
                            listSVS.add(sv);
                        }
                    }
                    list = listSVS;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = list;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                list = (ArrayList<DAO_Staff>) results.values;
                notifyDataSetChanged();
            }
        };
    }

    public ArrayList<DAO_Add> sua() {
        list1 = new ArrayList<>();
        list1.add(new DAO_Add(R.mipmap.hanh_chinh, "Hành Chính"));
        list1.add(new DAO_Add(R.mipmap.dt, "Đào Tạo"));
        list1.add(new DAO_Add(R.mipmap.nv, "Nhân Viên"));
        list1.add(new DAO_Add(R.mipmap.kt, "Kế Toán"));
        return list1;
    }



}

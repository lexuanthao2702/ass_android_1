package com.example.assignment.Model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.assignment.R;

import java.util.ArrayList;

// BaseAdapter là một lớp trừu tượng trong android SDk
// cung cấp các phương pháp để hiện thị dử liệu trong listview hoặc GridView
public class Adapter_Add extends BaseAdapter {
    private Context context;
    private ArrayList<DAO_Add> list;

    public Adapter_Add(Context context, ArrayList<DAO_Add> list) {
        this.context = context;
        this.list = list;
    }
    // trả về số lượng item trong listView hoặc GridView
    @Override
    public int getCount() {
        return list != null ? list.size() : 0;
    }
    // trả về item tại vị trí position
    @Override
    public Object getItem(int position) {
        return list.get(position);
    }
    // trả về id của item tại vị trí position
    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Khởi tạo và liên kết Layout
        View rowView = LayoutInflater.from(context).inflate(R.layout.spinner_item, parent, false);

        // Ánh xạ từng thành phần trên layout
        ImageView imgLogo = rowView.findViewById(R.id.imgV);
        TextView txtName = rowView.findViewById(R.id.txtName);

        imgLogo.setImageResource(list.get(position).getImage());
        txtName.setText(list.get(position).getName());
        return rowView;
    }

}


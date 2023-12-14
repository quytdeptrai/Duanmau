package com.example.duanmauassignment.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duanmauassignment.R;
import com.example.duanmauassignment.adapter.ThanhVienAdapter;
import com.example.duanmauassignment.dao.ThanhVienDAO;
import com.example.duanmauassignment.model.ThanhVien;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class QLThanhVienFragment extends Fragment {
    ThanhVienDAO thanhVienDAO;
    RecyclerView recyclerThanhVien;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_qlthanhvien,container,false);
        recyclerThanhVien = view.findViewById(R.id.recyclerThanhVien);
        FloatingActionButton floatAdd = view.findViewById(R.id.floatAdd);
        thanhVienDAO = new ThanhVienDAO(getContext());
        loadData();//hiển thị danh sách
        floatAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });
        return view;
    }
    private void loadData(){
        ArrayList<ThanhVien> list = thanhVienDAO.getDSThanhVien();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerThanhVien.setLayoutManager(linearLayoutManager);
        ThanhVienAdapter adapter = new ThanhVienAdapter(getContext(),list,thanhVienDAO);
        recyclerThanhVien.setAdapter(adapter);
    }
    private void showDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_them_thanhvien,null);
        builder.setView(view);
        EditText edtHoTen = view.findViewById(R.id.edtHoTen);
        EditText edtNamSinh = view.findViewById(R.id.edtNamSinh);
        builder.setNegativeButton("Thêm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String hoten = edtHoTen.getText().toString();
                String namsinh = edtNamSinh.getText().toString();
                boolean check = thanhVienDAO.themThanhVien(hoten, namsinh);
                if (check){
                    Toast.makeText(getContext(),"Thêm thành công", Toast.LENGTH_SHORT).show();
                    //Load data
                    loadData();
                }else{
                    Toast.makeText(getContext(),"Thêm Thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });
        builder.setPositiveButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getContext(),"Hủy thành công", Toast.LENGTH_SHORT).show();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();//show dấu + ở quản lý thành viên
    }
}

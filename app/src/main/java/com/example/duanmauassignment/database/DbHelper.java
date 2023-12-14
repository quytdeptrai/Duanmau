package com.example.duanmauassignment.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {
    public DbHelper(Context context){
        super(context, "DANGKYMONHOC",null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String dbThuThu = "CREATE TABLE THUTHU(matt text primary key, hoten text, matkhau text, loaitaikhoan text)";
        sqLiteDatabase.execSQL(dbThuThu);

        String dbThanhVien = "CREATE TABLE THANHVIEN(matv integer primary key autoincrement, hoten text, namsinh text)";
        sqLiteDatabase.execSQL(dbThanhVien);

        String dbLoai = "CREATE TABLE LOAISACH(maloai integer primary key autoincrement, tenloai text)";
        sqLiteDatabase.execSQL(dbLoai);

        String dbSach = "CREATE TABLE SACH(masach integer primary key autoincrement, tensach text, giathue integer, maloai integer references LOAISACH(maloai))";
        sqLiteDatabase.execSQL(dbSach);

        String dbPhieuMuon = "CREATE TABLE PHIEUMUON(mapm integer primary key autoincrement, matv integer references THANHVIEN(matv), matt text references THUTHU(matt), masach integer references SACH(masach), ngay text, trasach integer, tienthue integer)";
        sqLiteDatabase.execSQL(dbPhieuMuon);

        //data  mẫu
        sqLiteDatabase.execSQL("INSERT INTO LOAISACH VALUES (1, 'CONAN'),(2,'DORAEMON'),(3, 'TRẠNG QUỶNH')");
        sqLiteDatabase.execSQL("INSERT INTO SACH VALUES (1, 'Conan phá án', 12500, 1), (2, 'Doraemon Và Nobita', 11000, 1), (3, 'Trạng Quỷnh siêu thông minh', 12000, 3),(4, 'doraemon và những người bạn', 27000, 4),(5, 'Conan và Ran', 19000, 3),(6, 'Anh yêu em', 10000, 2),(7, 'Chú voi con', 8000, 1)");
        sqLiteDatabase.execSQL("INSERT INTO THUTHU VALUES ('thuthu01','Ngô Trung Kiên','123456','Admin'),('thuthu02','Gái Xinh','123456','Thủ thư')");
        sqLiteDatabase.execSQL("INSERT INTO THANHVIEN VALUES (1,'Cao Thu Trang','2000'),(2,'Trần Mỹ Kim','2000'),(3,'Trần Thị Hà','2004'),(4,'Lê Văn Anh','2003'),(5,'Trần Thị Kim Dung','2002'),(6,'Lê Hoàng Luân','2001'),(7,'Trần Văn Đức','2004'),(8,'Châu Thị Thủy','2002'),(9,'Nguyễn Thị Thúy','2003'),(10,'Mai Thị Vân','2003')");
        //trả sách: 1: đã trả - 0: chưa trả
        sqLiteDatabase.execSQL("INSERT INTO PHIEUMUON VALUES (1,1,'thuthu01', 1, '20/09/2022', 1, 20000),(2,1,'thuthu01', 3, '25/09/2022', 0, 34000),(3,2,'thuthu02', 1, '05/10/2022', 1, 52000),(4,4,'thuthu01', 6, '09/10/2022', 0, 32000),(5,8,'thuthu02', 5, '20/10/2022', 0, 59000),(6,10,'thuthu01', 4, '16/10/2022', 1, 15000),(7,9,'thuthu01', 5, '14/10/2022', 0, 24000)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        if (i != i1){
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS THUTHU");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS THANHVIEN ");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS LOAISACH");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS SACH");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS PHIEUMUON");
            onCreate(sqLiteDatabase);
        }
    }
}

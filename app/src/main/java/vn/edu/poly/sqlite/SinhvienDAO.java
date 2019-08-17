package vn.edu.poly.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class SinhvienDAO {
    private SinhvienRederSQL sinhvienRederSQL;

    public SinhvienDAO(Context context) {
        sinhvienRederSQL = new SinhvienRederSQL(context);
    }

    public long insertsv(Sinhvien sinhvien) {
        SQLiteDatabase sqLiteDatabase = sinhvienRederSQL.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(sinhvienRederSQL.COL_ID, sinhvien.getId());
        contentValues.put(sinhvienRederSQL.COL_TEN, sinhvien.getTen());
        contentValues.put(sinhvienRederSQL.COL_TUOI, sinhvien.getTuoi());

        long result = sqLiteDatabase.insert(sinhvienRederSQL.TABLE_NAME, null, contentValues);
        sqLiteDatabase.close();
        return result;
    }

    public long updatesv(Sinhvien sinhvien) {
        SQLiteDatabase sqLiteDatabase = sinhvienRederSQL.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(sinhvienRederSQL.COL_ID, sinhvien.getId());
        contentValues.put(sinhvienRederSQL.COL_TEN, sinhvien.getTen());
        contentValues.put(sinhvienRederSQL.COL_TUOI, sinhvien.getTuoi());

        long result = sqLiteDatabase.update(sinhvienRederSQL.TABLE_NAME, contentValues, sinhvienRederSQL.COL_ID + "=?", new String[]{sinhvien.getId()});
        sqLiteDatabase.close();
        return result;
    }

    public List<Sinhvien> getAllsv() {
        List<Sinhvien> sinhvienList = new ArrayList<>();

        SQLiteDatabase sqLiteDatabase = sinhvienRederSQL.getReadableDatabase();

        String query = "SELECT * FROM " + sinhvienRederSQL.TABLE_NAME;

        Cursor cursor = sqLiteDatabase.rawQuery(query, null);

        cursor.moveToFirst();
        while (cursor.isAfterLast() == false) {
            Sinhvien sinhvien = new Sinhvien();
            sinhvien.setId(cursor.getString(cursor.getColumnIndex(sinhvienRederSQL.COL_ID)));
            sinhvien.setTen(cursor.getString(cursor.getColumnIndex(sinhvienRederSQL.COL_TEN)));
            sinhvien.setTuoi(Integer.parseInt(cursor.getString(cursor.getColumnIndex(sinhvienRederSQL.COL_TUOI))));
            sinhvienList.add(sinhvien);
            cursor.moveToNext();
        }
        return sinhvienList;
    }


    public List<Sinhvien> Search(String id) {
        List<Sinhvien> sinhvienList = new ArrayList<>();

        SQLiteDatabase sqLiteDatabase = sinhvienRederSQL.getReadableDatabase();

        String query = "SELECT * FROM " + sinhvienRederSQL.TABLE_NAME + " where " + sinhvienRederSQL.COL_ID + "=?";

        Cursor cursor = sqLiteDatabase.rawQuery(query, new String[]{id});

        cursor.moveToFirst();

        Sinhvien sinhvien = new Sinhvien();
        sinhvien.setId(cursor.getString(cursor.getColumnIndex(sinhvienRederSQL.COL_ID)));
        sinhvien.setTen(cursor.getString(cursor.getColumnIndex(sinhvienRederSQL.COL_TEN)));
        sinhvien.setTuoi(Integer.parseInt(cursor.getString(cursor.getColumnIndex(sinhvienRederSQL.COL_TUOI))));
        sinhvienList.add(sinhvien);

        return sinhvienList;
    }




    public List<Sinhvien> Order() {
        List<Sinhvien> sinhvienList = new ArrayList<>();

        SQLiteDatabase sqLiteDatabase = sinhvienRederSQL.getReadableDatabase();

        String query = "SELECT * FROM " + sinhvienRederSQL.TABLE_NAME + " order by " + sinhvienRederSQL.COL_TUOI + " asc";

        Cursor cursor = sqLiteDatabase.rawQuery(query, null);

        cursor.moveToFirst();
        while (cursor.isAfterLast() == false) {
            Sinhvien sinhvien = new Sinhvien();
            sinhvien.setId(cursor.getString(cursor.getColumnIndex(sinhvienRederSQL.COL_ID)));
            sinhvien.setTen(cursor.getString(cursor.getColumnIndex(sinhvienRederSQL.COL_TEN)));
            sinhvien.setTuoi(Integer.parseInt(cursor.getString(cursor.getColumnIndex(sinhvienRederSQL.COL_TUOI))));
            sinhvienList.add(sinhvien);
            cursor.moveToNext();
        }
        return sinhvienList;
    }





    public void deletesv(String id) {
        SQLiteDatabase sqLiteDatabase = sinhvienRederSQL.getWritableDatabase();

        sqLiteDatabase.delete(sinhvienRederSQL.TABLE_NAME, sinhvienRederSQL.COL_ID + "=?", new String[]{id});

    }
}

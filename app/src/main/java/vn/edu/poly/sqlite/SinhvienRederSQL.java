package vn.edu.poly.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SinhvienRederSQL extends SQLiteOpenHelper {


    public SinhvienRederSQL(Context context) {
        super(context, "sinhvien.sb", null, 1);
    }


    public static String TABLE_NAME = "Sinhvien";

    public static String COL_ID = "id";

    public static String COL_TEN = "ten";

    public static String COL_TUOI = "tuoi";

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String create = "CREATE TABLE " + TABLE_NAME +"("+COL_ID+" text primary key, "+COL_TEN+" text, "+COL_TUOI+" integer)";
        sqLiteDatabase.execSQL(create);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}

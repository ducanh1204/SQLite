package vn.edu.poly.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText edtID, edtTen, edtTuoi, edtSearch;

    private ListView lvList;

    private SinhvienDAO sinhvienDAO;

    private SinhvienAdapter sinhvienAdapter;

    private List<Sinhvien> sinhvienList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtID = findViewById(R.id.edtID);
        edtTen = findViewById(R.id.edtTen);
        edtTuoi = findViewById(R.id.edtTuoi);
        edtSearch = findViewById(R.id.edtSearchID);
        lvList = findViewById(R.id.lvList);




        sinhvienDAO = new SinhvienDAO(MainActivity.this);


        //xóa keyword
        edtSearch.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                String search= edtSearch.getText().toString().trim();
                if(search.equals("")){
                    hienthi();
                }
                return false;
            }
        });



        hienthi();
        findViewById(R.id.btnthemsv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {

                    String id = edtID.getText().toString().trim();
                    String ten = edtTen.getText().toString().trim();
                    String tuoii = edtTuoi.getText().toString().trim();
                    int tuoi = Integer.parseInt(edtTuoi.getText().toString().trim());
                    Sinhvien sinhvien = new Sinhvien();
                    sinhvien.setId(id);
                    sinhvien.setTen(ten);
                    sinhvien.setTuoi(tuoi);
                    if (ten.equals("") || id.equals("") || tuoii.equals("")) {
                        Toast.makeText(MainActivity.this, "Nhập đủ thông tin", Toast.LENGTH_SHORT).show();
                    } else {
                        long result = sinhvienDAO.insertsv(sinhvien);

                        if (result > 0) {
                            hienthi();
                            clearForm();
                            Toast.makeText(MainActivity.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(MainActivity.this, "Thêm thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }

                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, "Tuổi sai định dạng, thêm thất bại", Toast.LENGTH_SHORT).show();
                }


            }
        });



        findViewById(R.id.btnsua).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {

                    String id = edtID.getText().toString().trim();
                    String ten = edtTen.getText().toString().trim();
                    String tuoii = edtTuoi.getText().toString().trim();
                    int tuoi = Integer.parseInt(edtTuoi.getText().toString().trim());
                    Sinhvien sinhvien = new Sinhvien();
                    sinhvien.setId(id);
                    sinhvien.setTen(ten);
                    sinhvien.setTuoi(tuoi);
                    if (ten.equals("") || id.equals("") || tuoii.equals("")) {
                        Toast.makeText(MainActivity.this, "Nhập đủ thông tin", Toast.LENGTH_SHORT).show();
                    } else {
                        long result = sinhvienDAO.updatesv(sinhvien);

                        if (result > 0) {
                            hienthi();
                            Toast.makeText(MainActivity.this, "Sửa thành công", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(MainActivity.this, "Sửa thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }

                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, "Tuổi sai định dạng, Sửa thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });




        findViewById(R.id.btnSearch).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    String idd= edtSearch.getText().toString().trim();
                    sinhvienList=sinhvienDAO.Search(idd);
                    sinhvienAdapter = new SinhvienAdapter(MainActivity.this, sinhvienList,edtID,edtTen,edtTuoi,lvList);
                    lvList.setAdapter(sinhvienAdapter);
                } catch (Exception e){
                }
            }
        });
        findViewById(R.id.btnSapxep).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sinhvienList=sinhvienDAO.Order();
                sinhvienAdapter = new SinhvienAdapter(MainActivity.this, sinhvienList,edtID,edtTen,edtTuoi,lvList);
                lvList.setAdapter(sinhvienAdapter);
            }
        });


    }


    public void hienthi() {
        sinhvienList = sinhvienDAO.getAllsv();
        sinhvienAdapter = new SinhvienAdapter(MainActivity.this, sinhvienList,edtID,edtTen,edtTuoi,lvList);
        lvList.setAdapter(sinhvienAdapter);
    }

    public void clearForm() {
        edtTuoi.setText("");
        edtID.setText("");
        edtTen.setText("");
    }
}

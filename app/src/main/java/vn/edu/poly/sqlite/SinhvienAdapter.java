package vn.edu.poly.sqlite;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;


public class SinhvienAdapter extends BaseAdapter {


    private List<Sinhvien> sinhvienList;
    private Context context;
    private EditText edtID, edtTen,edtTuoi;
    private SinhvienDAO sinhvienDAO;
    private ListView lvList;


    public SinhvienAdapter(Context context,List<Sinhvien> sinhvienList,EditText edtID,EditText edtTen, EditText edtTuoi,ListView lvList) {
        this.sinhvienList = sinhvienList;
        this.context = context;
        this.edtID=edtID;
        this.edtTen=edtTen;
        this.edtTuoi=edtTuoi;
        this.lvList=lvList;
    }

    @Override
    public int getCount() {
        return sinhvienList.size();
    }

    @Override
    public Sinhvien getItem(int i) {
        return sinhvienList.get(i);
    }


    @Override
    public long getItemId(int i) {
        return 0;
    }


    private class ViewHolder{
        TextView tvID,tvName,tvAge;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder=null;
        final Sinhvien sinhvien = (Sinhvien) getItem(i);
        if(view ==null){
            viewHolder = new ViewHolder();
            view = LayoutInflater.from(context).inflate(R.layout.row,viewGroup,false);
            viewHolder.tvID=view.findViewById(R.id.tvID);
            viewHolder.tvName=view.findViewById(R.id.tvName);
            viewHolder.tvAge=view.findViewById(R.id.tvAge);
            view.setTag(viewHolder);
        } else{
            viewHolder = (ViewHolder) view.getTag();
        }

        viewHolder.tvID.setText(sinhvien.getId());
        viewHolder.tvName.setText(sinhvien.getTen());
        viewHolder.tvAge.setText(sinhvien.getTuoi()+"");


        view.findViewById(R.id.btnX).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Xóa sv khỏi danh sách này?");
                builder.setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                builder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        sinhvienDAO = new SinhvienDAO(context);
                        sinhvienDAO.deletesv(sinhvien.getId());
                        sinhvienList=sinhvienDAO.getAllsv();
                        lvList.setAdapter(SinhvienAdapter.this);
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();


            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edtTen.setText(sinhvien.getTen());
                edtID.setText(sinhvien.getId());
                edtTuoi.setText(sinhvien.getTuoi()+"");
            }
        });

        return view;
    }
}

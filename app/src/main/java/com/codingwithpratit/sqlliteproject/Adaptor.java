package com.codingwithpratit.sqlliteproject;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import Model.Data;

public class Adaptor extends ArrayAdapter<String> {

    //private final Activity context;

    private final String[] Name;
    private  final String[] Snn;
    private final String[] PhoneNumber;
    private final String[] Address;
    DatabaseCrud obj;
    ListView listitem;
    Data dataobj;
    private final Context context;


    public Adaptor(Context context, String[] Snn,String[] Name, String[] PhoneNumber,String[] Address) {
        super(context, R.layout.custom, Name);
        // TODO Auto-generated constructor stub
        this.context=context;
        this.Name=Name;
       this.PhoneNumber=PhoneNumber;
       this.Address=Address;
       this.Snn=Snn;
    }
    public View getView(int position, View view, ViewGroup parent) {

                LayoutInflater inflater = (LayoutInflater) context
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View rowView = inflater.inflate(R.layout.custom, parent, false);

                obj = new DatabaseCrud(context);
                EditText name = (EditText) rowView.findViewById(R.id.tv_name);
                EditText address = (EditText) rowView.findViewById(R.id.tv_address);
                EditText Number = (EditText) rowView.findViewById(R.id.tv_phone);
                EditText snn = (EditText) rowView.findViewById(R.id.tv_snn);
                ImageButton deletebtn = (ImageButton) rowView.findViewById(R.id.btn_delete);
                 Button upadtebtn = (Button) rowView.findViewById(R.id.btn_update);


                     name.setText(Name[position]);
                     Number.setText(PhoneNumber[position]);
                     address.setText(Address[position]);
                     snn.setText(Snn[position]);
                     name.setEnabled(false);
                     snn.setEnabled(false);
                     address.setEnabled(false);
                     Number.setEnabled(false);

        try {

                deletebtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        listitem=(ListView)v.findViewById(R.id.lv_result);
                        dataobj=new Data(snn.getText().toString());
                        Integer isdeleted = obj.deleteData(dataobj);

                        if (isdeleted > 0) {
                            Toast.makeText(context, "delete success", Toast.LENGTH_SHORT).show();
                        }else
                            Toast.makeText(context, "delete failure", Toast.LENGTH_SHORT).show();
                    }

                });
                upadtebtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        new CountDownTimer(30000, 1000) {

                            public void onTick(long millisUntilFinished) {
                                name.setEnabled(true);
                                snn.setEnabled(true);
                                address.setEnabled(true);
                                Number.setEnabled(true);
                                upadtebtn.setBackgroundColor(0xFFFF0000);
                                upadtebtn.setText(""+ millisUntilFinished / 1000);
                            }

                            public void onFinish() {

                                name.setEnabled(false);
                                snn.setEnabled(false);
                                address.setEnabled(false);
                                Number.setEnabled(false);
                                 upadtebtn.setBackgroundResource(R.drawable.edit);
                                upadtebtn.setText("");
                                    dataobj=new Data(snn.getText().toString(), name.getText().toString(),
                                            Number.getText().toString(), address.getText().toString());
                                boolean isUpdated = obj.updateData(dataobj);
                                if (isUpdated == true)
                                    Toast.makeText(context, "update success", Toast.LENGTH_SHORT).show();
                                else
                                    Toast.makeText(context, "update failure", Toast.LENGTH_SHORT).show();

                            }
                        }.start();

                    }
                });



            }catch (Exception e){
                Toast.makeText(context,e.getMessage(),Toast.LENGTH_LONG).show();
            }
        return rowView;
    };

}

package com.codingwithpratit.sqlliteproject;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import Model.Data;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btn_Add;
    ImageButton btn_update,btn_delete;
    EditText etd_name,etd_phone,etd_snn,etd_address;
    ListView listView;
    DatabaseCrud DatabaseObj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Start();



        
    }
    public void Start(){
        //btn...

        btn_Add =(Button)findViewById(R.id.btn_insert);


        //list
        listView=(ListView)findViewById(R.id.lv_result);


        //EditText..
        etd_name=(EditText)findViewById(R.id.et_name);
        etd_phone=(EditText)findViewById(R.id.et_phone);
        etd_snn=(EditText)findViewById(R.id.et_ssn);
        etd_address=(EditText)findViewById(R.id.et_address);
       //DATBASE
        DatabaseObj = new DatabaseCrud(this);
        //onClick
        btn_Add.setOnClickListener(this);
//        btn_update.setOnClickListener(this);
//        btn_delete.setOnClickListener(this);
        DisplayData();

    }

    @Override
    public void onClick(View v) {
        int Choice=v.getId();
        switch (Choice){
            case R.id.btn_insert:
                try {
                    if(etd_snn.getText().equals("")){
                        break;
                    }

                    Data DataObj = new Data(etd_snn.getText().toString(), etd_name.getText().toString(),
                            etd_phone.getText().toString(), etd_address.getText().toString());

                       boolean isInserted = DatabaseObj.insertData(DataObj);
                       DisplayData();


                       if (isInserted == true) {
                           Toast.makeText(getApplicationContext(), "insert success", Toast.LENGTH_SHORT).show();
                       } else {
                           Toast.makeText(getApplicationContext(), "insert failure", Toast.LENGTH_SHORT).show();

                       }

                }catch (Exception e){
                    Toast.makeText(this,e.getMessage(),Toast.LENGTH_LONG).show();
                }
                break;

        }
    }
    public void DisplayData(){
        Cursor result = DatabaseObj.viewAllData();
        if (result.getCount() == 0) {
            //showMessage ("error", "Nothing found");
            Toast.makeText(this,"error"+"Nothing found",Toast.LENGTH_LONG).show();
            return;
        }

        String[] name=new String[result.getCount()];
        String[] address=new String[result.getCount()];
        String[] phonenumber=new String[result.getCount()];
        String[] snn=new String[result.getCount()];

        while(result.moveToNext()){

            snn[result.getPosition()]=result.getString(0);
            name[result.getPosition()]=result.getString(1);
            phonenumber[result.getPosition()]=result.getString(2);
            address[result.getPosition()]=result.getString(3);

            Toast.makeText(this,"result.getString(0):-"+result.getPosition(),Toast.LENGTH_LONG).show();
        }

            Adaptor adapter = new Adaptor(this, snn, name, phonenumber, address);
            listView.setAdapter(adapter);

    }
}
package com.example.finish;

import android.content.ContentValues;
import android.content.Context;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Act_Products extends AppCompatActivity {
    ImageView Img_Back, Img_Search,Img_More,Img_Sale;
    TextView Text_Title, Text_Id, Text_Sum;
    EditText Edit_Title, Edt_ProName, Edt_ProPrice;
    Button Btn_Insert, Btn_Delete, Btn_Search, Btn_Update, Btn_Id, Btn_Name, Btn_Price, Btn_Sum, Btn_Count;
    LinearLayout Lnr_ListProduct;
    DrawerLayout Drawer;
    Globalclass _Globalclass = new Globalclass();
    ClassDatabase _ClassDatabase;
    Context Ctx;
    TextWatcher OnTextChangePrice = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence Text, int start, int count, int after) {
            //  _Globalclass.DisPlayToast(Act_Products.this,"Before Text Change"+Text);
        }

        @Override
        public void onTextChanged(CharSequence Text, int start, int before, int count) {
            //_Globalclass.DisPlayToast(Act_Products.this,"On Text Change"+Text);
            try {
                String SearchPrice = BuildConfig.FLAVOR;
                if (Text.toString().trim().length() != 0) {
                    SearchPrice = " WHERE Priceproduct Like '%" + Text.toString().trim() + "%'";

                }
                Act_Products.this.PrintData(SearchPrice, null);
            } catch (Exception Error) {
                _Globalclass.DisPlayToast(Act_Products.this, Error.getMessage());
            }
        }

        @Override
        public void afterTextChanged(Editable Text) {
            //_Globalclass.DisPlayToast(Act_Products.this,"After Text Change"+Text);
        }
    };
    TextWatcher OnTextChangeName = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence Text, int start, int count, int after) {
            // _Globalclass.DisPlayToast(Act_Products.this,"Before Text Change"+Text);
        }

        @Override
        public void onTextChanged(CharSequence Text, int start, int before, int count) {
            // _Globalclass.DisPlayToast(Act_Products.this,"On Text Change"+Text);
            try {
                String SearchName = BuildConfig.FLAVOR;
                if (Text.toString().trim().length() != 0) {
                    SearchName = " WHERE Nameproduct Like '%" + Text.toString().trim() + "%' ";

                }
                Act_Products.this.PrintData(SearchName, null);

            } catch (Exception Error) {
                _Globalclass.DisPlayToast(Act_Products.this, Error.getMessage());
            }
        }

        @Override
        public void afterTextChanged(Editable Text) {
            //   _Globalclass.DisPlayToast(Act_Products.this,"After Text Change"+Text);
        }
    };
    View.OnClickListener rowClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            ContentValues _ContentValues = (ContentValues) v.getTag();
            Text_Id.setText(_ContentValues.getAsString("Id_Auto"));
            Edt_ProPrice.setText(_ContentValues.getAsString("Price"));
            Edt_ProName.setText(_ContentValues.getAsString("Name"));
            Btn_Delete.setEnabled(true);
            Btn_Update.setEnabled(true);

        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_products);
        _ClassDatabase = new ClassDatabase();
        Ctx = this;
        _ClassDatabase.CreateTable(Ctx);
        Init();
        SumOrCount();
        PrintData(BuildConfig.FLAVOR, null);
    }


    void Init() {
        Img_Back = findViewById(R.id.Img_Back);
        Img_Sale = findViewById(R.id.Img_Sale);
        Img_Search=findViewById(R.id.Img_Search);
        Text_Title = findViewById(R.id.Text_Title);
        Text_Id = findViewById(R.id.Text_Id);
        Text_Sum = findViewById(R.id.Text_Sum);
        Edit_Title = findViewById(R.id.Edit_Title);
        Edt_ProName = findViewById(R.id.Edt_ProName);
        Edt_ProPrice =findViewById(R.id.Edt_ProPrice);
        Btn_Insert = findViewById(R.id.Btn_Insert);
        Btn_Delete = findViewById(R.id.Btn_Delete);
        Btn_Search = findViewById(R.id.Btn_Search);
        Btn_Update = findViewById(R.id.Btn_Update);
        Btn_Id = findViewById(R.id.Btn_Id);
        Btn_Name =  findViewById(R.id.Btn_Name);
        Btn_Price = findViewById(R.id.Btn_Price);
        Btn_Sum = findViewById(R.id.Btn_Sum);
        Btn_Count = findViewById(R.id.Btn_Count);
        Lnr_ListProduct = findViewById(R.id.Lnr_ListProduct);
        Drawer = findViewById(R.id.Drawer);
        Edt_ProName.addTextChangedListener(OnTextChangeName);
        Edt_ProPrice.addTextChangedListener(OnTextChangePrice);
        Edt_ProName.removeTextChangedListener(OnTextChangeName);
        Edt_ProPrice.removeTextChangedListener(OnTextChangePrice);
    }




    public void BtnInsert(View _v) {
        try {

            if (!ValidateName()|!ValidatePrice()){
                return;
            }
            String input="Product Name: "+Edt_ProName.getText().toString().trim();
            input +="\n";
            input +="Product Price: "+Edt_ProPrice.getText().toString().trim();
            Toast.makeText(this,input,Toast.LENGTH_LONG).show();
            _ClassDatabase.CreateTable(Ctx);
            String Nameproduct = Edt_ProName.getText().toString().trim();
            int Priceproduct = Integer.parseInt(Edt_ProPrice.getText().toString().trim());
            _ClassDatabase.Insert(Ctx, Priceproduct, Nameproduct);
            PrintData(BuildConfig.FLAVOR, null);
            SumOrCount();
            Edt_ProPrice.removeTextChangedListener(OnTextChangePrice);
            Edt_ProName.removeTextChangedListener(OnTextChangeName);
            Edt_ProPrice.setText(null);
            Edt_ProName.setText(null);
            Edt_ProPrice.addTextChangedListener(OnTextChangePrice);
            Edt_ProName.addTextChangedListener(OnTextChangeName);
        }catch (Exception Error){
            _Globalclass.DisPlayToast(Act_Products.this,Error.getMessage());
        }
    }

    public void BtnDelete(View _v) {
        _ClassDatabase.CreateTable(Ctx);
        String Id_Product = Text_Id.getText().toString().trim();
        _ClassDatabase.Delete(Ctx, Id_Product);
        PrintData(BuildConfig.FLAVOR, null);
        SumOrCount();
        Edt_ProPrice.removeTextChangedListener(OnTextChangePrice);
        Edt_ProName.removeTextChangedListener(OnTextChangeName);
        Edt_ProPrice.setText(null);
        Edt_ProName.setText(null);
        Edt_ProPrice.addTextChangedListener(OnTextChangePrice);
        Edt_ProName.addTextChangedListener(OnTextChangeName);
    }

    public void BtnSearch(View _v) {
        //  PrintData(" WHERE Nameproduct Like '%" + Edt_ProName.getText().toString().trim() + "%'",null);
        _ClassDatabase.CreateTable(Ctx);
        String Nameproduct = Edt_ProName.getText().toString().trim();
        String SearchN = "WHERE Nameproduct Like '%" + Nameproduct + "%'";
        PrintData(SearchN, null);
        SumOrCount();
//        int Priceproduct = Integer.parseInt(Edt_ProPrice.getText().toString().trim());
//        String SearchP = "WHERE Priceproduct Like '%" + Priceproduct + "%'";
//        PrintData(SearchP, null);
    }

    public void BtnUpdate(View _v) {
        _ClassDatabase.CreateTable(Ctx);
        ContentValues _ContentValues = new ContentValues();
        _ContentValues.put("Id_Auto", Text_Id.getText().toString());
        _ContentValues.put("Priceproduct", Edt_ProPrice.getText().toString());
        _ContentValues.put("Nameproduct", Edt_ProName.getText().toString());
        _ClassDatabase.Update(Ctx, _ContentValues);
        PrintData(BuildConfig.FLAVOR, null);
        SumOrCount();
        Edt_ProPrice.removeTextChangedListener(OnTextChangePrice);
        Edt_ProName.removeTextChangedListener(OnTextChangeName);
        Edt_ProPrice.setText(null);
        Edt_ProName.setText(null);
        Edt_ProPrice.addTextChangedListener(OnTextChangePrice);
        Edt_ProName.addTextChangedListener(OnTextChangeName);

    }

    public void SearchClick(View _v) {
        if (_v.getTag().equals("Back")) {

            if (Edit_Title.getVisibility() == View.GONE){
                Edit_Title.setVisibility(View.GONE);
                Img_Search.setVisibility(View.VISIBLE);
                Text_Title.setVisibility(View.VISIBLE);

            }
        } else if (_v.getTag().equals("Search")) {
            Edit_Title.setVisibility(View.VISIBLE);
            Img_Search.setVisibility(View.GONE);
            Img_Sale.setVisibility(View.GONE);
            Text_Title.setVisibility(View.GONE);

        }

    }public void MenuClick(View _v){
        if (_v.getTag().equals("BackHeader"))
            Drawer.closeDrawers();
        else
            Drawer.openDrawer(Gravity.START);
        if (_v.getTag().equals("Menu")){
            Drawer.openDrawer(Gravity.START);
        }
    }

    void PrintData(String Search, String Order) {
        try {
            Btn_Delete.setEnabled(false);
            Btn_Update.setEnabled(false);
            Lnr_ListProduct.removeAllViews();
            _ClassDatabase.CreateTable(Ctx);
            ArrayList<ContentValues> _ArrayList = _ClassDatabase.Select(Ctx, Search, 1, Order);
            for (ContentValues _ContentValues : _ArrayList) {
                View _v = View.inflate(this, R.layout.row_product, null);
                TextView Text_Price =_v.findViewById(R.id.Text_Price);
                TextView Text_NameUser =_v.findViewById(R.id.Text_NameUser);
                Text_Price.setText(_ContentValues.getAsString("Price"));
                Text_NameUser.setText(_ContentValues.getAsString("Name"));
                _v.setTag(_ContentValues);
                _v.setOnClickListener(rowClick);
                Lnr_ListProduct.addView(_v);

            }

        } catch (Exception Error) {
            _Globalclass.DisPlayToast(Act_Products.this, Error.getMessage());
        }

    }



    public void Order(View _v) {
        String _Order = null;
        if (_v.getTag().equals("ID"))
            _Order = " ORDER BY Id_Auto ASC ";
        else if (_v.getTag().equals("Price"))
            _Order = " ORDER BY Priceproduct DESC ";
        else if (_v.getTag().equals("Name"))
            _Order = " ORDER BY Nameproduct ASC ";
        PrintData(null, _Order);
    }

    public void Sum_Count(View _v) {
        String SumOrCount = null;
        if (_v.getTag().equals("Count")) {
            SumOrCount = " COUNT(*) ";
            int Result = _ClassDatabase.Sum_Count(Ctx, null, SumOrCount);
            Text_Sum.setText("Count:");
            Text_Sum.append(String.valueOf(Result));
            Text_Sum.append("\n");
            Text_Sum.append("Sum:0");
        } else if (_v.getTag().equals("Sum")) {
            SumOrCount = " SUM(Priceproduct) ";
            int Result = _ClassDatabase.Sum_Count(Ctx, null, SumOrCount);
            Text_Sum.setText("Count:0");
            Text_Sum.append("\n");
            Text_Sum.append("Sum:");
            Text_Sum.append(String.valueOf(Result));
        }
    }

    void SumOrCount() {
        String SumOrCount = null;
        SumOrCount = " COUNT(*) ";
        int Result = _ClassDatabase.Sum_Count(Ctx, null, SumOrCount);
        Text_Sum.setText("Count:");
        Text_Sum.append(String.valueOf(Result));
        Text_Sum.append("\n");
        SumOrCount = " SUM(Priceproduct) ";
        int Result1 = _ClassDatabase.Sum_Count(Ctx, null, SumOrCount);
        Text_Sum.append("Sum:");
        Text_Sum.append(String.valueOf(Result1));

    }private boolean ValidateName(){
        String _Name=Edt_ProName.getText().toString().trim();
        if (_Name.isEmpty()){
            Edt_ProName.setError("Product Name is empty");
            return false;}
        else {
            Edt_ProName.setError(null);
            return true;
        }
    }private boolean ValidatePrice(){
        String _Price=Edt_ProPrice.getText().toString().trim();
        if (_Price.isEmpty()){
            Edt_ProPrice.setError("Product Price is empty");
            return false;}
        else {
            Edt_ProPrice.setError(null);
            return true;
        }
    }

}


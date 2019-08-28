package com.example.doannv.duan_dat_ban;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.doannv.duan_dat_ban.unti.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {
    private RelativeLayout menu_progressbar;
    private TextInputLayout layoutUser;
    private TextInputEditText edtUser;
    private TextInputLayout layoutPass;
    private TextInputEditText edtPass;
    private Button btnDangNhap;
    private Button btnDangKy;
    private String username,password,email,hoten,image;
    private int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        AnhXa();
        CheckPass();
        EvenButton();
    }

    private void EvenButton() {
        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String taikhoan = edtUser.getText().toString().trim();
                final String matkhau = edtPass.getText().toString().trim();
                menu_progressbar.setVisibility(View.VISIBLE);
                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Server.duongdandangnhap, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        if (response != null) {

                            for (int i = 0; i < response.length(); i++) {
                                try {
                                    JSONObject jsonObject = response.getJSONObject(i);
                                    id = jsonObject.getInt("id");
                                    username = jsonObject.getString("taikhoan");
                                    password = jsonObject.getString("matkhau");
                                    email = jsonObject.getString("email");
                                    hoten = jsonObject.getString("hoten");
                                    image = jsonObject.getString("imageok");
                                    if (taikhoan.equals(username) && matkhau.equals(password)){
                                        if (taikhoan.equals("admin") && matkhau.equals("admin")){
                                            Intent intent = new Intent(LoginActivity.this,QuanLyNHActivity.class);
                                            startActivity(intent);
                                            menu_progressbar.setVisibility(View.GONE);
                                            return;
                                        }else {
                                            Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                                            intent.putExtra("ID",""+id);
                                            intent.putExtra("HOTEN",hoten);
                                            intent.putExtra("EMAIL",email);
                                            intent.putExtra("TAIKHOAN",username);
                                            intent.putExtra("MATKHAU",password);
                                            intent.putExtra("IMAGE",image);
                                            startActivity(intent);
                                            menu_progressbar.setVisibility(View.GONE);
                                            return;
                                        }
                                    }

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                        Toast.makeText(LoginActivity.this, "Tài khoản mật khẩu chưa chính xác", Toast.LENGTH_SHORT).show();
                        menu_progressbar.setVisibility(View.GONE);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
                requestQueue.add(jsonArrayRequest);
            }
        });
        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,DangKyActivity.class);
                startActivity(intent);
            }
        });
    }

    private void CheckPass() {
        edtPass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 0){
                    layoutPass.setPasswordVisibilityToggleEnabled(false);
                }else {
                    layoutPass.setPasswordVisibilityToggleEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void AnhXa() {
        layoutUser = (TextInputLayout) findViewById(R.id.layout_User);
        edtUser = (TextInputEditText) findViewById(R.id.edt_User);
        layoutPass = (TextInputLayout) findViewById(R.id.layout_pass);
        edtPass = (TextInputEditText) findViewById(R.id.edt_pass);
        btnDangNhap = (Button) findViewById(R.id.btn_Dang_Nhap);
        btnDangKy = (Button) findViewById(R.id.btn_Dang_Ky);
        menu_progressbar = findViewById(R.id.menu_progressbar);
    }
}
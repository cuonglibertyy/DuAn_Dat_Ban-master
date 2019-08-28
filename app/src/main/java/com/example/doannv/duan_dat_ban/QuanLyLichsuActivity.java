package com.example.doannv.duan_dat_ban;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.doannv.duan_dat_ban.adapter.LichSuAdapter;
import com.example.doannv.duan_dat_ban.model.LichSu;
import com.example.doannv.duan_dat_ban.model.OnClickHuyDB;
import com.example.doannv.duan_dat_ban.model.OnClickXoaLS;
import com.example.doannv.duan_dat_ban.unti.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class QuanLyLichsuActivity extends AppCompatActivity {
    private Toolbar toolbarLichSu;
    private RecyclerView recyLichSu;
    ArrayList<LichSu> arrayList;
    LinearLayoutManager manager;
    LichSuAdapter lichSuAdapter;
    private String IDNH;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quan_ly_lichsu);
        AnhXa();
        ActionToolbar();
        GetData();
        lichSuAdapter.setOnClickHuyDB(new OnClickHuyDB() {
            @Override
            public void OnClick(final LichSu lichSu) {
                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.duongdansuadatban, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        DatBan(lichSu.getIdBanAn());
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        HashMap<String,String> hashMap = new HashMap<>();
                        hashMap.put("idlichsu",""+lichSu.getID());
                        hashMap.put("trangthai",""+3);
                        return hashMap;
                    }
                };
                requestQueue.add(stringRequest);
            }
        });
        lichSuAdapter.setOnClickXoaLS(new OnClickXoaLS() {
            @Override
            public void onClick(final LichSu lichSu) {
                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.duongdanxoalichsu, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        arrayList.clear();
                        GetData();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        HashMap<String,String> hashMap = new HashMap<>();
                        hashMap.put("idlichsu",""+lichSu.getID());
                        return hashMap;
                    }
                };
                requestQueue.add(stringRequest);
            }
        });

    }

    private void GetData() {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.duongdanquanLyLS, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                int ID;
                int IDNH;
                String NameNH;
                int IDTK;
                String imgNH;
                int TrangThai;
                int SoBan;
                int BuaAn;
                int idBanAn;
                if (response != null ) {
                    try {
                        JSONArray jsonArray = new JSONArray(response);
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            ID = jsonObject.getInt("id");
                            IDNH = jsonObject.getInt("idnhahang");
                            NameNH = jsonObject.getString("tennhahang");
                            IDTK = jsonObject.getInt("idtk");
                            imgNH = jsonObject.getString("imgnhahang");
                            TrangThai = jsonObject.getInt("trangthai");
                            SoBan = jsonObject.getInt("soban");
                            BuaAn = jsonObject.getInt("buaan");
                            idBanAn = jsonObject.getInt("idbanan");
                            arrayList.add(new LichSu(ID,IDNH,NameNH,IDTK,imgNH,TrangThai,SoBan,BuaAn,idBanAn));
                            lichSuAdapter.notifyDataSetChanged();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> hashMap = new HashMap<>();
                IDNH = Main2Activity.IDNH;
                hashMap.put("idnhahang",IDNH);
                return hashMap;
            }
        };
        requestQueue.add(stringRequest);
    }

    private void AnhXa() {
        toolbarLichSu = (Toolbar) findViewById(R.id.toolbarQLLichSu);
        recyLichSu = (RecyclerView) findViewById(R.id.recyQLLichSu);
        arrayList = new ArrayList<>();
        manager = new LinearLayoutManager(QuanLyLichsuActivity.this,LinearLayoutManager.VERTICAL,false);
        lichSuAdapter = new LichSuAdapter(arrayList, QuanLyLichsuActivity.this);
        recyLichSu.setHasFixedSize(true);
        recyLichSu.setLayoutManager(manager);
        recyLichSu.setAdapter(lichSuAdapter);
    }
    private void ActionToolbar() {
        setSupportActionBar(toolbarLichSu);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarLichSu.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private void DatBan(final int IDBanAn){
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.duongdandatban, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                arrayList.clear();
                GetData();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> hashMap = new HashMap<>();
                hashMap.put("idbanan",""+IDBanAn);
                hashMap.put("trangthai",""+1);
                return hashMap;
            }
        };
        requestQueue.add(stringRequest);
    }
}

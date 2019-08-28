package com.example.doannv.duan_dat_ban.model;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


import com.example.doannv.duan_dat_ban.DatBanActivity;
import com.example.doannv.duan_dat_ban.fragment.BuaToiFragemnt;
import com.example.doannv.duan_dat_ban.fragment.BuatruaFragment;

public class ViewPage extends FragmentPagerAdapter {
    String IDNH,IMGNH;
    Bundle bundle;

    public ViewPage(FragmentManager fm) {
        super(fm);

    }

    @Override
    public Fragment getItem(int i) {
        IDNH = DatBanActivity.IDNH;
        IMGNH = DatBanActivity.IMGNH;
        switch (i){
            case 0:
                BuatruaFragment buatruaFragment = new BuatruaFragment();
                bundle = new Bundle();
                bundle.putString("IDNH",IDNH);
                bundle.putString("IMGNH",IMGNH);
                buatruaFragment.setArguments(bundle);
                return buatruaFragment;
            case 1:
                BuaToiFragemnt buaToiFragemnt = new BuaToiFragemnt();
                bundle = new Bundle();
                bundle.putString("IDNH",IDNH);
                bundle.putString("IMGNH",IMGNH);
                buaToiFragemnt.setArguments(bundle);
                return buaToiFragemnt;

                default:
                    return null;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }
}

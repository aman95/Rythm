package com.code_breakers.rythm.adapters;

import com.code_breakers.rythm.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aman on 12/5/15.
 */
public class DrawerItems {
    String drawerItemName;
    int drawerListIconId;

    public static List<DrawerItems> getItem() {
        List<DrawerItems> data = new ArrayList<>();
        int[] iconId = {R.drawable.ic_person,R.drawable.ic_user_icon,R.drawable.ic_logout,R.drawable.ic_createc};
        String[] itemName = {"Circles","Updates","Events","Settings"};
        for(int i=0;i<itemName.length && i<iconId.length;i++){
            DrawerItems current = new DrawerItems();
            current.drawerItemName = itemName[i];
            current.drawerListIconId = iconId[i];
            data.add(current);
        }
        return data;
    }
}

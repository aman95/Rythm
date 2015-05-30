package com.code_breakers.rythm.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.code_breakers.rythm.R;

/**
 * Created by aman on 11/5/15.
 */
public class DrawerViewHolder extends RecyclerView.ViewHolder {

    ImageView drawerListIcon;
    TextView drawerListName;
    RelativeLayout drawerListContainer;

    public DrawerViewHolder(View itemView) {
        super(itemView);
        drawerListIcon = (ImageView) itemView.findViewById(R.id.drawer_list_item_icon);
        drawerListName = (TextView) itemView.findViewById(R.id.drawer_list_item_name);
        drawerListContainer = (RelativeLayout) itemView.findViewById(R.id.container);
    }
}

package com.code_breakers.rythm.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.code_breakers.rythm.R;

import java.util.Collections;
import java.util.List;

/**
 * Created by aman on 11/5/15.
 */
public class DrawerAdapter extends RecyclerView.Adapter<DrawerViewHolder> {

    LayoutInflater inflater;
    List<DrawerItems> data = Collections.emptyList();

    public DrawerAdapter(Context context, List<DrawerItems> data){
        inflater = LayoutInflater.from(context);
        this.data = data;
    }

    @Override
    public DrawerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.drawer_list_items, parent, false);
        DrawerViewHolder holder = new DrawerViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(DrawerViewHolder holder, int position) {
        DrawerItems currentItem = data.get(position);
        holder.drawerListName.setText(currentItem.drawerItemName);
        holder.drawerListIcon.setImageResource(currentItem.drawerListIconId);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}

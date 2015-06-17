package com.code_breakers.rythm.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.code_breakers.rythm.Dashboard;
import com.code_breakers.rythm.R;

import java.util.Collections;
import java.util.List;

/**
 * Created by aman on 11/5/15.
 */
public class DrawerAdapter extends RecyclerView.Adapter<DrawerViewHolder> {

    LayoutInflater inflater;
    List<DrawerItems> data = Collections.emptyList();
    Context context;

    public DrawerAdapter(Context context, List<DrawerItems> data){
        inflater = LayoutInflater.from(context);
        this.data = data;
        this.context = context;
    }

    @Override
    public DrawerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.drawer_list_items, parent, false);
        DrawerViewHolder holder = new DrawerViewHolder(view);
        Log.d("ADAPTER" ,"onCreateViewHolder called --- viewType = "+viewType);
        return holder;
    }

    @Override
    public void onBindViewHolder(DrawerViewHolder holder, final int position) {
        DrawerItems currentItem = data.get(position);
        holder.drawerListName.setText(currentItem.drawerItemName);
        holder.drawerListIcon.setImageResource(currentItem.drawerListIconId);
        Log.d("ADAPTER", "onBindViewHolder called --- position = " + position);

//        holder.drawerListContainer.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(context,"Position = "+position,Toast.LENGTH_SHORT).show();
//
//            }
//        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}

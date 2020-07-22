package com.elnaranjo.pibi2goserver.ViewHolder;


import android.view.ContextMenu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.elnaranjo.pibi2goserver.Common.Common;
import com.elnaranjo.pibi2goserver.Interface.ItemClickListener;
import com.elnaranjo.pibi2goserver.R;


public class MenuViewHolder extends RecyclerView.ViewHolder implements
        View.OnClickListener,
        View.OnCreateContextMenuListener
{
    public TextView txtMenuName;
    public ImageView imageView;
    private ItemClickListener itemClickListener;

    public MenuViewHolder(@NonNull View itemView) {
        super(itemView);

        txtMenuName = (TextView)itemView.findViewById(R.id.menu_name);
        imageView = (ImageView)itemView.findViewById(R.id.menu_image);
        itemView.setOnCreateContextMenuListener(this);
        itemView.setOnClickListener(this);


    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View view) {
        itemClickListener.onClick(
                view,getAdapterPosition(),false);
    }

    @Override
    public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
        contextMenu.setHeaderTitle("Seleccione la opción");

        contextMenu.add(0,0,getAdapterPosition(), Common.UPDATE);
        contextMenu.add(0,1,getAdapterPosition(), Common.DELETE);


    }
}

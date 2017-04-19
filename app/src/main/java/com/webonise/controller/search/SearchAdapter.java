package com.webonise.controller.search;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import com.webonise.controller.R;

import java.util.List;

class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {
    private Context context;
    private List<UserModel.DataEntity.UsersEntity> list;

    public SearchAdapter(Context context, List<UserModel.DataEntity.UsersEntity> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.name.setText(list.get(position).getFirstName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final CheckBox name;

        public ViewHolder(View itemView) {
            super(itemView);
            name = (CheckBox) itemView.findViewById(R.id.name);
        }
    }
}

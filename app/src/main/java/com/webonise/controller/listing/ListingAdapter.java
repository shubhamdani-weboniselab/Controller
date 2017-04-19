package com.webonise.controller.listing;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import com.webonise.controller.R;

import java.util.List;

class ListingAdapter extends RecyclerView.Adapter<ListingAdapter.Viewholder> {

    private Context context;
    private final List<LisitngModel.DataEntity.ListEntity> data;
    private final ListingPresenter presenter;

    public ListingAdapter(Context context, List<LisitngModel.DataEntity.ListEntity> data, ListingPresenter presenter) {
        this.context = context;
        this.data = data;
        this.presenter = presenter;
    }

    @Override
    public ListingAdapter.Viewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new Viewholder(LayoutInflater.from(context).inflate(R.layout.listing_item, parent, false));
    }

    @Override
    public void onBindViewHolder(final ListingAdapter.Viewholder holder, final int position) {
        final LisitngModel.DataEntity.ListEntity createModel = data.get(position);
        holder.chkTitle.setText(createModel.getFields().getName());
        holder.chkTitle.setChecked(createModel.getFields().isChecked());
        holder.chkTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onItemClick(createModel, position, holder.chkTitle.isChecked());
            }
        });

        holder.chkTitle.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                presenter.onLongClick(createModel.getGuid(), position);
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return data == null || data.isEmpty() ? 0 : data.size();
    }

    public void removeItem(int position) {
        data.remove(position);
    }

    public void setItemChecked(int position) {
        data.get(position).getFields().setChecked(!data.get(position).getFields().isChecked());
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        CheckBox chkTitle;

        public Viewholder(View itemView) {
            super(itemView);
            chkTitle = (CheckBox) itemView.findViewById(R.id.chkTitle);
        }
    }
}

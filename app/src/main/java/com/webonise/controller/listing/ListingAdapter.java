package com.webonise.controller.listing;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.webonise.controller.R;
import com.webonise.controller.create.CreateModel;

import io.realm.RealmResults;

class ListingAdapter extends RecyclerView.Adapter<ListingAdapter.Viewholder> {


    private Context context;
    private final RealmResults<CreateModel> data;
    private final ListingPresenter presenter;

    public ListingAdapter(Context context, RealmResults<CreateModel> data, ListingPresenter presenter) {
        this.context = context;
        this.data = data;
        this.presenter = presenter;
    }

    @Override
    public ListingAdapter.Viewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new Viewholder(LayoutInflater.from(context).inflate(R.layout.listing_item, parent, false));
    }

    @Override
    public void onBindViewHolder(ListingAdapter.Viewholder holder, final int position) {
        final CreateModel createModel = data.get(position);
        holder.title.setText(createModel.getTitle());
        holder.title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onItemClick(createModel, position);
            }
        });

        holder.title.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                presenter.onLongClick(createModel, position);
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return data == null || data.isEmpty() ? 0 : data.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        TextView title;

        public Viewholder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
        }
    }
}

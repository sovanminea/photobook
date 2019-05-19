package me.sovanminea.photobook.ui.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import me.sovanminea.photobook.R;
import me.sovanminea.photobook.listener.LoadImageListener;
import me.sovanminea.photobook.listener.OnItemClickListener;
import me.sovanminea.photobook.model.PhotoModel;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

public class PhotoListAdapter extends BaseLoadMoreAdapter<PhotoModel, PhotoListAdapter.PhotoListViewHolder> {

    private OnItemClickListener mOnItemClickListener;
    private Context mContext;
    private RequestOptions requestOptions = new RequestOptions();


    @SuppressLint("CheckResult")
    public PhotoListAdapter(Context context, List<PhotoModel> dataItems, RecyclerView recyclerView, LoadImageListener listener, OnItemClickListener onItemClickListener) {
        super(dataItems, recyclerView, listener);
        mOnItemClickListener = onItemClickListener;
        mContext = context;
        requestOptions.placeholder(R.color.colorAccent);
        requestOptions.diskCacheStrategy(DiskCacheStrategy.RESOURCE);
    }

    @Override
    public RecyclerView.ViewHolder setViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.photo_item, parent, false);
        return new PhotoListViewHolder(view);
    }

    @Override
    public void onBindData(PhotoListViewHolder holder, PhotoModel data, int position) {
        Glide.with(mContext)
                .load(data.getDownloadUrl())
                .transition(withCrossFade())
                .apply(requestOptions)
                .into(holder.imageView);
    }

    public class PhotoListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView imageView;

        PhotoListViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mOnItemClickListener != null)
                mOnItemClickListener.onItemClick(view, getAdapterPosition());
        }
    }
}

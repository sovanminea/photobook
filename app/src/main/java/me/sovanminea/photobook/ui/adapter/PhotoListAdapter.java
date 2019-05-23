package me.sovanminea.photobook.ui.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.GridLayoutManager;
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
    private GridLayoutManager mManager;

    @SuppressLint("CheckResult")
    public PhotoListAdapter(Context context, List<PhotoModel> dataItems, RecyclerView recyclerView, LoadImageListener listener, OnItemClickListener onItemClickListener) {
        super(dataItems, recyclerView, listener);
        mOnItemClickListener = onItemClickListener;
        mContext = context;
        requestOptions.placeholder(R.color.image_placeholder);
        requestOptions.diskCacheStrategy(DiskCacheStrategy.RESOURCE);
        requestOptions.skipMemoryCache(true);
        this.mManager = (GridLayoutManager) recyclerView.getLayoutManager();

        mManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return getItemViewType(position) == VIEW_PROG ? mManager.getSpanCount() : 1;
            }
        });
    }

    @Override
    public RecyclerView.ViewHolder setViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.photo_item, parent, false);
        return new PhotoListViewHolder(view);
    }

    @Override
    public void onBindData(final PhotoListViewHolder holder, PhotoModel data, int position) {
        ViewCompat.setTransitionName(holder.imageView, String.valueOf(position) + "_image");
        Glide.with(mContext)
                .load(data.getDownloadUrl())
                .thumbnail(/*sizeMultiplier=*/ 0.10f)
                .transition(withCrossFade())
                .apply(requestOptions)
                .into(holder.imageView);
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mOnItemClickListener != null)
                    mOnItemClickListener.onItemClick(holder, holder.getAdapterPosition());
            }
        });
    }

    public class PhotoListViewHolder extends RecyclerView.ViewHolder {

        public ImageView imageView;

        PhotoListViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                imageView.setClipToOutline(true);
            }
        }

    }
}

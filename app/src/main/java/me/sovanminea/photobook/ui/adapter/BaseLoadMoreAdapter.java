package me.sovanminea.photobook.ui.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import java.util.List;

import me.sovanminea.photobook.Config;
import me.sovanminea.photobook.R;
import me.sovanminea.photobook.listener.LoadImageListener;
import me.sovanminea.photobook.util.NetworkUtil;

public abstract class BaseLoadMoreAdapter<T, VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int VIEW_ITEM = 1;
    protected static final int VIEW_PROG = 0;
    protected List<T> dataItems;
    private LoadImageListener mLoadImageListener;
    private RecyclerView mRecyclerView;
    private int totalItemCount;
    private int lastVisibleItem;
    private boolean loading = true;
    private boolean isAll = false;
    private int previousItemCount;

    public static class ProgressViewHolder extends RecyclerView.ViewHolder {
        ProgressBar mProgressBar;

        public ProgressViewHolder(View itemView) {
            super(itemView);
            mProgressBar = itemView.findViewById(R.id.progressbar);
        }
    }

    public abstract RecyclerView.ViewHolder setViewHolder(ViewGroup parent);

    public abstract void onBindData(VH holder, T data, int position);

    public BaseLoadMoreAdapter(List<T> dataItems, RecyclerView recyclerView, LoadImageListener listener) {
        this.dataItems = dataItems;
        mLoadImageListener = listener;
        mRecyclerView = recyclerView;
        if (this.getItemCount() == 0) {
            mLoadImageListener.onLoadFirst();
        }
        if (mRecyclerView.getLayoutManager() instanceof GridLayoutManager) {
//            reset();
            final GridLayoutManager layoutManager = (GridLayoutManager) recyclerView.getLayoutManager();
            mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);
                    if (dy > 0) {
                        int itemCount = layoutManager.getItemCount();

//                        if (itemCount != previousItemCount) {
//                            loading = false;
//                        }

                        if (!loading && layoutManager.findLastVisibleItemPosition() >= itemCount - 1) {
                            previousItemCount = itemCount;
                            mLoadImageListener.onLoadMore();
                            loading = true;
                        }
                    }
                }
            });
        }
    }

    @Override
    public int getItemViewType(int position) {
        return dataItems.get(position) != null ? VIEW_ITEM : VIEW_PROG;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == VIEW_ITEM) {
            return setViewHolder(parent);
        } else if (viewType == VIEW_PROG) {
            return new ProgressViewHolder(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.load_more_progressbar, parent, false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ProgressViewHolder) {
            ((ProgressViewHolder) holder).mProgressBar.setVisibility(isAll ? View.GONE : View.VISIBLE);
        } else {
            VH viewHolder = (VH) holder;
            onBindData(viewHolder, dataItems.get(position), position);
        }
    }

    @Override
    public int getItemCount() {
        return dataItems == null ? 0 : dataItems.size();
    }

    public void addItems(final List<T> dataItem) {
        removeBottomPb();
        isAll = dataItem.size() < Config.QUERY_LIMIT;
        loading = false;
        dataItems.addAll(dataItem);
        notifyItemInserted(getItemCount());
        if (isAll) enableLoadingBottom();
    }

    public void removeBottomPb() {
        if (getItemCount() != 0) {
            dataItems.remove(dataItems.size() - 1);
            notifyItemRemoved(dataItems.size());
            loading = false;
        }
    }

    public void enableLoadingBottom() {
        if (getItemCount() != 0 && !loading) {
            dataItems.add(null);
            mRecyclerView.post(new Runnable() {
                @Override
                public void run() {
                    notifyItemInserted(getItemCount());
                }
            });
        }
    }

    public void reset() {
        this.loading = false;
        this.previousItemCount = -1;
    }

    public void clear() {
        isAll = false;
        dataItems.clear();
        notifyDataSetChanged();
    }

    public boolean isAll() {
        return isAll;
    }

    public boolean isLoading() {
        return loading;
    }

    public void setLoading(boolean loading) {
        this.loading = loading;
    }

    public T getItem(int position) {
        return dataItems.get(position);
    }
}

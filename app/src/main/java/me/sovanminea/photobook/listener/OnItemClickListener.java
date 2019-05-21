package me.sovanminea.photobook.listener;

import me.sovanminea.photobook.ui.adapter.PhotoListAdapter;

public interface OnItemClickListener {
    void onItemClick(PhotoListAdapter.PhotoListViewHolder view, int position);
}

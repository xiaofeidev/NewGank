package com.github.xiaofeidev.main.ui.adapter.rv

import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.module.LoadMoreModule
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.github.xiaofeidev.base.singleton.requestOptions
import com.github.xiaofeidev.comm.model.gank.GankBean
import com.github.xiaofeidev.main.R

/**
 * @author xiaofei_dev
 * @date 2020/8/18
 */
class GankAdapter: BaseQuickAdapter<GankBean, BaseViewHolder>(R.layout.item_gank), LoadMoreModule {
    override fun convert(holder: BaseViewHolder, item: GankBean) {
        holder.setText(R.id.textTitle, item.title)
            .setText(R.id.textDesc, item.desc)
            .setText(R.id.textAuthor, item.author)
            .setText(R.id.textDate, if (item.createdAt.contains(' ')){
                item.createdAt.substringBefore(' ')
            } else {
                ""
            })
        if (item.images.isNotEmpty()){
            Glide
                .with(context)
                .asBitmap()
                .load(item.images[0])
                .apply(requestOptions)
                .into(holder.getView(R.id.imgImg))
        }
    }
}
package com.github.xiaofeidev.main.ui.adapter.rv

import com.blankj.utilcode.util.ImageUtils
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.module.LoadMoreModule
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.github.xiaofeidev.base.singleton.requestOptions
import com.github.xiaofeidev.comm.model.gank.GankBean
import com.github.xiaofeidev.main.R

/**
 * @author xiaofei_dev
 * @date 2020/8/23
 */
class GirlAdapter(val height: Int): BaseQuickAdapter<GankBean, BaseViewHolder>(R.layout.item_girl), LoadMoreModule {
    override fun convert(holder: BaseViewHolder, item: GankBean) {
        holder.itemView.layoutParams.height = height
        Glide
            .with(context)
            .asBitmap()
            .load(item.url)
            .apply(requestOptions)
            .into(holder.getView(R.id.imgImg))
    }
}
package com.xinly.dendrobe.widget

import android.content.Context
import android.widget.ImageView
import com.xinly.core.utils.GlideUtils
import com.youth.banner.loader.ImageLoader

/*
    Banner图片加载器
 */
class BannerImageLoader : ImageLoader() {
    override fun displayImage(context: Context, path: Any, imageView: ImageView) {
        //使用Glide框架加载图片
        GlideUtils.loadImage(context, path.toString(), imageView)
    }
}

package com.xinly.dendrobe.module.user

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.databinding.Observable
import com.matisse.Matisse
import com.matisse.MimeType
import com.matisse.entity.CaptureStrategy
import com.matisse.entity.ConstValue
import com.matisse.filter.Filter
import com.matisse.utils.Platform
import com.matisse.widget.CropImageView
import com.tbruyelle.rxpermissions2.RxPermissions
import com.xinly.core.ui.activity.BaseActivity
import com.xinly.dendrobe.BR
import com.xinly.dendrobe.R
import com.xinly.dendrobe.databinding.UserInfoBinding
import com.xinly.dendrobe.widget.GifSizeFilter
import com.xinly.dendrobe.widget.GlideEngine

/**
 * 个人资料
 * <p>
 * Create by zm on 2019/7/4.
 */
class UserInfoEditActivity : BaseActivity<UserInfoBinding, UserInfoEditViewModel>() {
    override fun initContentViewId(savedInstanceState: Bundle?): Int {
        return R.layout.activity_basic_info_edit
    }

    override fun initVariableId(): Int {
        return BR.viewModel
    }

    override fun initViewObservable() {
        //图片选择
        viewModel?.requestSelPicture?.addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback() {
            @SuppressLint("CheckResult")
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                RxPermissions(this@UserInfoEditActivity)
                    .request(Manifest.permission.CAMERA)
                    .subscribe {
                        if (it) {
                            Matisse.from(this@UserInfoEditActivity)
                                .choose(setOf(MimeType.JPEG, MimeType.PNG)) //选择图片类型
                                .capture(true) //可拍照
                                .countable(false) //记录文件选择顺序
                                .captureStrategy(CaptureStrategy(true, "${Platform.getPackageName(this@UserInfoEditActivity)}.fileprovider"))
                                .maxSelectable(1) //最多选择一张
                                .isCrop(true) //开启裁剪
                                .cropOutPutX(400) //设置裁剪后保存图片的宽高
                                .cropOutPutY(400) //设置裁剪后保存图片的宽高
                                .cropStyle(CropImageView.Style.RECTANGLE) //方形裁剪CIRCLE为圆形裁剪
                                .setStatusIsDark(false)
                                .theme(R.style.Matisse_Def)
                                .isCropSaveRectangle(true) //裁剪后保存方形（只对圆形裁剪有效）
                                .addFilter(GifSizeFilter(320, 320, 5 * Filter.K * Filter.K)) //筛选数据源可选大小限制
                                .gridExpectedSize(resources.getDimensionPixelSize(R.dimen.grid_expected_size))
                                .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
                                .thumbnailScale(0.8f)
                                .setStatusIsDark(true) // 设置状态栏文字颜色 需依赖ImmersionBar库
                                .imageEngine(GlideEngine()) // 加载库
                                .forResult(ConstValue.REQUEST_CODE_CHOOSE)
                        }
                    }

            }
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (data == null) return

        if (requestCode == ConstValue.REQUEST_CODE_CHOOSE) {
            val strList = Matisse.obtainPathResult(data)
            strList.let {
                viewModel?.uploadFile(it[0])
            }
        }
    }

}

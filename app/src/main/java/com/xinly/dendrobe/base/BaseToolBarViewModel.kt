package com.xinly.dendrobe.base

import android.app.Application
import com.xinly.core.binding.command.BindingAction
import com.xinly.core.binding.command.BindingCommand
import com.xinly.core.viewmodel.BaseViewModel
import com.xinly.dendrobe.model.vo.bean.ToolBarData

/**
 * Created by zm on 2019-06-30.
 */
abstract class BaseToolBarViewModel(application: Application) : BaseViewModel(application) {
    /**
     * TooBar数据
     */
    val toolBarData: ToolBarData = ToolBarData()
    /**
     * toolbar back event
     */
    val backClick: BindingCommand<Nothing> = BindingCommand(object : BindingAction {
        override fun call() {
            handLeftClick()
        }

    })

    open fun handLeftClick() {
        finish()
    }
    /**
     * toolbar text event
     */
    val rightTextClick: BindingCommand<Nothing> = BindingCommand(object : BindingAction {
        override fun call() {
            handRightText()
        }

    })
    open fun handRightText() {

    }

    /**
     * toolbar text event
     */
    val rightImgClick: BindingCommand<Nothing> = BindingCommand(object : BindingAction {
        override fun call() {
            handRightImg()
        }

    })
    open fun handRightImg() {

    }
}
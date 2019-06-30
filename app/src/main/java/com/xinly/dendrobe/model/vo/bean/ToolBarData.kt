package com.xinly.dendrobe.model.vo.bean

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.xinly.dendrobe.BR

/**
 * Created by zm on 2019-06-30.
 */
 class ToolBarData: BaseObservable(){
    @Bindable
    var titleText: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.titleText)
        }
    @Bindable
    var rightText: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.rightText)
        }
    @Bindable
    var rightImg: Int = -1
        set(value) {
            field = value
            notifyPropertyChanged(BR.rightImg)
        }
}
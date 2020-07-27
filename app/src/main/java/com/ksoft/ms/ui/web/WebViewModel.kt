package com.ksoft.ms.ui.web

import com.ksoft.ms.ui.base.BasePresenter
import com.ksoft.ms.ui.base.BaseViewModel
import javax.inject.Inject

class WebViewModel @Inject constructor() : BaseViewModel<BasePresenter>() {

    var url: String = ""

}
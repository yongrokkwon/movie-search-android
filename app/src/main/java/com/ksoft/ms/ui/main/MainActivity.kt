package com.ksoft.ms.ui.main

import com.ksoft.ms.R
import com.ksoft.ms.databinding.ActivityMainBinding
import com.ksoft.ms.ui.base.BaseActivity

class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>() {
    override val layoutRes = R.layout.activity_main
    override val viewModelClass = MainViewModel::class
}
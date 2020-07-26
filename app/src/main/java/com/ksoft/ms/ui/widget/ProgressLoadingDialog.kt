package com.ksoft.ms.ui.widget

import android.app.Dialog
import android.content.Context
import android.graphics.BlendModeColorFilter
import android.graphics.Color
import android.graphics.ColorFilter
import android.graphics.PorterDuff
import android.graphics.drawable.ColorDrawable
import android.view.Window
import android.view.WindowManager
import androidx.core.content.ContextCompat
import androidx.core.graphics.BlendModeColorFilterCompat
import androidx.core.graphics.BlendModeCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.ksoft.ms.R
import kotlinx.android.synthetic.main.dialog_progress_loading.*

class ProgressLoadingDialog(context: Context) : Dialog(context), LifecycleObserver {

    init {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setCancelable(true)

        setContentView(R.layout.dialog_progress_loading)
        window?.let {
            it.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            it.attributes = WindowManager.LayoutParams().apply {
                copyFrom(window?.attributes)
                width = WindowManager.LayoutParams.MATCH_PARENT
                height = WindowManager.LayoutParams.MATCH_PARENT
            }
        }
        progressBar.indeterminateDrawable.colorFilter =
            BlendModeColorFilterCompat.createBlendModeColorFilterCompat(
                ContextCompat.getColor(context, android.R.color.holo_red_dark),
                BlendModeCompat.SRC_ATOP
            )
    }

    fun safeShow() {
        if (!isShowing) {
            show()
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun close() {
        cancel()
    }
}
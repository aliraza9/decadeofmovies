package com.ali.decadeofmovies.ui.widgets.textviews

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import com.google.android.material.textview.MaterialTextView

class RegularTextView : MaterialTextView {
    constructor(context: Context) : super(context) { init() }
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) { init() }
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) { init() }

    private fun init() {
        val face = Typeface.createFromAsset(context.assets, "fonts/karla_regular.ttf")
        typeface = face
        invalidate()
    }

}
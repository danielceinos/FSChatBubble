package com.fireshield.fschatbubble

import android.content.res.Resources
import android.graphics.Color
import android.graphics.drawable.GradientDrawable

/**
 * Created by Daniel S on 11/02/2018.
 */
class FSBubbleBackground(bgColor: Int, bottomLeftRadius: Float, bottomRightRadius: Float, topLeftRadius: Float, topRightRadius: Float) {

  var shape: GradientDrawable
  var cornerRadius: FloatArray = FloatArray(8)
    set(value) {
      field = value
      shape.cornerRadii = value
    }
  var bgColor: Int = Color.RED
    set(value) {
      field = value
      shape.setColor(value)
    }

  init {
    shape = GradientDrawable()
    shape.shape = GradientDrawable.RECTANGLE
    setCornersRadius(bottomLeftRadius, bottomRightRadius, topLeftRadius, topRightRadius)
    shape.cornerRadii = cornerRadius
    shape.setColor(bgColor)
  }

  fun setCornersRadius(bottomLeftRadius: Float?, bottomRightRadius: Float?, topLeftRadius: Float?, topRightRadius: Float?) {
    bottomLeftRadius?.let {
      cornerRadius[0] = it
      cornerRadius[1] = it
    }
    bottomRightRadius?.let {
      cornerRadius[2] = it
      cornerRadius[3] = it
    }
    topLeftRadius?.let {
      cornerRadius[4] = it
      cornerRadius[5] = it
    }
    topRightRadius?.let {
      cornerRadius[6] = it
      cornerRadius[7] = it
    }
    shape.cornerRadii = cornerRadius
  }

  companion object {
    private val radiusBig = dpToPx(13)
    private val radiusSmall = dpToPx(4)

    val RightTop: FSBubbleBackground = FSBubbleBackground(Color.BLUE, radiusBig, radiusSmall, radiusBig, radiusSmall)
    val RightMiddle: FSBubbleBackground = FSBubbleBackground(Color.BLUE, radiusBig, radiusSmall, radiusSmall, radiusBig)
    val RightBottom: FSBubbleBackground = FSBubbleBackground(Color.BLUE, radiusBig, 0F, radiusSmall, radiusBig)

    val LeftTop: FSBubbleBackground = FSBubbleBackground(Color.BLUE, radiusSmall, radiusBig, radiusBig, radiusSmall)
    val LeftMiddle: FSBubbleBackground = FSBubbleBackground(Color.BLUE, radiusSmall, radiusBig, radiusBig, radiusSmall)
    val LeftBottom: FSBubbleBackground = FSBubbleBackground(Color.BLUE, 0F, radiusBig, radiusBig, radiusSmall)

    private fun dpToPx(dp: Int): Float {
      return (dp * Resources.getSystem().displayMetrics.density)
    }
  }
}
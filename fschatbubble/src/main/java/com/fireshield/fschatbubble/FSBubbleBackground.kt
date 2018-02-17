package com.fireshield.fschatbubble

import android.content.res.Resources
import android.graphics.Color
import android.graphics.drawable.GradientDrawable

/**
 * Created by Daniel S on 11/02/2018.
 */
class FSBubbleBackground(bgColor: Int, topLeftRadius: Float, topRightRadius: Float, bottomRightRadius: Float, bottomLeftRadius: Float) {

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
    setCornersRadius(topLeftRadius, topRightRadius, bottomRightRadius, bottomLeftRadius)
    shape.cornerRadii = cornerRadius
    shape.setColor(bgColor)
  }

  //ordered top-left, top-right, bottom-right, bottom-left
  fun setCornersRadius(topLeftRadius: Float?, topRightRadius: Float?, bottomRightRadius: Float?, bottomLeftRadius: Float?) {
    topLeftRadius?.let {
      cornerRadius[0] = it
      cornerRadius[1] = it
    }
    topRightRadius?.let {
      cornerRadius[2] = it
      cornerRadius[3] = it
    }
    bottomRightRadius?.let {
      cornerRadius[4] = it
      cornerRadius[5] = it
    }
    bottomLeftRadius?.let {
      cornerRadius[6] = it
      cornerRadius[7] = it
    }
    shape.cornerRadii = cornerRadius
  }

  companion object {
    private val radiusBig = dpToPx(13)
    private val radiusSmall = dpToPx(4)

    val RightTop: FSBubbleBackground
      get() {
        return FSBubbleBackground(Color.BLUE, radiusBig, radiusBig, radiusSmall, radiusBig)
      }
    val RightMiddle: FSBubbleBackground
      get() {
        return FSBubbleBackground(Color.BLUE, radiusBig, radiusSmall, radiusSmall, radiusBig)
      }
    val RightBottom: FSBubbleBackground
      get() {
        return FSBubbleBackground(Color.BLUE, radiusBig, radiusSmall, 0F, radiusBig)
      }

    val LeftTop: FSBubbleBackground
      get() {
        return FSBubbleBackground(Color.BLUE, radiusBig, radiusBig, radiusBig, radiusSmall)
      }
    val LeftMiddle: FSBubbleBackground
      get() {
        return FSBubbleBackground(Color.BLUE, radiusSmall, radiusBig, radiusBig, radiusSmall)
      }
    val LeftBottom: FSBubbleBackground
      get() {
        return FSBubbleBackground(Color.BLUE, radiusSmall, radiusBig, radiusBig, 0F)
      }

    fun getDefaultAt(indx: Int): FSBubbleBackground {
      return when (indx) {
        0 -> RightTop
        1 -> RightMiddle
        2 -> RightBottom
        3 -> LeftTop
        4 -> LeftMiddle
        5 -> LeftBottom
        else -> {
          RightTop
        }
      }
    }

    private fun dpToPx(dp: Int): Float {
      return (dp * Resources.getSystem().displayMetrics.density)
    }
  }
}
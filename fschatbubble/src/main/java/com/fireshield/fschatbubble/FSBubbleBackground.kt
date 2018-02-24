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
    private val radiusBig = dpToPx(30)
    private val radiusSmall = dpToPx(4)

    fun setCornersForPosition(position: Position, radiusBig: Float, radiusSmall: Float): FSBubbleBackground {
      return when (position) {
        Position.RightSingle -> FSBubbleBackground(Color.BLUE, radiusBig, radiusBig, 0f, radiusBig)
        Position.RightTop -> FSBubbleBackground(Color.BLUE, radiusBig, radiusBig, radiusSmall, radiusBig)
        Position.RightMiddle -> FSBubbleBackground(Color.BLUE, radiusBig, radiusSmall, radiusSmall, radiusBig)
        Position.RightBottom -> FSBubbleBackground(Color.BLUE, radiusBig, radiusSmall, 0F, radiusBig)
        Position.LeftSingle -> FSBubbleBackground(Color.BLUE, radiusBig, radiusBig, radiusBig, 0f)
        Position.LeftTop -> FSBubbleBackground(Color.BLUE, radiusBig, radiusBig, radiusBig, radiusSmall)
        Position.LeftMiddle -> FSBubbleBackground(Color.BLUE, radiusSmall, radiusBig, radiusBig, radiusSmall)
        Position.LeftBottom -> FSBubbleBackground(Color.BLUE, radiusSmall, radiusBig, radiusBig, 0F)
      }
    }

     fun intToPosition(index: Int): Position {
      return when (index) {
        0 -> Position.RightSingle
        1 -> Position.RightTop
        2 -> Position.RightMiddle
        3 -> Position.RightBottom
        4 -> Position.LeftSingle
        5 -> Position.LeftTop
        6 -> Position.LeftMiddle
        7 -> Position.LeftBottom
        else -> {
          Position.NONE
        }
      }
    }

    private fun dpToPx(dp: Int): Float {
      return (dp * Resources.getSystem().displayMetrics.density)
    }
  }

  enum class Position {
    RightSingle,
    RightTop,
    RightMiddle,
    RightBottom,
    LeftSingle,
    LeftTop,
    LeftMiddle,
    LeftBottom,
    NONE
  }
}
package com.fireshield.fschatbubble

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

  fun setCornersForPosition(bubbleFSBubblePosition: FSBubblePosition, radiusBig: Float, radiusSmall: Float) {
    when (bubbleFSBubblePosition) {
      FSBubblePosition.RightSingle -> setCornersRadius(radiusBig, radiusBig, 0f, radiusBig)
      FSBubblePosition.RightTop -> setCornersRadius(radiusBig, radiusBig, radiusSmall, radiusBig)
      FSBubblePosition.RightMiddle -> setCornersRadius(radiusBig, radiusSmall, radiusSmall, radiusBig)
      FSBubblePosition.RightBottom -> setCornersRadius(radiusBig, radiusSmall, 0F, radiusBig)
      FSBubblePosition.LeftSingle -> setCornersRadius(radiusBig, radiusBig, radiusBig, 0f)
      FSBubblePosition.LeftTop -> setCornersRadius(radiusBig, radiusBig, radiusBig, radiusSmall)
      FSBubblePosition.LeftMiddle -> setCornersRadius(radiusSmall, radiusBig, radiusBig, radiusSmall)
      FSBubblePosition.LeftBottom -> setCornersRadius(radiusSmall, radiusBig, radiusBig, 0F)
      FSBubblePosition.NONE -> TODO()
    }
  }

  companion object {

    fun intToPosition(index: Int): FSBubblePosition {
      return when (index) {
        0 -> FSBubblePosition.RightSingle
        1 -> FSBubblePosition.RightTop
        2 -> FSBubblePosition.RightMiddle
        3 -> FSBubblePosition.RightBottom
        4 -> FSBubblePosition.LeftSingle
        5 -> FSBubblePosition.LeftTop
        6 -> FSBubblePosition.LeftMiddle
        7 -> FSBubblePosition.LeftBottom
        else -> {
          FSBubblePosition.NONE
        }
      }
    }
  }
}
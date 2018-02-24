package com.fireshield.fschatbubble

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView


/**
 * Created by Daniel S on 10/02/2018.
 */
class FSChatBubble(context: Context?, attrs: AttributeSet?) : FrameLayout(context, attrs) {

  var background: FSBubbleBackground? = null
    set(value) {
      field = value
      findViewById<View>(R.id.v_background).background = value?.shape
    }
  var text: String = ""
    @SuppressLint("WrongViewCast")
    set(value) {
      field = value
      findViewById<TextView>(R.id.tv_chat_content).text = value
      findViewById<TextView>(R.id.tv_chat_content).requestLayout()
    }
  var bubbleColor: Int = -1
    set(value) {
      field = value
      background?.bgColor = value
    }

  val bubblePosition: FSBubbleBackground.Position

  init {
    initialize(context!!)

    val ta = context.obtainStyledAttributes(attrs, R.styleable.FSChatBubble, 0, 0)
    val textSize = ta.getDimension(R.styleable.FSChatBubble_textSize, 16F)
    val text = ta.getString(R.styleable.FSChatBubble_text)
    bubbleColor = ta.getColor(R.styleable.FSChatBubble_bubbleColor, Color.RED)
    val textColor = ta.getColor(R.styleable.FSChatBubble_textColor, Color.WHITE)
    val backgroundRes = ta.getResourceId(R.styleable.FSChatBubble_background, -1)
    bubblePosition = FSBubbleBackground.intToPosition(ta.getInt(R.styleable.FSChatBubble_bubblePosition, -1))
    ta.recycle()

    val vBackground = findViewById<View>(R.id.v_background)
    if (backgroundRes != -1) {
      vBackground.setBackgroundResource(backgroundRes)
    } else if (bubblePosition != FSBubbleBackground.Position.NONE) {
      background = FSBubbleBackground.setCornersForPosition(bubblePosition)
      background!!.bgColor = bubbleColor
      vBackground.background = background!!.shape
    }

    val tvChatContent = findViewById<TextView>(R.id.tv_chat_content)
    tvChatContent.text = text
    tvChatContent.setTextColor(textColor)
    tvChatContent.textSize = textSize / 2

  }

  private fun initialize(context: Context) {
    View.inflate(context, R.layout.chat_bubble, this)
  }

  override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
    super.onLayout(changed, left, top, right, bottom)
    Log.e("onMeasure", "width: ${findViewById<TextView>(R.id.tv_chat_content).width}")
    Log.e("onMeasure", "height: ${findViewById<TextView>(R.id.tv_chat_content).height}")
    Log.e("onMeasure", "lines: ${findViewById<TextView>(R.id.tv_chat_content).lineCount} ")

    val radius = height / (2F * findViewById<TextView>(R.id.tv_chat_content).lineCount)
    background = FSBubbleBackground.setCornersForPosition(FSBubbleBackground.Position.LeftSingle, radius, radius / 4)
  }

}
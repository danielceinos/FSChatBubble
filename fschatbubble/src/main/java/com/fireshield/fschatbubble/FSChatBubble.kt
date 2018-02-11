package com.fireshield.fschatbubble

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView


/**
 * Created by Daniel S on 10/02/2018.
 */
class FSChatBubble(context: Context?, attrs: AttributeSet?) : FrameLayout(context, attrs) {

  var bubbleBg: FSBubbleBackground? = null
    set(value) {
      field = value
      findViewById<View>(R.id.v_background).background = value?.shape
    }

  init {
    initialize(context!!)

    val ta = context.obtainStyledAttributes(attrs, R.styleable.FSChatBubble, 0, 0)
    val textSize = ta.getDimension(R.styleable.FSChatBubble_textSize, 16F)
    val text = ta.getString(R.styleable.FSChatBubble_text)
    val bubbleColor = ta.getColor(R.styleable.FSChatBubble_bubbleColor, Color.RED)
    val textColor = ta.getColor(R.styleable.FSChatBubble_textColor, Color.WHITE)
    val background = ta.getResourceId(R.styleable.FSChatBubble_background, -1)
    val bubbleBackground = ta.getInt(R.styleable.FSChatBubble_bubbleBackground, -1)
    ta.recycle()

    val vBackground = findViewById<View>(R.id.v_background)
    if (background != -1) {
      vBackground.setBackgroundResource(background)
    } else if (bubbleBackground != -1) {
      bubbleBg = FSBubbleBackground.getDefaultAt(bubbleBackground)
      bubbleBg!!.bgColor = bubbleColor
      vBackground.background = bubbleBg!!.shape
    }
    val tvChatContent = findViewById<TextView>(R.id.tv_chat_content)

    tvChatContent.text = text
    tvChatContent.setTextColor(textColor)
    tvChatContent.textSize = textSize

  }

  private fun initialize(context: Context) {
    View.inflate(context, R.layout.chat_bubble, this)
  }
}
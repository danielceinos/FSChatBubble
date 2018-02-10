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

  init {
    initialize(context!!)


    val ta = context.obtainStyledAttributes(attrs, R.styleable.FSChatBubble, 0, 0)
    val textSize = ta.getDimension(R.styleable.FSChatBubble_textSize, 0F)
    val text = ta.getString(R.styleable.FSChatBubble_text)
    val bubbleColor = ta.getColor(R.styleable.FSChatBubble_bubbleColor, Color.RED)
    val textColor = ta.getColor(R.styleable.FSChatBubble_textColor, Color.WHITE)
    val background = ta.getResourceId(R.styleable.FSChatBubble_background, -1)
    ta.recycle()

    if (background != -1)
      findViewById<View>(R.id.ll_backgound).setBackgroundResource(background)
    findViewById<TextView>(R.id.tv_chat_content).text = text
    findViewById<TextView>(R.id.tv_chat_content).setTextColor(textColor)
  }

  private fun initialize(context: Context) {
    View.inflate(context, R.layout.chat_bubble, this)
  }
}
package com.fireshield.fschatbubble

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Resources
import android.graphics.Color
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView


/**
 * Created by Daniel S on 10/02/2018.
 */
class FSChatBubble(context: Context?, attrs: AttributeSet?) : FrameLayout(context, attrs) {

  var background: FSBubbleBackground = FSBubbleBackground(Color.RED, 0F, 0F, 0F, 0F)
    set(value) {
      field = value
      findViewById<View>(R.id.v_background).background = value.shape
    }
  var text: String = ""
    @SuppressLint("WrongViewCast")
    set(value) {
      field = value
      findViewById<TextView>(R.id.tv_chat_content).text = value
      findViewById<TextView>(R.id.tv_chat_content).requestLayout()
    }

  var bubbleFSBubblePosition: FSBubblePosition

  init {
    initialize(context!!)

    val ta = context.obtainStyledAttributes(attrs, R.styleable.FSChatBubble, 0, 0)
    val textSize = ta.getDimension(R.styleable.FSChatBubble_textSize, 16F)
    val text = ta.getString(R.styleable.FSChatBubble_text)
    val bubbleColor = ta.getColor(R.styleable.FSChatBubble_bubbleColor, Color.RED)
    val textColor = ta.getColor(R.styleable.FSChatBubble_textColor, Color.WHITE)
    bubbleFSBubblePosition = FSBubbleBackground.intToPosition(ta.getInt(R.styleable.FSChatBubble_bubblePosition, -1))
    ta.recycle()

    background.bgColor = bubbleColor

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

    val radius = height / (2F * findViewById<TextView>(R.id.tv_chat_content).lineCount)
    background.setCornersForPosition(bubbleFSBubblePosition, radius, radius / 4)
    findViewById<View>(R.id.v_background).background = background.shape
  }

  private fun dpToPx(dp: Int): Float {
    return (dp * Resources.getSystem().displayMetrics.density)
  }
}
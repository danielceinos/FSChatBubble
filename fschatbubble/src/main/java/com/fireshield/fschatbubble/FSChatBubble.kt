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

    lateinit var tvChatContent: TextView
    lateinit var vBackground: View

    var background: FSBubbleBackground = FSBubbleBackground(Color.RED, 20F, 20F, 20F, 20F)
        set(value) {
            field = value
            vBackground.background = value.shape
        }
    var text: String = ""
        set(value) {
            field = value
            tvChatContent.text = value
            tvChatContent.requestLayout()
        }

    var bubbleFSBubblePosition: FSBubblePosition

    init {
        initialize(context!!)

        val ta = context.obtainStyledAttributes(attrs, R.styleable.FSChatBubble, 0, 0)
        val textSize = ta.getDimension(R.styleable.FSChatBubble_textSize, 16F)
        val text = ta.getString(R.styleable.FSChatBubble_text)
        val bubbleColor = ta.getColor(R.styleable.FSChatBubble_bubbleColor, Color.RED)
        val textColor = ta.getColor(R.styleable.FSChatBubble_textColor, Color.WHITE)
        bubbleFSBubblePosition = intToPosition(ta.getInt(R.styleable.FSChatBubble_bubblePosition, -1))
        ta.recycle()

        background.bgColor = bubbleColor
        vBackground.background = background.shape

        tvChatContent.text = text
        tvChatContent.setTextColor(textColor)
        tvChatContent.textSize = textSize / 2
    }

    private fun initialize(context: Context) {
        View.inflate(context, R.layout.chat_bubble, this)
        vBackground = findViewById<View>(R.id.v_background)
        tvChatContent = findViewById(R.id.tv_chat_content)
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)

        val radius = height / (2F * findViewById<TextView>(R.id.tv_chat_content).lineCount)
        background.setCornersForPosition(bubbleFSBubblePosition, radius, radius / 4)
        vBackground.background = background.shape
    }

    private fun intToPosition(index: Int): FSBubblePosition {
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
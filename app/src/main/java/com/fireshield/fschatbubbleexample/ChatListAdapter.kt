package com.fireshield.fschatbubbleexample

import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.RelativeLayout
import com.fireshield.fschatbubble.FSBubbleBackground
import com.fireshield.fschatbubble.FSChatBubble


/**
 * Created by Daniel S on 11/02/2018.
 */
class ChatListAdapter(var chatList: ArrayList<Item>) : RecyclerView.Adapter<ChatListAdapter.ViewHolder>() {

  override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
    val v = LayoutInflater.from(parent?.context).inflate(R.layout.row_chat_bubble, parent, false)
    return ViewHolder(v)
  }

  override fun getItemCount(): Int = chatList.size

  override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
    holder?.itemView?.requestLayout()
    var background: FSBubbleBackground?
    val item = chatList[position]
    val itemPrev = chatList.getOrNull(position - 1)
    val itemNext = chatList.getOrNull(position + 1)

    if (item.owner == 0) {
      if (itemPrev?.owner == item.owner) {
        if (position == chatList.size - 1 || itemNext?.owner != item.owner) {
          background = FSBubbleBackground.RightBottom
        } else {
          background = FSBubbleBackground.RightMiddle
        }
      } else {
        if (itemNext?.owner != item.owner) {
          background = FSBubbleBackground.RightBottom
        } else {
          background = FSBubbleBackground.RightTop
        }
      }
    } else if (item.owner == 1) {
      if (itemPrev?.owner == item.owner) {
        if (position == chatList.size - 1 || itemNext?.owner != item.owner) {
          background = FSBubbleBackground.LeftBottom
        } else {
          background = FSBubbleBackground.LeftMiddle
        }
      } else {
        if (itemNext?.owner != item.owner) {
          background = FSBubbleBackground.LeftBottom
        } else {
          background = FSBubbleBackground.LeftTop
        }
      }
    } else {
      background = null
    }
    if (background != null) {
      background.bgColor = Color.parseColor("#69DB94")
      holder?.bind(chatList[position].content, background, item.owner)
    } else {
      holder?.bindSeparator()
    }
  }

  class ViewHolder(val bubbleView: View) : RecyclerView.ViewHolder(bubbleView) {
    fun bind(content: String, background: FSBubbleBackground, owner: Int) {
      bubbleView.findViewById<FSChatBubble>(R.id.bubble_chat).visibility = VISIBLE
      bubbleView.findViewById<View>(R.id.separator).visibility = GONE

      val bubble = bubbleView.findViewById<FSChatBubble>(R.id.bubble_chat)
      bubble.text = content
      bubble.bubbleBg = background

      val params = RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
          ViewGroup.LayoutParams.WRAP_CONTENT)
      if (owner == 0) {
        params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT)
      } else {
        params.addRule(RelativeLayout.ALIGN_PARENT_LEFT)
      }
      params.setMargins(5, 0, 5, 0)

      bubble.layoutParams = params

    }

    fun bindSeparator() {
      bubbleView.findViewById<FSChatBubble>(R.id.bubble_chat).visibility = GONE
      bubbleView.findViewById<View>(R.id.separator).visibility = VISIBLE
    }
  }

  data class Item(val owner: Int, val content: String)
}
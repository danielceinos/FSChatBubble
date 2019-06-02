package com.fireshield.fschatbubbleexample

import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.RelativeLayout
import com.fireshield.fschatbubble.FSBubblePosition
import com.fireshield.fschatbubble.FSChatBubble


/**
 * Created by Daniel S on 11/02/2018.
 */
class ChatListAdapter(private var chatList: ArrayList<Item>) : RecyclerView.Adapter<ChatListAdapter.ViewHolder>() {

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    val v = LayoutInflater.from(parent.context).inflate(R.layout.row_chat_bubble, parent, false)
    return ViewHolder(v)
  }

  override fun getItemCount(): Int = chatList.size

  override fun onBindViewHolder(holder: ViewHolder, index: Int) {
    val bubblePosition: FSBubblePosition
    val item = chatList[index]
    val itemPrev = chatList.getOrNull(index - 1)
    val itemNext = chatList.getOrNull(index + 1)

    if (item.owner == 0) {
      if (itemPrev?.owner == item.owner) {
        if (index == chatList.size - 1 || itemNext?.owner != item.owner) {
          bubblePosition = FSBubblePosition.RightBottom
        } else {
          bubblePosition = FSBubblePosition.RightMiddle
        }
      } else {
        if (itemNext?.owner != item.owner) {
          bubblePosition = FSBubblePosition.RightSingle
        } else {
          bubblePosition = FSBubblePosition.RightTop
        }
      }
    } else if (item.owner == 1) {
      if (itemPrev?.owner == item.owner) {
        if (index == chatList.size - 1 || itemNext?.owner != item.owner) {
          bubblePosition = FSBubblePosition.LeftBottom
        } else {
          bubblePosition = FSBubblePosition.LeftMiddle
        }
      } else {
        if (itemNext?.owner != item.owner) {
          bubblePosition = FSBubblePosition.LeftSingle
        } else {
          bubblePosition = FSBubblePosition.LeftTop
        }
      }
    } else {
      bubblePosition = FSBubblePosition.NONE
    }
    if (bubblePosition != FSBubblePosition.NONE) {
      holder.bind(chatList[index].content, bubblePosition, item.owner)
    } else {
      holder.bindSeparator()
    }
  }

  fun add(item: Item) {
    if (chatList.lastOrNull()?.owner != item.owner)
      chatList.add(Item(2, ""))
    chatList.add(item)
    notifyItemChanged(chatList.size - 2)
    notifyItemInserted(chatList.size - 1)
  }

  class ViewHolder(val bubbleView: View) : RecyclerView.ViewHolder(bubbleView) {

    fun bind(content: String, bubblePosition: FSBubblePosition, owner: Int) {
      bubbleView.findViewById<FSChatBubble>(R.id.bubble_chat).visibility = VISIBLE
      bubbleView.findViewById<View>(R.id.separator).visibility = GONE

      val bubble = bubbleView.findViewById<FSChatBubble>(R.id.bubble_chat)
      val params = RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
          ViewGroup.LayoutParams.WRAP_CONTENT)
      if (owner == 0) {
        params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT)
        bubble.background.bgColor = Color.parseColor("#95c03a")
      } else {
        params.addRule(RelativeLayout.ALIGN_PARENT_LEFT)
        bubble.background.bgColor = Color.parseColor("#a8d841")
      }
      params.setMargins(5, 0, 5, 0)

      bubble.text = content
      bubble.bubbleFSBubblePosition = bubblePosition
      bubble.layoutParams = params
    }

    fun bindSeparator() {
      bubbleView.findViewById<FSChatBubble>(R.id.bubble_chat).visibility = GONE
      bubbleView.findViewById<View>(R.id.separator).visibility = VISIBLE
    }
  }

  data class Item(val owner: Int, val content: String)
}
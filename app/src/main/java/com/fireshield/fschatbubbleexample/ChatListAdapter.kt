package com.fireshield.fschatbubbleexample

import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.RelativeLayout
import com.fireshield.fschatbubble.FSChatBubble
import com.fireshield.fschatbubble.FSBubblePosition


/**
 * Created by Daniel S on 11/02/2018.
 */
class ChatListAdapter(var chatList: ArrayList<Item>) : RecyclerView.Adapter<ChatListAdapter.ViewHolder>() {

  override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
    val v = LayoutInflater.from(parent?.context).inflate(R.layout.row_chat_bubble, parent, false)
    return ViewHolder(v)
  }

  override fun getItemCount(): Int = chatList.size

  override fun onBindViewHolder(holder: ViewHolder?, index: Int) {
    var FSBubblePosition: FSBubblePosition
    val item = chatList[index]
    val itemPrev = chatList.getOrNull(index - 1)
    val itemNext = chatList.getOrNull(index + 1)

    if (item.owner == 0) {
      if (itemPrev?.owner == item.owner) {
        if (index == chatList.size - 1 || itemNext?.owner != item.owner) {
          FSBubblePosition = FSBubblePosition.RightBottom
        } else {
          FSBubblePosition = FSBubblePosition.RightMiddle
        }
      } else {
        if (itemNext?.owner != item.owner) {
          FSBubblePosition = FSBubblePosition.RightSingle
        } else {
          FSBubblePosition = FSBubblePosition.RightTop
        }
      }
    } else if (item.owner == 1) {
      if (itemPrev?.owner == item.owner) {
        if (index == chatList.size - 1 || itemNext?.owner != item.owner) {
          FSBubblePosition = FSBubblePosition.LeftBottom
        } else {
          FSBubblePosition = FSBubblePosition.LeftMiddle
        }
      } else {
        if (itemNext?.owner != item.owner) {
          FSBubblePosition = FSBubblePosition.LeftSingle
        } else {
          FSBubblePosition = FSBubblePosition.LeftTop
        }
      }
    } else {
      FSBubblePosition = FSBubblePosition.NONE
    }
    if (FSBubblePosition != FSBubblePosition.NONE) {
      holder?.bind(chatList[index].content, FSBubblePosition, item.owner)
    } else {
      holder?.bindSeparator()
    }
  }

  fun add(item: Item) {
    if (chatList.lastOrNull()?.owner != item.owner)
      chatList.add(Item(2, ""))
    chatList.add(item)
    notifyItemChanged(chatList.size-2)
    notifyItemInserted(chatList.size-1)
  }

  class ViewHolder(val bubbleView: View) : RecyclerView.ViewHolder(bubbleView) {
    fun bind(content: String, FSBubblePosition: FSBubblePosition, owner: Int) {
      bubbleView.findViewById<FSChatBubble>(R.id.bubble_chat).visibility = VISIBLE
      bubbleView.findViewById<View>(R.id.separator).visibility = GONE

      val params = RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
          ViewGroup.LayoutParams.WRAP_CONTENT)
      if (owner == 0) {
        params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT)
      } else {
        params.addRule(RelativeLayout.ALIGN_PARENT_LEFT)
      }
      params.setMargins(5, 0, 5, 0)

      val bubble = bubbleView.findViewById<FSChatBubble>(R.id.bubble_chat)
      bubble.text = content
      bubble.bubbleFSBubblePosition = FSBubblePosition
      bubble.layoutParams = params
      if (owner == 0) {
        bubble.background.bgColor = Color.parseColor("#95c03a")
      }else{
        bubble.background.bgColor = Color.parseColor("#a8d841")
      }
    }

    fun bindSeparator() {
      bubbleView.findViewById<FSChatBubble>(R.id.bubble_chat).visibility = GONE
      bubbleView.findViewById<View>(R.id.separator).visibility = VISIBLE
    }
  }

  data class Item(val owner: Int, val content: String)
}
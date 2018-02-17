package com.fireshield.fschatbubbleexample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout

class MainActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    val users = ArrayList<ChatListAdapter.Item>()

    val adapter = ChatListAdapter(users)

    val recyclerView = findViewById<RecyclerView>(R.id.rv_chat)
    recyclerView.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
    recyclerView.adapter = adapter

    val etMsg = findViewById<EditText>(R.id.et_text_box)
    findViewById<Button>(R.id.b_send_der).setOnClickListener {
      adapter.add(ChatListAdapter.Item(0, etMsg.text.toString()))
      etMsg.setText("")
      recyclerView.scrollToPosition(adapter.itemCount - 1)
    }
    findViewById<Button>(R.id.b_send_izq).setOnClickListener {
      adapter.add(ChatListAdapter.Item(1, etMsg.text.toString()))
      etMsg.setText("")
      recyclerView.scrollToPosition(adapter.itemCount - 1)
    }
  }
}

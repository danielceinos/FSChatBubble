package com.fireshield.fschatbubbleexample

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.LinearLayout

class MainActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    val users = ArrayList<ChatListAdapter.Item>()
    users.add(ChatListAdapter.Item(1, "Kaju katli, also known as kaju Katari or kaju barfi, is an Indian dessert similar to a barfi."))
    users.add(ChatListAdapter.Item(1, "The outlets"))
    users.add(ChatListAdapter.Item(2, ""))
    users.add(ChatListAdapter.Item(0, "Panna cotta is an Italian desseffee, vanilla, or other flavorings."))
    users.add(ChatListAdapter.Item(0, "Rose cooky is a famous South Indian snack made during festivals"))
    users.add(ChatListAdapter.Item(2, ""))
    users.add(ChatListAdapter.Item(1, "In North Aer squares, and dn waffles"))
    users.add(ChatListAdapter.Item(2, ""))
    users.add(ChatListAdapter.Item(0, "Kaju ."))
    users.add(ChatListAdapter.Item(2, ""))
    users.add(ChatListAdapter.Item(1, "The doughnut is popular in many countries and prepared in various fokets, food stalls, and franchised specialty outlets"))
    users.add(ChatListAdapter.Item(2, ""))
    users.add(ChatListAdapter.Item(0, "Panna cotta is an Itvanilla, or other flavorings."))
    users.add(ChatListAdapter.Item(0, "Rose cooky "))
    users.add(ChatListAdapter.Item(2, ""))
    users.add(ChatListAdapter.Item(1, "In North Aer squares, and deeper pockets than ordinary American waffles"))
    users.add(ChatListAdapter.Item(2, ""))
    users.add(ChatListAdapter.Item(0, "Panna cotta is an Italian desseffee, vanilla, or other flavorings."))
    users.add(ChatListAdapter.Item(0, "Rose cooky is a famous South Indian snack made during festivals"))
    users.add(ChatListAdapter.Item(2, ""))
    users.add(ChatListAdapter.Item(1, "In North Aer squares, and dn waffles"))
    users.add(ChatListAdapter.Item(2, ""))
    users.add(ChatListAdapter.Item(0, "Kaju ."))
    users.add(ChatListAdapter.Item(2, ""))
    users.add(ChatListAdapter.Item(1, "The doughnut is popular in many countries and prepared in various fokets, food stalls, and franchised specialty outlets"))
    users.add(ChatListAdapter.Item(2, ""))
    users.add(ChatListAdapter.Item(0, "Panna cotta is an Itvanilla, or other flavorings."))
    users.add(ChatListAdapter.Item(0, "Rose cooky "))
    users.add(ChatListAdapter.Item(2, ""))
    users.add(ChatListAdapter.Item(1, "In North Aer squares, and deeper pockets than ordinary American waffles"))

    val adapter = ChatListAdapter(users)

    val recyclerView = findViewById<RecyclerView>(R.id.rv_chat)
    recyclerView.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
    recyclerView.adapter = adapter

  }
}

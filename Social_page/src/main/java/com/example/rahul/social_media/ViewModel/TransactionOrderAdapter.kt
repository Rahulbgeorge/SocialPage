package com.example.rahul.social_media.ViewModel

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.rahul.social_media.Model.Order
import com.example.rahul.social_media.R
import kotlinx.android.synthetic.main.item_transaction.view.*

class TransactionOrderAdapter(private val orders:ArrayList<Order>,private val context:Context):RecyclerView.Adapter<TransactionHolder>()
{

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionHolder {

        Log.e("view holder","hodler"+viewType)
        return TransactionHolder(LayoutInflater.from(context).inflate(R.layout.item_transaction, parent, false))
    }

    override fun getItemCount(): Int = orders.size

    override fun onBindViewHolder(holder: TransactionHolder, position: Int) {
        val order=orders.get(position)

        holder.title.setText(order.username+" has transacted "+order.cost)
        holder.material.setText("22 Karat gold")
        holder.image.setImageResource(R.drawable.log)
        holder.qty.setText(order.qty+" gms")
        holder.view.setOnClickListener {

            Toast.makeText(context,order.username+"item is clilcked",Toast.LENGTH_SHORT).show()
        }
    }

}

class TransactionHolder(view:View):RecyclerView.ViewHolder(view){

    val title=view.title
    val material=view.material
    val qty=view.qty
    val image=view.imageView
    val view=view


}


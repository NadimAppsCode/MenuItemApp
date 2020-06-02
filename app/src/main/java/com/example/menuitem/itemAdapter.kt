package com.example.menuitem

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.menuitem.model.mItem
import com.squareup.picasso.Picasso

class itemAdapter(private val context: Context,
                  private val dataSource: ArrayList<mItem>) : BaseAdapter() {
    private val inflater: LayoutInflater
            = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    //1
    override fun getCount(): Int {
        return dataSource.size
    }

    //2
    override fun getItem(position: Int): Any {
        return dataSource[position]
    }

    //3
    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    //4
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        // Get view for row item
        val rowView = inflater.inflate(R.layout.itemlist, parent, false)

        // Get title element
        val itemimage = rowView.findViewById(R.id.icon) as ImageView

// Get subtitle element
        val subtitleTextView = rowView.findViewById(R.id.itemname) as TextView

// Get detail element
        val detailTextView = rowView.findViewById(R.id.itemprice) as TextView

        val item = getItem(position) as mItem

// 2
        //titleTextView. = item.imageUrl
        subtitleTextView.text = item.name
        detailTextView.text = "$" + item.price + ".00"

// 3
     //   System.out.println("Imageur:" + item.dishimage)
        Picasso.with(context).load(item.dishimage).placeholder(R.mipmap.ic_launcher).into(itemimage)

        return rowView
    }
}
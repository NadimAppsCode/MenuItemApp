package com.example.menuitem

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import com.example.menuitem.model.mItem
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.IOException


class MainActivity : AppCompatActivity() {

    private lateinit var listView :ListView

    fun loadJsonDataFromAsset(context: Context, fileName: String): String? {
        val jsonString: String;
       // val path = context.filesDir.absolutePath + "/" + fileName
        try{
            jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() };
        }catch (ioException: IOException){
            ioException.printStackTrace();
            return null;
        }

        return jsonString;
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val listView = findViewById<ListView>(R.id.menuitem)

        val listItems = arrayOfNulls<String>(2)

       // listItems[0].name = "Test"
       // listItems[1].name = "Test1"
       // System.out.println("data : "+baseContext.filesDir.absolutePath);
        val jsonDataString = loadJsonDataFromAsset(baseContext, "menuitem.json")
       // System.out.println("data : "+jsonDataString);

        val gson = Gson()
        val listMenuItem = object : TypeToken<List<mItem>>() {}.type

        val menuItems: List<mItem> = gson.fromJson( jsonDataString, listMenuItem)

        for (i in 0 until menuItems.size) {
            listItems[i] = menuItems[i].name
        }
        val adapter = itemAdapter(baseContext, menuItems as ArrayList<mItem>)
        listView.adapter = adapter
        //val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, listItems)
        //listView.adapter = adapter
        val context = this
        listView.setOnItemClickListener { parent, view, position, id ->
            //System.out.println("Item clicked pos = " + position)
            val item = menuItems[position]
           // System.out.println("Item clicked pos = " + item.name)
            val detailIntent = Intent(this, itemdetails::class.java)
            detailIntent.putExtra("Description", item.ingredients)
            startActivity(detailIntent)
        }

    }

    companion object{
        private lateinit var context:Context
        fun senContext(con: Context){
            context =con
        }
    }
}

package com.example.getandpostlocation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create

class MainActivity : AppCompatActivity() {
    lateinit var edName:EditText
    lateinit var edLoc:EditText
    lateinit var edNameToLoc:EditText

    lateinit var btnSave:Button
    lateinit var btnSearch:Button

    lateinit var tvLocation:TextView

    lateinit var name:String
    lateinit var loc:String
    lateinit var NameToLoc:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        edName=findViewById(R.id.edName)
        edLoc=findViewById(R.id.edLocation)
        edNameToLoc=findViewById(R.id.edNameSearch)

        btnSave=findViewById(R.id.btnSave)
        btnSearch=findViewById(R.id.btnSearch)

        tvLocation=findViewById(R.id.tvLocation)

        btnSave.setOnClickListener(){
            name=edName.text.toString()
            loc=edLoc.text.toString()
            if (name.isNotEmpty() && loc.isNotEmpty()){
                postData()
            }else
                Toast.makeText(applicationContext, "please enter missing fields",Toast.LENGTH_LONG).show()
        }//end save listener

        btnSearch.setOnClickListener(){
            NameToLoc=edNameToLoc.text.toString()
            if (NameToLoc.isNotEmpty()){
                getNameToLoc()
            }else
                Toast.makeText(applicationContext, "please enter missing fields",Toast.LENGTH_LONG).show()
        }//end search listener

    }//end onCreate

    fun postData() {
        val apIinterface=APIclint().getClient()?.create(APIinterface::class.java)
        apIinterface?.postData(myData.myDataItem(loc,name))?.enqueue(object : Callback<myData.myDataItem?> {
            override fun onResponse(call: Call<myData.myDataItem?>, response: Response<myData.myDataItem?>) {
                Toast.makeText(applicationContext, "$name data is added successfully", Toast.LENGTH_LONG).show()
            }

            override fun onFailure(call: Call<myData.myDataItem?>, t: Throwable) {
                Toast.makeText(applicationContext, "$t", Toast.LENGTH_LONG).show()
            }
        })
    }//end postData()

    fun getNameToLoc(){
        val apIinterface=APIclint().getClient()?.create(APIinterface::class.java)
        apIinterface?.getDate()?.enqueue(object : Callback<List<myData.myDataItem>?> {
            override fun onResponse(call: Call<List<myData.myDataItem>?>, response: Response<List<myData.myDataItem>?>) {
                val response=response.body()
                for (item in response!!){
                    if (item.name.equals(NameToLoc)){
                        tvLocation.text=item.location
                    }
                }
            }

            override fun onFailure(call: Call<List<myData.myDataItem>?>, t: Throwable) {
                Toast.makeText(applicationContext, "$t", Toast.LENGTH_LONG).show()
            }
        })
    }//end getNameToLoc()

}
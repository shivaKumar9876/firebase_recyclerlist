package com.example.recyclerviewinfocom.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewinfocom.DetailsAdapter
import com.example.recyclerviewinfocom.R
import com.example.recyclerviewinfocom.data.FetchDetailsModel
import com.example.recyclerviewinfocom.databinding.ActivityMainBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var dataList: ArrayList<FetchDetailsModel>
    private lateinit var adapter: DetailsAdapter
    private lateinit var binding: ActivityMainBinding
    private lateinit var dataBase: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        dataList = arrayListOf()
        dataBase = FirebaseDatabase.getInstance().getReference("User")
        dataBase.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                dataList.clear()
                if (snapshot.exists()) {
                    for (data in snapshot.children) {
                        val userData = data.getValue(FetchDetailsModel::class.java)
                        dataList.add(userData!!)
                    }
                    adapter = DetailsAdapter(dataList)
                    recyclerView.adapter = adapter
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("firebase", "${error.message}")
            }
        })

        recyclerView = findViewById(R.id.rv_container)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = DetailsAdapter(dataList)
        recyclerView.adapter = adapter
        recyclerView.hasFixedSize()

        binding.addDetatilsForm.setOnClickListener {
            var intent = Intent(this, AddDetatilsActivity::class.java)
            startActivity(intent)
        }
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){

            R.id.sort_by_name -> {
                dataList.sortBy {
                    it.name?.toLowerCase()
                }
                adapter = DetailsAdapter(dataList)
                recyclerView.adapter = adapter
                recyclerView.hasFixedSize()}

            R.id.sort_by_city -> {
                Toast.makeText(this,"Clicked sort by city",Toast.LENGTH_SHORT).show()
                dataList.sortBy {
                    it.city?.toLowerCase()
                }
                adapter = DetailsAdapter(dataList)
                recyclerView.adapter = adapter
                recyclerView.hasFixedSize()

            }
            R.id.sort_by_age -> {
                Toast.makeText(this,"Clicked sort by age",Toast.LENGTH_SHORT).show()
                dataList.sortBy {
                    it.age
                }
                adapter = DetailsAdapter(dataList)
                recyclerView.adapter = adapter
                recyclerView.hasFixedSize()
                }
            }
        return true
        }

    }

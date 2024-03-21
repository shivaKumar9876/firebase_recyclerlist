package com.example.recyclerviewinfocom.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.recyclerviewinfocom.data.FetchDetailsModel
import com.example.recyclerviewinfocom.databinding.ActivityAddDetailsBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class AddDetatilsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddDetailsBinding
    private lateinit var database : DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        database = FirebaseDatabase.getInstance().getReference("User")
        binding.apply {
            btnAddDetails.setOnClickListener {
                var name = inputName.text.toString()
                var city = inputCity.text.toString()
                var age =inputAge.text.toString().toInt()
                Log.i("age","$age")
                val ref: String = database.push().key!!
                var data = FetchDetailsModel(ref,name,city,age)

                database.child(name).setValue(data).addOnCompleteListener {
                    inputAge.text?.clear()
                    inputCity.text?.clear()
                    inputAge.text?.clear()
                    finish()

                }
            }

        }
    }
}
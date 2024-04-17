package com.example.familyconnection

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.QuerySnapshot

class Contact : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var contactsArrayList: ArrayList<Contacts>
    private lateinit var contactAdapter: ContactAdapter
    private lateinit var db: FirebaseFirestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact)

        recyclerView = findViewById(R.id.contacts_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        contactsArrayList = arrayListOf()

        contactAdapter = ContactAdapter(contactsArrayList)

        recyclerView.adapter = contactAdapter

        EventChangeListener()

        val Return = findViewById<Button>(R.id.back)
        Return.setOnClickListener {
            startActivity(Intent(this, main_page::class.java))
        }
    }
    private fun EventChangeListener() {

        db = FirebaseFirestore.getInstance()
        db.collection("Admin").whereNotEqualTo("title", null).orderBy("title", Query.Direction.ASCENDING)
            .addSnapshotListener(object : EventListener<QuerySnapshot> {
                override fun onEvent(value: QuerySnapshot?, error: FirebaseFirestoreException?) {
                    if (error != null) {
                        Log.e("Firestore Error",error.message.toString())
                        return
                    }
                    for (dc : DocumentChange in value?.documentChanges!!) {

                        if (dc.type == DocumentChange.Type.ADDED) {

                            contactsArrayList.add(dc.document.toObject(Contacts::class.java))

                        }
                    }
                    contactAdapter.notifyDataSetChanged()
                }
            })
    }
}
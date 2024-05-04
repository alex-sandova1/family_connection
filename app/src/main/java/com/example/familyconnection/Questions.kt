package com.example.familyconnection

import FAQAdapter
import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore

 class Questions : AppCompatActivity() {
    private lateinit var db: FirebaseFirestore
    private lateinit var faqList: ArrayList<FAQsClass>
    private lateinit var faqRecyclerView: RecyclerView
    private lateinit var faqAdapter: FAQAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_questions)

        db = FirebaseFirestore.getInstance()
        faqList = ArrayList()
        faqRecyclerView = findViewById(R.id.faq_recycler_view)
        faqAdapter = FAQAdapter(faqList)
        faqRecyclerView.adapter = faqAdapter

        fetchFAQs()
    }

   private fun fetchFAQs() {
    db.collection("Faqs").get()
        .addOnSuccessListener { documents ->
            for (document in documents) {
                Log.d(TAG, "${document.id} => ${document.data}")
                val questionAnswerList =
                    (document.get("questionAnswerList") as? List<Map<String, String>>?) ?: emptyList()
                val qaList = questionAnswerList.map { map ->
                    FAQsClass(map["question"], map["answer"])
                }
                this.faqList.addAll(qaList) // Use this.faqList to refer to the member variable
                Log.d(TAG, "Added FAQ: $qaList") // Log the added FAQ
                faqAdapter.notifyDataSetChanged()
            }
        }
        .addOnFailureListener { exception ->
            Log.d(TAG, "get failed with ", exception)
        }
}
}
package com.example.familyconnection

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.FirebaseFirestore


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [DayFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DayFragment : Fragment(){

private lateinit var recyclerView: RecyclerView
private lateinit var viewAdapter: RecyclerView.Adapter<*>
private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_day, container, false)

        viewManager = LinearLayoutManager(context)

        val day = arguments?.getString("day")

        val db = FirebaseFirestore.getInstance()
        db.collection(day!!).get()
            .addOnSuccessListener { documents ->
                val myDataset = ArrayList<String>()
                for (document in documents) {
                    Log.d(TAG, "Document: ${document.id} => ${document.data}")
                    myDataset.add(document.getString("YourFieldName")!!)
                }
                viewAdapter = MyAdapter(myDataset)

                recyclerView = view.findViewById<RecyclerView>(R.id.my_recycler_view).apply {
                    setHasFixedSize(true)
                    layoutManager = viewManager
                    adapter = viewAdapter
                }
            }
            .addOnFailureListener { exception ->
                Log.w(TAG, "Error getting documents: ", exception)
            }

        return view
    }

        companion object {
            fun newInstance(day: String) = DayFragment().apply {
                arguments = Bundle().apply {
                  putString("day", day)
                }
            }
        }
    }
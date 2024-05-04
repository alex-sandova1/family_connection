package com.example.familyconnection

import android.text.util.Linkify
import android.view.LayoutInflater
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import android.view.View
import android.view.ViewGroup

class ContactAdapter(private val contactsArrayList: ArrayList<Contacts>) : RecyclerView.Adapter<ContactAdapter.ContactViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ContactAdapter.ContactViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.contact_item, parent, false)
        return ContactViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ContactAdapter.ContactViewHolder, position: Int) {
        val contact : Contacts = contactsArrayList[position]
        holder.title.text = contact.title
        holder.firstName.text = contact.firstName
        holder.lastName.text = contact.lastName
        holder.email.text = contact.email
        holder.phoneNumber.text = contact.phoneNumber

        // Linkify the email and phone number
        Linkify.addLinks(holder.email, Linkify.EMAIL_ADDRESSES)
        Linkify.addLinks(holder.phoneNumber, Linkify.PHONE_NUMBERS)
    }

    override fun getItemCount(): Int {
        return contactsArrayList.size
    }

    public class ContactViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val title : TextView = itemView.findViewById(R.id.contact_title)
        val firstName : TextView = itemView.findViewById(R.id.first_name_text)
        val lastName : TextView = itemView.findViewById(R.id.last_name_text)
        val email : TextView = itemView.findViewById(R.id.email_text)
        val phoneNumber : TextView = itemView.findViewById(R.id.phone_number_text)
    }

}
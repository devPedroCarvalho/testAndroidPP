package app.devpedrocarvalho.testpp.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import app.devpedrocarvalho.testpp.R

class ContactsListAdapter(private val listContacts: List<String>): RecyclerView.Adapter<ContactsListAdapter.ContactsListViewHolder>() {

    class ContactsListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        val username: TextView = itemView.findViewById(R.id.usernameTextView)
        val name: TextView = itemView.findViewById(R.id.nameTextView)
        val imageProfile: ImageView = itemView.findViewById(R.id.profileImageImageView)

        fun bind(model: String) {
            username.text = model
            name.text = model
            //imageProfile
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactsListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list_contacts_recycler_view, parent,false)
        return ContactsListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ContactsListViewHolder, position: Int) {
        when(holder) {
            is ContactsListViewHolder -> {
                holder.bind(listContacts[position])
            }
        }

    }

    override fun getItemCount(): Int {
       return listContacts.size
    }
}
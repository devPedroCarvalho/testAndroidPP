package app.devpedrocarvalho.testpp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import app.devpedrocarvalho.testpp.R
import app.devpedrocarvalho.testpp.loadImage
import app.devpedrocarvalho.testpp.network.response.ContactsResponse

class ContactsListAdapter(private val listContacts: ArrayList<ContactsResponse>): RecyclerView.Adapter<ContactsListAdapter.ContactsListViewHolder>() {

    class ContactsListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        private val username: TextView = itemView.findViewById(R.id.usernameTextView)
        private val name: TextView = itemView.findViewById(R.id.nameTextView)
        private val imageProfile: ImageView = itemView.findViewById(R.id.profileImageImageView)

        fun bind(model: ContactsResponse) {
            username.text = model.username
            name.text = model.name
            loadImage(
                imageUrl = model.img!!,
                imageView = imageProfile,
                context = itemView
            )
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
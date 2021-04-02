package app.devpedrocarvalho.testpp.ui.adapter

import android.graphics.Bitmap
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import app.devpedrocarvalho.testpp.R
import app.devpedrocarvalho.testpp.network.response.ContactsResponse
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener

class ContactsListAdapter(private val listContacts: List<ContactsResponse>): RecyclerView.Adapter<ContactsListAdapter.ContactsListViewHolder>() {

    class ContactsListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        private val username: TextView = itemView.findViewById(R.id.usernameTextView)
        private val name: TextView = itemView.findViewById(R.id.nameTextView)
        private val imageProfile: ImageView = itemView.findViewById(R.id.profileImageImageView)

        fun bind(model: ContactsResponse) {
            username.text = model.username
            name.text = model.name

            try {
                Glide.with(itemView)
                        .asBitmap()
                        .load(model.img)
                        .placeholder(R.drawable.ic_profile_empty)
                        .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                        .circleCrop()
                        .listener(object :RequestListener<Bitmap> {
                            override fun onLoadFailed(e: GlideException?, model: Any?,target: com.bumptech.glide.request.target.Target<Bitmap>?, isFirstResource: Boolean): Boolean {
                                Log.d("Excepion",e?.message.toString())
                                return false
                            }

                            override fun onResourceReady(resource: Bitmap?, model: Any?, target: com.bumptech.glide.request.target.Target<Bitmap>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                                return false
                            }

                        }).into(imageProfile)
            }
            catch (e : Exception)
            {
                Log.d("Excepion",e.message.toString())
            }
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
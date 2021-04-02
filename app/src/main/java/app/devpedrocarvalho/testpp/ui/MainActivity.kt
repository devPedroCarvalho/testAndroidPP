package app.devpedrocarvalho.testpp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import app.devpedrocarvalho.testpp.R
import app.devpedrocarvalho.testpp.databinding.ActivityMainBinding
import app.devpedrocarvalho.testpp.utils.isNetworkConnected
import app.devpedrocarvalho.testpp.network.response.ContactsResponse
import app.devpedrocarvalho.testpp.ui.adapter.ContactsListAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var  viewModel: MainActivityViewModel
    private lateinit var adapterContacts: ContactsListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)

        setupObserver()

        if (isNetworkConnected(context = this)){
            viewModel.getContactsList()
        }else{
            viewModel.getContactsListDatabase()
        }

    }

    private fun setupObserver() {
        viewModel.contactsListLiveData.observe(this, Observer {
            it?.let {
                setUpRecyclerView(it)
            }
        })
    }

    private fun setUpRecyclerView(list: List<ContactsResponse>){

        adapterContacts = ContactsListAdapter(listContacts = list)
        binding.contactsRecyclerView.apply {
            adapter = adapterContacts
            adapter?.notifyDataSetChanged()
            layoutManager = LinearLayoutManager(context)
        }
    }

}
package app.devpedrocarvalho.testpp.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import app.devpedrocarvalho.testpp.R
import app.devpedrocarvalho.testpp.databinding.ActivityMainBinding
import app.devpedrocarvalho.testpp.network.resource.Status
import app.devpedrocarvalho.testpp.network.response.ContactsResponse
import app.devpedrocarvalho.testpp.ui.adapter.ContactsListAdapter
import app.devpedrocarvalho.testpp.utils.isNetworkConnected
import app.devpedrocarvalho.testpp.utils.showToast
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainActivityViewModel
    private lateinit var adapterContacts: ContactsListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Timber.plant(Timber.DebugTree())

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)

        setupObserver()

        viewModel.loadData(this)
    }

    private fun setupObserver() {
        viewModel.contactsListLiveData.observe(this, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        it.data?.let { list ->
                            setUpRecyclerView(list)
                        }
                        binding.mainProgressBar.visibility = View.INVISIBLE
                    }
                    Status.ERROR -> {
                        binding.mainProgressBar.visibility = View.INVISIBLE
                        showToast(this, getString(R.string.error_common, it.message))
                    }
                    Status.LOADING -> {
                        binding.mainProgressBar.visibility = View.VISIBLE
                    }
                }
            }
        })
    }

    private fun setUpRecyclerView(list: List<ContactsResponse>) {
        adapterContacts = ContactsListAdapter(listContacts = list)
        binding.contactsRecyclerView.apply {
            adapter = adapterContacts
            adapter?.notifyDataSetChanged()
            layoutManager = LinearLayoutManager(context)
        }
    }

}
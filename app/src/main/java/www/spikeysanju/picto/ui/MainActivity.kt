package www.spikeysanju.picto.ui

import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import www.spikeysanju.picto.R
import www.spikeysanju.picto.adapter.PostAdapter
import www.spikeysanju.picto.db.PostDatabase
import www.spikeysanju.picto.repo.PostRepository
import www.spikeysanju.picto.ui.viewmodel.PostViewModel
import www.spikeysanju.picto.ui.viewmodel.PostViewModelProviderFactory
import www.spikeysanju.picto.utils.Resource
import www.spikeysanju.picto.utils.hide
import www.spikeysanju.picto.utils.show

class MainActivity : AppCompatActivity() {


    lateinit var postViewModel: PostViewModel
    lateinit var postAdapter: PostAdapter

    val TAG = "Images"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // init viewModelProvider
        val newsRepository = PostRepository(PostDatabase(this))
        val viewModelProviderFactory = PostViewModelProviderFactory(newsRepository)
        postViewModel =
            ViewModelProvider(this, viewModelProviderFactory).get(PostViewModel::class.java)

        // setup Night Mode

        setUpNightMode()

        // init RV
        setUpRecyclerView()

        // init viewModel
        postViewModel = (this).postViewModel

        // observe for changes
        postViewModel.postData.observe(this, androidx.lifecycle.Observer { response ->

            when (response) {

                is Resource.Loading -> {
                    mProgress.show()
                }

                is Resource.Success -> {
                    mProgress.hide()
                    response.data?.let { postResponse ->
                        postAdapter.differ.submitList(postResponse)
                    }
                }
                is Resource.Error -> {
                    mProgress.hide()
                    response.message?.let { message ->
                        Log.d(TAG, "An Error Occured:$message")
                    }
                }
            }

        })


    }

    private fun setUpNightMode() {


        val appSettingPrefs: SharedPreferences = getSharedPreferences("AppSettingPrefs", 0)
        val sharedPrefsEdit: SharedPreferences.Editor = appSettingPrefs.edit()
        val isNightModeOn: Boolean = appSettingPrefs.getBoolean("NightMode", false)

        if (isNightModeOn) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }

        dark_switch_fab.setOnClickListener {
            if (isNightModeOn) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                sharedPrefsEdit.putBoolean("NightMode", false)
                sharedPrefsEdit.apply()

            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                sharedPrefsEdit.putBoolean("NightMode", true)
                sharedPrefsEdit.apply()
            }
        }

    }

    private fun setUpRecyclerView() {
        postAdapter = PostAdapter()
        post_rv.apply {
            adapter = postAdapter
            layoutManager = GridLayoutManager(this@MainActivity, 2)
        }
    }

}

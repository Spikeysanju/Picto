package www.spikeysanju.picto.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import www.spikeysanju.picto.R
import www.spikeysanju.picto.adapter.PostAdapter
import www.spikeysanju.picto.ui.viewmodel.PostViewModel
import www.spikeysanju.picto.utils.Resource
import java.util.*

class MainActivity : AppCompatActivity() {


    lateinit var viewModel: PostViewModel
    lateinit var postAdapter: PostAdapter
    val TAG = "Images"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // init RV
        setUpRecyclerView()




        viewModel = (this).viewModel


    }

    private fun setUpRecyclerView() {
        postAdapter = PostAdapter()
        post_rv.apply {
            adapter = postAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
    }

}

package www.spikeysanju.picto.ui.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import www.spikeysanju.picto.repo.PostRepository

class PostViewModelProviderFactory(val app: Application, val postRepository: PostRepository) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PostViewModel(app, postRepository) as T
    }
}
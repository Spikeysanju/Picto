package www.spikeysanju.picto.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import www.spikeysanju.picto.repo.PostRepository

class PostViewModelProviderFactory(val postRepository: PostRepository) : ViewModelProvider.Factory{

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PostViewModel(postRepository) as T
    }
}
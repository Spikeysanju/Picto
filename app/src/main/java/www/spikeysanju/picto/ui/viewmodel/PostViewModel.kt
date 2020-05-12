package www.spikeysanju.picto.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.Response
import www.spikeysanju.picto.model.PostResponse
import www.spikeysanju.picto.repo.PostRepository
import www.spikeysanju.picto.utils.Resource

class PostViewModel (val postRepository: PostRepository) : ViewModel() {


    // Mutable Live Data
    val postData : MutableLiveData<Resource<PostResponse>> = MutableLiveData()

    init {
        getAllPostData()
    }

    // get All Post Data
    fun getAllPostData() = viewModelScope.launch {

        postData.postValue(Resource.Loading())
        val response = postRepository.getAllPost()
        postData.postValue(handleAllPostResponse(response))

    }


    // Handle Post Data
    private fun handleAllPostResponse(response: Response<PostResponse>): Resource<PostResponse> {

        // if successful emit data
        if (response.isSuccessful){
            response.body()?.let { resultResponse ->
                return  Resource.Success(resultResponse)
            }
        }
        // if failure emit data with message
        return  Resource.Error(response.message())
    }



}
package www.spikeysanju.picto.ui.viewmodel

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.Response
import www.spikeysanju.picto.app.PictoApp
import www.spikeysanju.picto.model.PostResponse
import www.spikeysanju.picto.repo.PostRepository
import www.spikeysanju.picto.utils.Resource
import java.io.IOException
import java.net.UnknownHostException

class PostViewModel(app: Application, val postRepository: PostRepository) : AndroidViewModel(app) {


    // Mutable Live Data
    val postData: MutableLiveData<Resource<PostResponse>> = MutableLiveData()

    init {
        getAllPostData()
    }

    // get All Post Data
    fun getAllPostData() = viewModelScope.launch {
        safeImagePostCall()
    }

    // safe api call
    private suspend fun safeImagePostCall() {
        postData.postValue(Resource.Loading())

        try {
            if (hasInternetConnection()) {
                val response = postRepository.getAllPost()
                postData.postValue(handleAllPostResponse(response))
            } else {
                postData.postValue(Resource.Error("No internet connection"))
            }

        } catch (t: Throwable) {
            when (t) {
                is IOException -> postData.postValue(Resource.Error("Network Failure"))
                is UnknownHostException -> postData.postValue(Resource.Error("Unknown host!"))
                else -> postData.postValue(Resource.Error("Conversion Error"))
            }
        }
    }


    // Handle Post Data
    private fun handleAllPostResponse(response: Response<PostResponse>): Resource<PostResponse> {

        // if successful emit data
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        // if failure emit data with message
        return Resource.Error(response.message())
    }


    private fun hasInternetConnection(): Boolean {


        val connectivityManager = getApplication<PictoApp>().getSystemService(
            Context.CONNECTIVITY_SERVICE
        ) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val activeNetwork = connectivityManager.activeNetwork ?: return false
            val capabilities =
                connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false
            return when {
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                else -> false

            }
        } else {
            connectivityManager.activeNetworkInfo?.run {
                return when (type) {
                    ConnectivityManager.TYPE_WIFI -> true
                    ConnectivityManager.TYPE_MOBILE -> true
                    ConnectivityManager.TYPE_ETHERNET -> true
                    else -> false
                }
            }
        }
        return false
    }


}
package www.spikeysanju.picto.repo

import www.spikeysanju.picto.data.api.RetrofitInstance
import www.spikeysanju.picto.data.api.db.PostDatabase

class PostRepository (val db: PostDatabase) {

    // get All Post
    suspend fun getAllPost() = RetrofitInstance.api.getAllPost()

}
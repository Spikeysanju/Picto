package www.spikeysanju.picto.repo

import www.spikeysanju.picto.api.RetrofitInstance
import www.spikeysanju.picto.db.PostDatabase

class PostRepository (val db: PostDatabase) {

    // get All Post
    suspend fun getAllPost() = RetrofitInstance.api.getAllPost()

}
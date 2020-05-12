package www.spikeysanju.picto.api

import retrofit2.Response
import retrofit2.http.GET
import www.spikeysanju.picto.model.PostResponse


interface PostApi {

    // get post using API Endpoint
    @GET("/img_api/")
    suspend fun getAllPost(): Response<PostResponse>


}
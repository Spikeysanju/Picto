package www.spikeysanju.picto.data.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import www.spikeysanju.picto.utils.Constants.Companion.BASE_URL

class RetrofitInstance {

companion object {

    private val  retrofit by lazy {

        // Into Logging Interceptor
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()

        // Init Retrofit
        Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    val api by lazy {

        retrofit.create(PostApi::class.java)
    }

}

}
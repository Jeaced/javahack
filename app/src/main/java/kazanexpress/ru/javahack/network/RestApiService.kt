package kazanexpress.ru.javahack.network

import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import java.util.concurrent.TimeUnit

interface RestApiService {

    companion object {

        private var INSTANCE: RestApiService? = null
        private var CLIENT_INSTANCE: OkHttpClient? = null
        private var BUILDER: OkHttpClient.Builder? = null


        private fun getClientInstance(): OkHttpClient? {
            synchronized(RestApiService::class) {
                if (CLIENT_INSTANCE == null) {


                    BUILDER = OkHttpClient.Builder()
                            .followRedirects(true)
                            .followSslRedirects(true)

                            .readTimeout(15, TimeUnit.SECONDS)
                            .connectTimeout(15, TimeUnit.SECONDS)
                            .retryOnConnectionFailure(true)
                            .pingInterval(5, TimeUnit.SECONDS)
                }

                CLIENT_INSTANCE = BUILDER!!.build()

                return CLIENT_INSTANCE
            }
        }


        fun getInstance(): RestApiService? {
            synchronized(RestApiService::class) {
                if (INSTANCE == null) {
                    val retrofit = Retrofit.Builder()
                            .addConverterFactory(GsonConverterFactory.create())
                            //.baseUrl("https://api.kazanexpress.ru/api/")
                            .baseUrl("https://google.com")
                            .client(getClientInstance()!!)
                            .build()

                    INSTANCE = retrofit.create(RestApiService::class.java)
                }

                return INSTANCE
            }
        }
    }
}
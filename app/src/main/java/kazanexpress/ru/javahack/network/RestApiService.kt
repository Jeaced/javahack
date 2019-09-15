package kazanexpress.ru.javahack.network

import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import java.util.concurrent.TimeUnit

interface RestApiService {

    @Headers("Content-Type: application/x-www-form-urlencoded")
    @GET("hi/greeting")//@POST("oauth/token?grant_type=password")
    fun signIn(@Query("username") username: String,
               @Query("password") password: String): Call<ResponseBody>

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
                            .baseUrl("http://10.91.5.98:8080/api/")
                            .client(getClientInstance()!!)
                            .build()

                    INSTANCE = retrofit.create(RestApiService::class.java)
                }

                return INSTANCE
            }
        }
    }
}
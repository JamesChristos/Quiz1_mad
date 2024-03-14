package kh.edu.rupp.dse.quiz1

import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("api/fb-profile.json")
    fun getProfileData(): Call<ProfileData>
}

package kh.edu.rupp.dse.quiz1

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var firstNameTextView: TextView
    private lateinit var lastNameTextView: TextView
    private lateinit var coverImageView: ImageView
    private lateinit var profileImageView: ImageView
    private lateinit var friendCountTextView: TextView
    private lateinit var bioTextView: TextView
    private lateinit var workPlaceTextView: TextView
    private lateinit var maritalStatusTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        firstNameTextView = findViewById(R.id.firstname)
        lastNameTextView = findViewById(R.id.lastname)
        coverImageView = findViewById(R.id.coverImage)
        profileImageView = findViewById(R.id.profileImage)
        friendCountTextView = findViewById(R.id.friendCount)
        bioTextView = findViewById(R.id.bio)
        workPlaceTextView = findViewById(R.id.workPlace)
        maritalStatusTextView = findViewById(R.id.maritalStatus)

        // Initialize Retrofit
        val retrofit = Retrofit.Builder()
            .baseUrl("https://smlp-pub.s3.ap-southeast-1.amazonaws.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        // Create the service
        val service = retrofit.create(ApiService::class.java)

        // Fetch data from the API
        val call = service.getProfileData()
        call.enqueue(object : Callback<ProfileData> {
            override fun onResponse(call: Call<ProfileData>, response: Response<ProfileData>) {
                if (response.isSuccessful) {
                    val profileData = response.body()
                    profileData?.let {
                        firstNameTextView.text = it.data.firstName
                        lastNameTextView.text = it.data.lastName
                        friendCountTextView.text = it.data.friendCount.toString()
                        bioTextView.text = it.data.bio
                        workPlaceTextView.text = it.data.workPlace
                        maritalStatusTextView.text = it.data.maritalStatus

                        // Load cover image using Picasso
                        Picasso.get()
                            .load(it.data.coverImage)
                            .into(coverImageView)

                        // Load profile image using Picasso
                        Picasso.get()
                            .load(it.data.profileImage)
                            .into(profileImageView)
                    }
                }
            }

            override fun onFailure(call: Call<ProfileData>, t: Throwable) {
                // Handle failure
            }
        })
    }
}


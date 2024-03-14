package kh.edu.rupp.dse.quiz1

data class ProfileData(
    val code: Int,
    val message: String,
    val data: Profile
)

data class Profile(
    val firstName: String,
    val lastName: String,
    val bio: String,
    val profileImage: String,
    val coverImage: String,
    val friendCount: Int,
    val job: String,
    val workPlace: String,
    val maritalStatus: String
)

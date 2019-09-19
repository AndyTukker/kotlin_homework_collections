package ee.bizware.kotlin_homework_android_app.dto

class Post(
    val author: String,
    val content: String,
    val createdTimeStamp: Int,
    //
    var likedByMe: Boolean = false,
    var commentedByMe: Boolean = false,
    var sharedByMe: Boolean = false,
    //
    var quantityOfLikes: Int = 0,
    var quantityOfComments: Int = 0,
    var quantityOfShares: Int = 0,
    //
    val address: String = "",
    val place: Coordinates = Coordinates(),
    //
    val videoUrl: String = ""
)

class Coordinates(
    val lat: Float = 0F,
    val lng: Float = 0F
)
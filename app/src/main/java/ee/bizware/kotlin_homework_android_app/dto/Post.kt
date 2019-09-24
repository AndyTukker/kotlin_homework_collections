package ee.bizware.kotlin_homework_android_app.dto

class Post(
    val id: Long,
    val author: String,
    val content: String,
    val createdTimeStamp: Long,
    val type: PostType = PostType.POST,
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
    val place: Coordinates? = null,
    //
    val link: String = "",
    //
    val source: Post? = null,
    var hidePost: Boolean = false
)

class Coordinates(
    val lat: Float = 0F,
    val lng: Float = 0F
)

enum class PostType{
    POST,
    //есть ссылка на предыдущий пост в цепочке, поле source
    REPOST,
    //есть Адрес и координаты
    EVENT,
    //есть ссылка
    VIDEO,
    //есть ссылка
    AD
}
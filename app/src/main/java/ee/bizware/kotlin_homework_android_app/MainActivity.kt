package ee.bizware.kotlin_homework_android_app

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import ee.bizware.kotlin_homework_android_app.dto.Post

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val firstPost = Post(
            author = "Братья Гамбс",
            content = "Этим полукреслом мастер Гамбс начинает новую партию мебели",
            createdTimeStamp = 1567770000,
            likedByMe = true,
            commentedByMe = true,
            sharedByMe = true,
            quantityOfLikes = 1,
            quantityOfComments = 1,
            quantityOfShares = 1)
        val currentMoment = System.currentTimeMillis()/1000
        created.text = timeInSecondsToString(currentMoment - firstPost.createdTimeStamp)
        author.text = firstPost.author
        content.text = firstPost.content
        //
        likeButton.setOnClickListener{
            firstPost.likedByMe = !firstPost.likedByMe
            firstPost.quantityOfLikes = firstPost.quantityOfLikes + ( if (firstPost.likedByMe) 1 else -1 )
            updateLike(firstPost)
        }
        //
        commentButton.setOnClickListener{
            firstPost.commentedByMe = !firstPost.commentedByMe
            firstPost.quantityOfComments = firstPost.quantityOfComments + ( if (firstPost.commentedByMe) 1 else -1 )
            updateComment(firstPost)
        }
        //
        shareButton.setOnClickListener{
            firstPost.sharedByMe = !firstPost.sharedByMe
            firstPost.quantityOfShares = firstPost.quantityOfShares + ( if (firstPost.sharedByMe) 1 else -1 )
            updateShare(firstPost)
        }
        //
        updateLike(firstPost)
        updateComment(firstPost)
        updateShare(firstPost)
        //
        if (firstPost.quantityOfShares == 0) {
            quantityOfShares.text = ""
        }
        else {
            quantityOfShares.text = firstPost.quantityOfShares.toString()
        }
        if (firstPost.sharedByMe) {
            quantityOfShares.setTextColor(Color.RED)
            shareButton.setImageResource(R.drawable.ic_share_active_24dp)
        }
    }

    private fun timeInSecondsToString(sec : Long = 0) : String {
        val oneMinute = 60
        val oneHour = oneMinute * 60
        val oneDay = oneHour * 24
        val oneMounth = oneDay * 30
        val oneYear = oneMounth * 12
        val publishedAgo: String = when {
            (sec > oneYear * 2) -> "Some years ago"
            (sec > oneYear) -> "One year ago"
            (sec >= oneMounth * 2) -> "" + (sec / oneMounth) + " months ago"
            (sec >= oneMounth) -> "One month ago"
            (sec >= oneDay * 2) -> "" + (sec / oneDay) + " days ago"
            (sec >= oneDay) -> "One day ago"
            (sec >= oneHour * 2) -> "" + (sec / oneHour) + " hours ago"
            (sec >= oneHour) -> "One hour ago"
            (sec >= oneMinute * 2) -> "" + (sec / oneMinute) + " minutes ago"
            (sec >= oneMinute) -> "One minute ago"
            else -> "less than a minute ago"
        }
        return publishedAgo
    }

    private fun updateLike(post: Post){
        likeButton.setImageResource(
            if (post.likedByMe) R.drawable.ic_favorite_active_24dp
            else R.drawable.ic_favorite_inactive_24dp
        )
        quantityOfLikes.setTextColor( if (post.likedByMe) Color.RED else Color.GRAY )
        if (post.quantityOfLikes == 0) {
            quantityOfLikes.visibility = View.GONE
        }
        else {
            quantityOfLikes.visibility = View.VISIBLE
            quantityOfLikes.text = post.quantityOfLikes.toString()
        }
    }

    private fun updateComment(post: Post){
        commentButton.setImageResource(
            if (post.commentedByMe) R.drawable.ic_comment_active_24dp
            else R.drawable.ic_comment_inactive_24dp
        )
        quantityOfComments.setTextColor( if (post.commentedByMe) Color.RED else Color.GRAY )
        if (post.quantityOfComments == 0) {
            quantityOfComments.visibility = View.GONE
        }
        else {
            quantityOfComments.visibility = View.VISIBLE
            quantityOfComments.text = post.quantityOfComments.toString()
        }
    }

    private fun updateShare(post: Post){
        shareButton.setImageResource(
            if (post.sharedByMe) R.drawable.ic_share_active_24dp
            else R.drawable.ic_share_inactive_24dp
        )
        quantityOfShares.setTextColor( if (post.sharedByMe) Color.RED else Color.GRAY )
        if (post.quantityOfShares == 0) {
            quantityOfShares.visibility = View.GONE
        }
        else {
            quantityOfShares.visibility = View.VISIBLE
            quantityOfShares.text = post.quantityOfShares.toString()
        }
    }
}

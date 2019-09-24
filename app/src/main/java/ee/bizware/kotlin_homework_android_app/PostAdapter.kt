package ee.bizware.kotlin_homework_android_app

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ee.bizware.kotlin_homework_android_app.dto.Post
import ee.bizware.kotlin_homework_android_app.dto.PostType
import kotlinx.android.synthetic.main.one_post.view.*

class PostAdapter( var list: List<Post> ) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.one_post, parent, false)
        return PostViewHolder(this, view)
    }
    override fun getItemCount(): Int {
        return list.size
    }
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        with(holder as PostViewHolder){
            bind(list[position])
        }
    }
    fun updateList() {
        list = list.filter { !it.hidden }
    }
}

class PostViewHolder(val adapter: PostAdapter, view: View) : RecyclerView.ViewHolder(view) {
    init {
        with(itemView) {
            //
            hidePost.setOnClickListener{
                if ( adapterPosition != RecyclerView.NO_POSITION ){
                    val item = adapter.list[adapterPosition]
                    item.hidden = true
                    adapter.updateList()
                    adapter.notifyDataSetChanged()
                }
            }
            //
            likeButton.setOnClickListener{
                if (adapterPosition != RecyclerView.NO_POSITION ) {
                    val item = adapter.list[adapterPosition]
                    item.likedByMe = !item.likedByMe
                    item.quantityOfLikes = item.quantityOfLikes + ( if (item.likedByMe) 1 else -1 )
                    adapter.notifyItemChanged(adapterPosition)
                }
            }
            //
            commentButton.setOnClickListener{
                if (adapterPosition != RecyclerView.NO_POSITION ) {
                    val item = adapter.list[adapterPosition]
                    item.commentedByMe = !item.commentedByMe
                    item.quantityOfComments =
                    item.quantityOfComments + (if (item.commentedByMe) 1 else -1)
                    adapter.notifyItemChanged(adapterPosition)
                }
            }
            //
            shareButton.setOnClickListener{
                if (adapterPosition != RecyclerView.NO_POSITION ) {
                    val item = adapter.list[adapterPosition]
                    item.sharedByMe = !item.sharedByMe
                    item.quantityOfShares = item.quantityOfShares + (if (item.sharedByMe) 1 else -1)
                    adapter.notifyItemChanged(adapterPosition)
                }
            }
            //
            locationButton.setOnClickListener{
                if (adapterPosition != RecyclerView.NO_POSITION ) {
                    val item = adapter.list[adapterPosition]
                    if (item.place != null) {
                        val lat = item.place.lat
                        val lng = item.place.lng
                        val intent = Intent().apply {
                            action = Intent.ACTION_VIEW
                            data = Uri.parse("geo:$lat,$lng")
                        }
                        itemView.context.startActivity(intent)
                    }
                }
            }
            //
            address.setOnClickListener{
                if (adapterPosition != RecyclerView.NO_POSITION ) {
                    val item = adapter.list[adapterPosition]
                    if (item.place != null) {
                        val lat = item.place.lat
                        val lng = item.place.lng
                        val intent = Intent().apply {
                            action = Intent.ACTION_VIEW
                            data = Uri.parse("geo:$lat,$lng")
                        }
                        itemView.context.startActivity(intent)
                    }
                }
            }
            //
            videoLogo.setOnClickListener{
                if (adapterPosition != RecyclerView.NO_POSITION ) {
                    val item = adapter.list[adapterPosition]
                    val videoUrl = item.link
                    val intent = Intent().apply {
                        action = Intent.ACTION_VIEW
                        data = Uri.parse(videoUrl)
                    }
                    itemView.context.startActivity(intent)
                }
            }
            //
            link.setOnClickListener{
                if ( adapterPosition != RecyclerView.NO_POSITION ){
                    val item = adapter.list[adapterPosition]
                    val link = Uri.parse( if ( item.type == PostType.REPOST) item.source?.link else item.link )
                    val intent = Intent(Intent.ACTION_VIEW, link)
                    itemView.context.startActivity(intent)
                }
            }
        }
    }

    fun bind(post: Post) {
        with(itemView) {
            author.text = post.author
            created.text = timeSincePublication(post.createdTimeStamp)
            address.text = post.address
            locationButton.visibility = if ( post.place == null ) View.GONE else View.VISIBLE
            videoLogo.visibility = if ( post.type == PostType.VIDEO && post.link != "") View.VISIBLE else View.GONE
            address.visibility = if ( post.type == PostType.EVENT && post.address != "" ) View.VISIBLE else View.GONE
            if ( post.type == PostType.REPOST && post.source != null ) {
                content.text = post.content + "\nRE:" + post.source.content
                link.text = post.source.link
                link.visibility = View.VISIBLE
            }
            else {
                content.text = post.content
                link.text = post.link
                link.visibility = if ( post.type == PostType.AD && post.link != "" ) View.VISIBLE else View.GONE
            }
            //
            this.likeButton.setImageResource(
                if (post.likedByMe) R.drawable.ic_favorite_active_24dp
                else R.drawable.ic_favorite_inactive_24dp
            )
            this.quantityOfLikes.setTextColor( if (post.likedByMe) Color.RED else Color.GRAY )
            if (post.quantityOfLikes == 0) {
                this.quantityOfLikes.visibility = View.GONE
            }
            else {
                this.quantityOfLikes.visibility = View.VISIBLE
                this.quantityOfLikes.text = post.quantityOfLikes.toString()
            }
            //
            this.commentButton.setImageResource(
                if (post.commentedByMe) R.drawable.ic_comment_active_24dp
                else R.drawable.ic_comment_inactive_24dp
            )
            this.quantityOfComments.setTextColor( if (post.commentedByMe) Color.RED else Color.GRAY )
            if (post.quantityOfComments == 0) {
                this.quantityOfComments.visibility = View.GONE
            }
            else {
                this.quantityOfComments.visibility = View.VISIBLE
                this.quantityOfComments.text = post.quantityOfComments.toString()
            }
            //
            this.shareButton.setImageResource(
                if (post.sharedByMe) R.drawable.ic_share_active_24dp
                else R.drawable.ic_share_inactive_24dp
            )
            this.quantityOfShares.setTextColor( if (post.sharedByMe) Color.RED else Color.GRAY )
            if (post.quantityOfShares == 0) {
                this.quantityOfShares.visibility = View.GONE
            }
            else {
                this.quantityOfShares.visibility = View.VISIBLE
                this.quantityOfShares.text = post.quantityOfShares.toString()
            }
        }
    }

    private fun timeSincePublication(timeStamp : Long = 0) : String {
        val currentMoment = System.currentTimeMillis()/1000
        val sec = currentMoment - timeStamp
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

}
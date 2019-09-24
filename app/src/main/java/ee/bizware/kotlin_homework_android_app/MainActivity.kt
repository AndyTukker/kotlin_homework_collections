package ee.bizware.kotlin_homework_android_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ee.bizware.kotlin_homework_android_app.dto.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var adapter: PostAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val adPost = Post(
            id = 4,
            author = "Kerry Tukker",
            content = "New online shop, we have a lot cool postcards!",
            createdTimeStamp = System.currentTimeMillis()/1000 - 3001,
            type = PostType.AD,
            link = "https://postcards.ee/",
            quantityOfLikes = 84,
            quantityOfShares = 19,
            quantityOfComments = 31,
            likedByMe = true,
            sharedByMe = true
        )
        val list = mutableListOf(
            Post(
                id = 5,
                author = "Polina",
                content = "My favorite store!",
                type = PostType.REPOST,
                source = adPost,
                createdTimeStamp = System.currentTimeMillis()/1000 - 67,
                quantityOfLikes = 23,
                quantityOfShares = 5
            ),
            adPost,
            Post(
                id = 3,
                author = "Собачка Лотте",
                content = "Новый мульт: Лотте и деревня изобретателей",
                createdTimeStamp = System.currentTimeMillis()/1000 - 5463,
                likedByMe = true,
                commentedByMe = true,
                sharedByMe = true,
                quantityOfLikes = 1,
                quantityOfComments = 1,
                quantityOfShares = 1,
                link = "https://www.youtube.com/watch?v=NApLB4AhaLM",
                type = PostType.VIDEO
            ),
            Post(
                id = 2,
                author = "Andy Tukker",
                content = "В пятницу состоится встреча зарегистрированных пользователей, сбор в пассажирском терминале D",
                type = PostType.EVENT,
                createdTimeStamp = 1567010000,
                quantityOfLikes = 3,
                quantityOfComments = 0,
                quantityOfShares = 1,
                address = "Passenger Terminal D, 10151 Tallin, Estonia, 10151 Tallinn",
                place = Coordinates(59.443358F, 24.767515F)
            ),
            Post( id = 1, author = "Andy Tukker", content = "Это первый пост в нашей сети!", createdTimeStamp = 1562710000)
        )

        with(container) {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = PostAdapter(list)
            //фильтр работает, но эта строка срабатывает только один раз, при старте приложения
            //adapter = PostAdapter(list.filter { !it.hidePost })
        }
    }
}

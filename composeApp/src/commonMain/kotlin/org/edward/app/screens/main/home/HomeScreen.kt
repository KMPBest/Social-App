package org.edward.app.screens.main.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Message
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.DefaultAlpha
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import org.koin.core.component.KoinComponent

data class Post(
    val username: String,
    val userAvatar: String,
    val postImage: String,
    val caption: String
)

class HomeScreen : Tab, KoinComponent {

    override val options: TabOptions
        @Composable get() = TabOptions(
            index = 0u,
            icon = rememberVectorPainter(Icons.Default.Home),
            title = "Home",
        )

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {
        val posts = remember {
            listOf(
                Post(
                    "Alice",
                    "https://randomuser.me/api/portraits/women/68.jpg",
                    "https://picsum.photos/800/400?1",
                    "Enjoying the beach 🌊"
                ),
                Post(
                    "Bob",
                    "https://randomuser.me/api/portraits/men/12.jpg",
                    "https://picsum.photos/800/400?2",
                    "Coffee and coding ☕💻"
                ),
                Post(
                    "Charlie",
                    "https://randomuser.me/api/portraits/men/45.jpg",
                    "https://picsum.photos/800/400?3",
                    "Mountain vibes 🏔️"
                )
            )
        }

        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("MySocial", fontWeight = FontWeight.Bold) },
                    actions = {
                        IconButton(onClick = { }) {
                            Icon(Icons.Default.Add, contentDescription = "Add post")
                        }
                    }
                )
            }
        ) { padding ->
            LazyColumn(
                modifier = Modifier.fillMaxSize().padding(padding)
            ) {
                item {
                    LazyRow(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp, horizontal = 12.dp)
                    ) {
                        items(posts) { post ->
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier.padding(end = 12.dp)
                            ) {
                                KamelImage(
                                    { asyncPainterResource(post.userAvatar) },
                                    contentDescription = null,
                                    modifier = Modifier
                                        .size(60.dp)
                                        .clip(CircleShape)
                                        .background(Color.Gray),
                                    alignment = Alignment.Center,
                                    contentScale = ContentScale.Fit,
                                    alpha = DefaultAlpha,
                                    contentAlignment = Alignment.Center
                                )
                                Text(
                                    text = post.username,
                                    fontSize = 12.sp,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                            }
                        }
                    }
                }

                items(posts) { post ->
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 12.dp)
                        ) {
                            KamelImage(
                                { asyncPainterResource(post.userAvatar) },
                                contentDescription = null,
                                modifier = Modifier
                                    .size(40.dp)
                                    .clip(CircleShape)
                                    .background(Color.Gray),
                                alignment = Alignment.Center,
                                contentScale = ContentScale.Fit,
                                alpha = DefaultAlpha,
                                contentAlignment = Alignment.Center,
                                onFailure = {
                                    println("Failed to load image: ${it.message}")
                                }
                            )
                            Spacer(Modifier.width(8.dp))
                            Text(post.username, fontWeight = FontWeight.Bold)
                            Spacer(Modifier.weight(1f))
                            Icon(Icons.Default.MoreVert, contentDescription = "More")
                        }

                        Spacer(Modifier.height(8.dp))

                        KamelImage(
                            { asyncPainterResource(post.postImage) },
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(250.dp)
                                .clip(RoundedCornerShape(4.dp)),
                            alignment = Alignment.Center,
                            contentScale = ContentScale.Fit,
                            alpha = DefaultAlpha,
                            contentAlignment = Alignment.Center
                        )
                        Row(
                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                Icons.Default.Favorite,
                                contentDescription = "Like",
                                tint = Color.Red
                            )
                            Spacer(Modifier.width(16.dp))
                            Icon(Icons.AutoMirrored.Filled.Message, contentDescription = "Comment")
                        }

                        Text(
                            text = post.caption,
                            modifier = Modifier.padding(horizontal = 12.dp),
                            fontSize = 14.sp
                        )
                    }
                }
            }
        }
    }
}
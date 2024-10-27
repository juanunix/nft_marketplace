package com.juansanz.nftapp.ui.home

import android.annotation.SuppressLint
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.juansanz.nftapp.R
import com.juansanz.nftapp.ui.theme.NFTMarketplaceTheme
import java.util.*

@Composable
fun CategoryCard(title: String, image: Painter) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(27.dp))
            .border(
                width = 1.dp,
                color = Color.White.copy(0.5f),
                shape = RoundedCornerShape(30.dp)
            )
            .height(186.dp)
            .width(280.dp)
    ) {
        Image(
            painter = image,
            contentDescription = "",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        Text(
            title,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 22.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 16.dp)
        )
    }
}

//@Preview
//@Composable
//fun CategoryCardPreview() {
//    NFTMarketplaceTheme {
//        CategoryCard("Art", painterResource(id = R.drawable.cardblur))
//    }
//}

@Composable
fun CollectionCard(title: String, image: Painter, likes: Int) {
    var isFavorite by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .height(216.dp)
            .width(216.dp)
            .border(
                width = 1.dp,
                color = Color.White.copy(0.5f),
                shape = RoundedCornerShape(30.dp)
            )
            .clip(RoundedCornerShape(30.dp))
            .background(Color.White.copy(0.2f))
    ) {
        Image(
            painter = image,
            contentDescription = "",
            modifier = Modifier
                .height(155.dp)
                .width(155.dp)
                .padding(10.dp)
                .border(
                    width = 1.dp,
                    color = Color.Transparent,
                    shape = RoundedCornerShape(22.dp)
                )
                .fillMaxSize()
                .clip(RoundedCornerShape(22.dp)),
            contentScale = ContentScale.Crop
        )
        Row(
            modifier = Modifier.padding(horizontal = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                title,
                color = Color.White,
                fontWeight = FontWeight.SemiBold,
                fontSize = 13.sp,
                textAlign = TextAlign.Left
            )
            Spacer(modifier = Modifier.weight(1f))
            Row(
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconToggleButton(checked = isFavorite, onCheckedChange = {
                    isFavorite = !isFavorite
                }, modifier = Modifier.size(13.dp)) {
                    Icon(
                        tint = if (isFavorite) {
                            Color.Red
                        } else {
                            Color(235, 235, 245).copy(0.6f)
                       },
                        imageVector = if (isFavorite) {
                            Icons.Filled.Favorite
                        } else {
                            Icons.Default.FavoriteBorder
                        },
                        contentDescription = "Favorite Button"
                    )
                }
                Text(
                    likes.toString(),
                    color = Color(235, 235, 245).copy(0.6f),
                    fontWeight = FontWeight.Normal,
                    fontSize = 13.sp,
                    textAlign = TextAlign.Right
                )
            }
        }
    }
}

//@Preview
//@Composable
//fun ListingCardPreview() {
//    NFTMarketplaceTheme {
//        CollectionCard("Metaverse", painterResource(id = R.drawable.cardblur), 200)
//    }
//}

@Composable
fun NFTCard(
    title: String,
    subtitle: String,
    image: Painter,
    likes: Int,
    eth: Double
) {
    var isFavorite by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .height(262.dp)
            .width(175.dp)
            .border(
                width = 1.dp,
                color = Color.White.copy(0.5f),
                shape = RoundedCornerShape(30.dp)
            )
            .clip(RoundedCornerShape(30.dp))
            .background(Color.White.copy(0.2f))
    ) {
        Image(
            painter = image,
            contentDescription = "",
            modifier = Modifier
                .height(155.dp)
                .width(155.dp)
                .padding(10.dp)
                .border(
                    width = 1.dp,
                    color = Color.Transparent,
                    shape = RoundedCornerShape(22.dp)
                )
                .fillMaxSize()
                .clip(RoundedCornerShape(22.dp)),
            contentScale = ContentScale.Crop
        )
        Column(
            Modifier
                .padding(horizontal = 10.dp)
                .padding(bottom = 10.dp)
        ) {
            Text(
                title,
                color = Color.White,
                fontWeight = FontWeight.SemiBold,
                fontSize = 13.sp,
                textAlign = TextAlign.Left
            )
            Text(
                subtitle,
                color = Color(235, 235, 245).copy(0.6f),
                fontSize = 12.sp,
                textAlign = TextAlign.Left
            )
        }
        Row(
            modifier = Modifier.padding(horizontal = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(painter = painterResource(id = R.drawable.icon_eth), contentDescription = "ETH logo", modifier = Modifier.size(13.dp))
                Text(
                    eth.toString(),
                    color = Color.White,
                    fontSize = 13.sp,
                    textAlign = TextAlign.Left
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            Row(
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconToggleButton(checked = isFavorite, onCheckedChange = {
                    isFavorite = !isFavorite
                }, modifier = Modifier.size(13.dp)) {
                    Icon(
                        tint = if (isFavorite) {
                            Color.Red
                        } else {
                            Color(235, 235, 245).copy(0.6f)
                       },
                        imageVector = if (isFavorite) {
                            Icons.Filled.Favorite
                        } else {
                            Icons.Default.FavoriteBorder
                        },
                        contentDescription = "Favorite Button"
                    )
                }
                Text(
                    likes.toString(),
                    color = Color(235, 235, 245).copy(0.6f),
                    fontWeight = FontWeight.Normal,
                    fontSize = 13.sp,
                    textAlign = TextAlign.Right
                )
            }

        }
    }
}

data class NFTCategory(
    val title: String,
    val image: Int = R.drawable.cardblur,
    val id: UUID = UUID.randomUUID()
)

data class NFTCollection(
    val title: String,
    val image: Int = R.drawable.cardblur,
    var likes: Int = 0,
    val id: UUID = UUID.randomUUID()
)

data class NFT(
    val title: String,
    val subtitle: String,
    val image: Int = R.drawable.cardblur,
    var likes: Int = 0,
    var eth: Double = 0.0,
    val id: UUID = UUID.randomUUID()
)

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeScreen() {
    val categories: List<NFTCategory> = listOf(
        NFTCategory("Music", R.drawable.card_music),
        NFTCategory("Art", R.drawable.card_art),
        NFTCategory("Virtual Worlds", R.drawable.card_vw)
    )

    val collections: List<NFTCollection> = listOf(
        NFTCollection("3D Art", R.drawable.card_3d, 123),
        NFTCollection("Abstract Art", R.drawable.card_abstract, 200),
        NFTCollection("Portrait Art", R.drawable.card_portrait, 242),
        NFTCollection("Metaverse", R.drawable.card_metaverse, 420)
    )

    val nfts: List<NFT> = listOf(
        NFT("Wave", "wav2 #5672", R.drawable.card_wave2, 5160, 0.018),
        NFT("Abstract Pink", "abstract #2538", R.drawable.card_pink, 1800, 0.906),
        NFT("Wave", "wavepi #5267", R.drawable.card_wave2, 2030, 0.26),
        NFT("Abstract23", "abstract #2038", R.drawable.card_wave2, 2060, 0.246),
        NFT("Music", "mali #7890", R.drawable.card_wave2, 1690, 0.46),
        NFT("Ball", "baalli #4890", R.drawable.card_wave2, 690, 0.03),
        NFT("Ring", "Ring #7288", R.drawable.card_wave2, 400, 0.106),
        NFT("Beer", "Beer #1238", R.drawable.card_wave2, 200, 0.26)
    )

    Scaffold(
        topBar = {
            TopAppBar(
                content = {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = "NFT Marketplace",
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold,
                        fontSize = 28.sp,
                        color = Color.White
                    )
                },
                backgroundColor = Color.Transparent,
                elevation = 0.dp
            )
        },
        backgroundColor = Color(33, 17, 52)
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            LazyRow(
                modifier = Modifier.padding(vertical = 30.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(categories) { category ->
                        CategoryCard(
                            title = category.title,
                            image = painterResource(id = category.image)
                        )
                }
            }
            Text(
                "Trending collections",
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.White
            )
            LazyRow(
                modifier = Modifier.padding(bottom = 30.dp, top = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(collections) { collection ->
                    CollectionCard(
                        title = collection.title,
                        image = painterResource(id = collection.image),
                        likes = collection.likes
                    )
                }
            }
            Text(
                "Top seller",
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.White
            )
            LazyRow(
                modifier = Modifier.padding(bottom = 60.dp, top = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(nfts) { nft ->
                    NFTCard(
                        title = nft.title,
                        subtitle = nft.subtitle,
                        image = painterResource(id = nft.image),
                        likes = nft.likes,
                        eth = nft.eth
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    NFTMarketplaceTheme {
        HomeScreen()
    }
}
package com.juansanz.nftapp.ui.stats

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Assessment
import androidx.compose.material.icons.filled.TrackChanges
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.debugInspectorInfo
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
fun CombinedTabWithCustomIndicator() {
    var tabIndex by remember { mutableStateOf(0) }
    val tabData = listOf(
        "Ranking" to Icons.Default.Assessment,
        "Activity" to Icons.Default.TrackChanges
    )
    TabRow(
        selectedTabIndex = tabIndex,
        backgroundColor = Color.Transparent,
        contentColor = Color.White,
        divider = {
            TabRowDefaults.Divider(
                thickness = 1.dp,
                color = Color(66, 34, 104)
            )
        },
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                modifier = Modifier.customTabIndicatorOffset(tabPositions[tabIndex]),
                height = 4.dp,
                color = Color(148, 103, 255)
            )
        }
    ) {
        tabData.forEachIndexed { index, pair ->
            Tab(selected = tabIndex == index, onClick = {
                tabIndex = index
            }, content = {
                 Row(
                     horizontalArrangement = Arrangement.spacedBy(8.dp),
                     modifier = Modifier.padding(
                         bottom = 16.dp
                     ),
                     verticalAlignment = Alignment.CenterVertically
                 ) {
                     Icon(imageVector = pair.second, contentDescription = null)
                     Text(
                         text = pair.first,
                         fontWeight = FontWeight.SemiBold,
                         fontSize = 17.sp,
                         color = Color.White
                     )
                 }
            })
        }
    }
}

fun Modifier.customTabIndicatorOffset(
    currentTabPosition: TabPosition
): Modifier = composed(
    inspectorInfo = debugInspectorInfo {
        name = "tabIndicatorOffset"
        value = currentTabPosition
    }
) {
    val indicatorWidth = 118.dp
    val currentTabWidth = currentTabPosition.width
    val indicatorOffset by animateDpAsState(
        targetValue = currentTabPosition.left + currentTabWidth / 2 - indicatorWidth / 2,
        animationSpec = tween(durationMillis = 1000, easing = FastOutSlowInEasing)
    )
    fillMaxWidth()
        .wrapContentSize(Alignment.BottomStart)
        .offset(x = indicatorOffset)
        .width(indicatorWidth)
}

data class Ranking(
    val title: String,
    val image: Int = R.drawable.cardblur,
    var change: Double = 0.0,
    var eth: Double = 0.0,
    val id: UUID = UUID.randomUUID()
)

@Composable
fun RankingTable(rankings: List<Ranking>) {
//    Card(
//        backgroundColor = Color.White.copy(0.5f),
//        elevation = 0.dp,
//        border = BorderStroke(1.5.dp, Color.White.copy(0.5f)),
//        shape = RoundedCornerShape(20.dp),
//        modifier = Modifier.padding(16.dp, 20.dp)
//    )

    LazyColumn(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 20.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        itemsIndexed(rankings) { index, ranking ->
            RankingRow(
                index = index,
                title = ranking.title,
                image = ranking.image,
                change = ranking.change,
                eth = ranking.eth
            )
        }
    }
}


@Composable
fun RankingRow(
    index: Int,
    title: String,
    image: Int,
    change: Double,
    eth: Double
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(
            (index + 1).toString(),
            fontSize = 15.sp,
            color = Color(235, 235, 245).copy(0.6f),
            modifier = Modifier.padding(end = 10.dp)
        )
        Image(
            painter = painterResource(id = image),
            contentDescription = "",
            Modifier
                .padding(vertical = 11.dp)
                .padding(end = 15.dp)
                .height(44.dp)
                .width(44.dp)
                .fillMaxSize()
                .clip(RoundedCornerShape(10.dp))
        )
        Column(
        ) {
            Text(
                title,
                fontWeight = FontWeight.SemiBold,
                fontSize = 17.sp,
                color = Color.White,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(
                "view info",
                fontSize = 13.sp,
                color = Color(235, 235, 245).copy(0.6f),
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        Column(
            horizontalAlignment = Alignment.End
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(bottom = 8.dp)
            ) {
                Image(painter = painterResource(id = R.drawable.icon_eth), contentDescription = "ETH logo", modifier = Modifier.size(13.dp))
                Text(
                    eth.toString(),
                    color = Color.White,
                    fontSize = 13.sp,
                    textAlign = TextAlign.Left
                )
            }
            Text(
                "$change%",
                color = if (change > 0) {
                    Color.Green
                } else {
                    Color.Red
                },
                fontSize = 13.sp
            )
        }
    }
}

@Composable
fun StatsScreen() {
    var rankings: List<Ranking> = listOf(
        Ranking("Azumi", R.drawable.ranking01, 3.99, 200055.02),
        Ranking("Hape prime", R.drawable.ranking02, 33.79, 180055.45),
        Ranking("Cryoto", R.drawable.ranking03, -6.56, 90055.62),
        Ranking("Ape Club", R.drawable.ranking04, 3.99, 88055.12),
        Ranking("Bat", R.drawable.ranking05, 3.99, 10055.06),
        Ranking("Mutant", R.drawable.ranking06, 3.99, 9095.27),
        Ranking("Metaverse", R.drawable.ranking07, 3.99, 10055.02),
        Ranking("Mountain", R.drawable.ranking08, 3.99, 8055.73),
        Ranking("Mutant Ape", R.drawable.ranking05, 3.99, 5055.73),
        Ranking("The Mountain", R.drawable.ranking10, -1.99, 1055.02)
    )

    Scaffold(
        topBar = {
            TopAppBar(
                content = {
                      Column() {
                          Text(
                              "Stats",
                              fontSize = 24.sp,
                              fontWeight = FontWeight.SemiBold,
                              color = Color.White,
                              modifier = Modifier.fillMaxWidth(),
                              textAlign = TextAlign.Center
                              )
                      }
                },
                backgroundColor = Color.Transparent,
                elevation = 0.dp,
            )
        },
        backgroundColor = Color(33, 17, 52)
    ) {
        Column(
            modifier = Modifier.padding(bottom = 30.dp)
        ) {
            CombinedTabWithCustomIndicator()
            RankingTable(rankings)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun StatsScreenPreview() {
    NFTMarketplaceTheme {
        StatsScreen()
    }
}
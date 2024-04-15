package com.example.himageapp.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.compose.AppTheme
import com.example.himageapp.R
import com.example.himageapp.data.BannerImageData
import kotlinx.coroutines.delay

@Composable
fun MainScreen(
    onCompressorClick: () -> Unit,
    onConverterClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        ImageSlideShow(images = BannerImageData.images)
        ImageSelection(
            imageResourceId = R.drawable.img_resize_ic,
            mainContent = "IMAGE COMPRESSOR",
            subContent = "Reduce your image size",
            colorPrimary = Color(0xFF01CE99),
            colorSecondary = Color(0xFFC4F4E8),
            onClick = onCompressorClick
        )
        ImageSelection(
            imageResourceId = R.drawable.img_conv_ic,
            mainContent = "CONVERT IMAGE",
            subContent = "Convert your image to other formats",
            colorPrimary = Color(0xFFF44336),
            colorSecondary = Color(0xFFFDD2C3),
            onClick = onConverterClick
        )
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ImageSlideShow(images: List<Int>, modifier: Modifier = Modifier) {
    val pagerState = rememberPagerState {
        images.size
    }

    LaunchedEffect(Unit) {
        while (true) {
            delay(2000L) // Đợi 2 giây
            val nextPage = (pagerState.currentPage + 1) % images.size
            pagerState.scrollToPage(nextPage)
        }
    }

    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.onPrimaryContainer,
        ),
        modifier = Modifier
            .fillMaxWidth().padding(bottom = 8.dp)) {
        HorizontalPager(
            modifier = Modifier
                .height(280.dp)
                .fillMaxWidth(),
            contentPadding = PaddingValues(15.dp),
            state = pagerState,
        ) { page ->
            Image(
                painter = painterResource(id = images[page % images.size]),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }

        Row(
            Modifier
                .wrapContentHeight()
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            repeat(pagerState.pageCount) { iteration ->
                val color = if (pagerState.currentPage == iteration) Color.DarkGray else Color.LightGray
                Box(
                    modifier = Modifier
                        .padding(2.dp)
                        .clip(CircleShape)
                        .background(color)
                        .size(16.dp)
                )
            }
        }
    }
}

@Composable
fun ImageSelection(
    modifier: Modifier = Modifier,
    imageResourceId: Int = R.drawable.img_resize_ic,
    mainContent: String,
    subContent: String,
    colorPrimary: Color = MaterialTheme.colorScheme.primary,
    colorSecondary: Color = MaterialTheme.colorScheme.primaryContainer,
    onClick: () -> Unit
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = colorSecondary,
        ),
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp)
            .clickable(onClick = onClick)
    ) {
        Column(
            modifier = Modifier
                .padding(12.dp),
        ) {
            Image(
                painter = painterResource(id = imageResourceId),
                contentDescription = null,
                modifier = Modifier
                    .padding(bottom = 24.dp)
                    .size(35.dp),
                colorFilter = ColorFilter.tint(colorPrimary)
            )
            Text(text = mainContent,
                style = TextStyle(
                    fontFamily = FontFamily.SansSerif,
                    fontSize = 16.sp
                ),
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 8.dp))
            Text(text = subContent,
                style = TextStyle(
                    fontFamily = FontFamily.SansSerif,
                    fontSize = 16.sp
                ),
                fontWeight = FontWeight.W400,
                modifier = Modifier.padding(bottom = 8.dp))
        }
    }

}

@Preview(
    showBackground = true,
    showSystemUi = true)
@Composable
fun ImageSlideShowPreview() {
    AppTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            color = MaterialTheme.colorScheme.onPrimary
        ) {
            MainScreen(
                onCompressorClick = {},
                onConverterClick = {}
            )
        }
    }

}
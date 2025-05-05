package com.example.mento_mentee_app.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.google.accompanist.pager.HorizontalPagerIndicator
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.mento_mentee_app.R
import androidx.compose.foundation.Image

@OptIn(ExperimentalMaterial3Api::class, ExperimentalPagerApi::class)
@Composable
fun WelcomeScreen(navController: NavController) {
    val pagerState = rememberPagerState()
    val scope = rememberCoroutineScope()

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Title
            Text(
                text = "Welcome to Mentor Mentee",
                color = Color(0xFF3F2C2C),
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )

            // Pager (Images)
            HorizontalPager(
                count = 3,
                state = pagerState,
                modifier = Modifier
                    .height(450.dp)
                    .fillMaxWidth()
            ) { page ->
                val imageRes = when (page) {
                    0 -> R.drawable.mento_mentee_picture
                    1 -> R.drawable.img_2
                    else -> R.drawable.img
                }

                Image(
                    painter = painterResource(id = imageRes),
                    contentDescription = "Pager Image $page",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 4.dp)
                        .height(250.dp)
                )

            }

            // Indicator
            HorizontalPagerIndicator(
                pagerState = pagerState,
                activeColor = Color(0xFF3F2C2C),
                inactiveColor = Color.LightGray,
                modifier = Modifier.padding(8.dp)
            )

            // Description
            Text(
                text = "Building meaningful mentorships to help you grow professionally.",
                color = Color(0xFF3F2C2C),
                fontSize = 16.sp,
                modifier = Modifier.padding(16.dp)
            )

            // Buttons
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(
                    onClick = { navController.navigate("signup") },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF3F2C2C)),
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier.weight(1f)
                ) {
                    Text(text = "Sign Up", color = Color.White)
                }

                Spacer(modifier = Modifier.width(16.dp))

                Button(
                    onClick = { navController.navigate("login") },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFF0F0)),
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier.weight(1f)
                ) {
                    Text(text = "Login", color = Color(0xFF3F2C2C))
                }
            }
        }
    }
}

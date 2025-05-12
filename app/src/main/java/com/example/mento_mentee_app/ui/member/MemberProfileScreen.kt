package com.example.mento_mentee_app.ui.member

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.mento_mentee_app.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MemberProfileScreen(navController: NavController) {
    val name = "Alex Johnson"
    val username = "@alexj"
    val occupation = "Cybersecurity"
    val location = "San Francisco, CA"
    val organization = "Tech Innovations Inc."
    val bio = "With over 10 years of experience in cybersecurity, I am passionate about sharing knowledge and guiding aspiring professionals in the field."

    Scaffold(
        topBar = {
            TopAppBar(
                title = {},
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF3F2C2C))
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(horizontal = 24.dp, vertical = 16.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Profile Image
            Surface(
                shape = CircleShape,
                color = Color(0xFFFFF4F4),
                modifier = Modifier
                    .size(100.dp)
                    .padding(8.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.profile_placeholder),
                    contentDescription = "Profile Picture",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.padding(20.dp)
                )
            }

            Spacer(modifier = Modifier.height(8.dp))
            Text(name, fontWeight = FontWeight.Bold, fontSize = 20.sp, color = Color(0xFF3F2C2C))
            Text(username, fontSize = 14.sp, color = Color(0xFF3F2C2C))
            Spacer(modifier = Modifier.height(12.dp))

            // Availability Row
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("✓ Available to Mentor", color = Color(0xFF3F2C2C))
                Spacer(modifier = Modifier.width(16.dp))
                Text("Need Mentoring", color = Color.Gray)
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Send Request Button
            Button(
                onClick = {
                    val encodedName = Uri.encode(name)
                    val encodedOccupation = Uri.encode(occupation)
                    navController.navigate("sendRequest/$encodedName/$encodedOccupation")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF3F2C2C),
                    contentColor = Color.White
                )
            ) {
                Text("Send Request", fontWeight = FontWeight.SemiBold)
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Details Section
            Column(modifier = Modifier.fillMaxWidth()) {
                Text("Bio", fontStyle = FontStyle.Italic, fontWeight = FontWeight.Bold, color = Color(0xFF3F2C2C))
                Text(bio, modifier = Modifier.padding(vertical = 4.dp))

                Spacer(modifier = Modifier.height(12.dp))

                Text("Location", fontStyle = FontStyle.Italic, fontWeight = FontWeight.Bold, color = Color(0xFF3F2C2C))
                Text(location, modifier = Modifier.padding(vertical = 4.dp))

                Spacer(modifier = Modifier.height(12.dp))

                Text("Organization", fontStyle = FontStyle.Italic, fontWeight = FontWeight.Bold, color = Color(0xFF3F2C2C))
                Text(organization, modifier = Modifier.padding(vertical = 4.dp))
            }
        }
    }
}


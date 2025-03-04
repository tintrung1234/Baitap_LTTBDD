package com.example.baitap1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.shape.RoundedCornerShape

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProfileScreen()
        }
    }
}

@Composable
fun ProfileScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 24.dp),  // Reduce space at the top
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Column(  // Ensure TopAppBar has space
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            TopAppBar()
        }

        Spacer(modifier = Modifier.height(150.dp)) // Increase space between TopAppBar and Image

        ProfilePicture()

        Spacer(modifier = Modifier.height(20.dp)) // Increase space between Image and Text

        ProfileInfo()
    }
}




@Composable
fun TopAppBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        IconButton(onClick = {},
                    modifier = Modifier.border(1.dp, Color.Gray, shape = RoundedCornerShape(8.dp))) {
            Icon(Icons.Default.KeyboardArrowLeft, contentDescription = "Back")
        }
        IconButton(onClick = {},
                    modifier = Modifier.border(1.dp, Color.Gray, shape = RoundedCornerShape(8.dp))) {
            Icon(Icons.Default.Edit, contentDescription = "Edit")
        }
    }
}

@Composable
fun ProfilePicture() {
    Image(
        painter = painterResource(id = R.drawable.image),
        contentDescription = "Profile Picture",
        modifier = Modifier
            .size(160.dp)
            .clip(CircleShape),
        contentScale = ContentScale.Crop
    )
}

@Composable
fun ProfileInfo() {
    Text(text = "Trung Tin", fontSize = 22.sp, color = Color.Black)
    Text(text = "TP Ho Chi Minh, Viet Nam", fontSize = 16.sp, color = Color.Gray)
}

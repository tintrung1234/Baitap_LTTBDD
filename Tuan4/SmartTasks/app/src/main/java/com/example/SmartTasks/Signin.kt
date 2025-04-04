package com.example.SmartTasks

import android.annotation.SuppressLint
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController


@SuppressLint("ContextCastToActivity")
@Composable
fun SigninPage(navController: NavHostController, firebaseAuthManager: FirebaseAuthManager) {
    val context = LocalContext.current
    val activity = context as? ComponentActivity
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .padding(top = 21.dp)
    )
    {
        Image(
            painterResource(R.drawable.image18),
            contentDescription = null,
            modifier = Modifier
                .size(513.dp, 354.dp)
                .align(alignment = Alignment.TopEnd),
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 60.dp, bottom = 30.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Column(
                    modifier = Modifier
                        .background(
                            color = Color(0xFFD5EDFF),
                            shape = RoundedCornerShape(5.dp)
                        )
                        .padding(10.dp, 20.dp)
                        .size(202.dp, 197.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                )
                {
                    Image(
                        painterResource(R.drawable.image16_2),
                        contentDescription = null,
                        modifier = Modifier.size(128.dp, 88.dp)
                    )
                }
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(Modifier.height(15.dp))
                    Text(
                        "SmartTasks", color = Color(0xFF2196F3),
                        fontWeight = FontWeight.Bold,
                        fontSize = 25.sp
                    )
                    Text(
                        "A simple and efficient to-do app",
                        color = Color(0xFF2196F3),
                        fontSize = 12.sp
                    )
                }
            }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    "Welcome",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
                Text("Ready to explore? Log in to get started.", fontSize = 12.sp)

                Spacer(Modifier.height(20.dp))

                Row(
                    modifier = Modifier
                        .background(
                            color = Color(0xFFD5EDFF),
                            shape = RoundedCornerShape(5.dp)
                        )
                        .size(284.dp, 50.dp)
                        .clickable {
                            Log.d("SigninPage", "Sign-in button clicked")
                            if (activity != null) {
                                firebaseAuthManager.signInWithGoogle(activity) { user ->
                                    Log.d("SigninPage", "Sign-in callback triggered: user = $user")
                                    if (user != null) {
                                        Log.d("SigninPage", "Navigating to information page")
                                        navController.navigate("information")
                                    }
                                }
                            } else {
                                Log.e(
                                    "SigninPage",
                                    "Context is not an instance of ComponentActivity"
                                )
                            }
                        },
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painterResource(R.drawable.google2),
                        contentDescription = null,
                        modifier = Modifier.size(16.dp, 20.dp)
                    )
                    Spacer(Modifier.width(10.dp))
                    Text(
                        "SIGN IN WITH GOOGLE", color = Color(0xFF130160), fontSize = 16.sp,
                        lineHeight = 16.sp
                    )
                }
            }
            Text("© UTHSmartTasks")

        }
    }
}
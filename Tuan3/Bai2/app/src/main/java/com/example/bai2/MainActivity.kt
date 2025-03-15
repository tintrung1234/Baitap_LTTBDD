package com.example.bai2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.bai2.ui.theme.Bai2Theme
import com.example.bai2.ui.theme.poppinsFontFamily
import com.example.bai2.ui.theme.righteousFontFamily
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Controller()
        }
    }
}

@Composable
fun Controller() {
    val navConTroller = rememberNavController()
    NavHost(navController = navConTroller, startDestination = "splashScreen") {
        composable("splashScreen") { SplashScreen(navConTroller) }
        composable("firstPage") { FirstPage(navConTroller) }
        composable("secondPage") { SecondPage(navConTroller) }
        composable("thirdPage") { ThirdPage(navConTroller) }
    }
}

@Composable
fun SplashScreen(navController: NavController) {
    LaunchedEffect(Unit) {
        delay(2000)
        navController.navigate("firstPage") {
            popUpTo("splashScreen") { inclusive = true } // Remove splash from back stack
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painterResource(R.drawable.logo),
                contentDescription = "Logo",
                modifier = Modifier.size(102.dp, 70.dp)
            )

            Spacer(Modifier.height(20.dp))

            Text(
                "UTH SmartTasks",
                fontFamily = righteousFontFamily,
                fontSize = 25.sp,
                color = Color(0xFF006EE9)
            )
        }
    }
}

@Composable
fun FirstPage(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
    ) {
        // Header
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row {

                Image(
                    painterResource(R.drawable.ellipse),
                    contentDescription = null,
                    modifier = Modifier.size(10.dp, 10.dp)
                )
                Spacer(Modifier.width(3.dp))
                Image(
                    painterResource(R.drawable.ellipse_null),
                    contentDescription = null,
                    modifier = Modifier.size(10.dp, 10.dp)
                )
                Spacer(Modifier.width(3.dp))
                Image(
                    painterResource(R.drawable.ellipse_null),
                    contentDescription = null,
                    modifier = Modifier.size(10.dp, 10.dp)
                )
            }


            Text(
                "Skip", color = Color(0xFF006EE9),
                textAlign = TextAlign.End
            )
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                painterResource(R.drawable.bro),
                contentDescription = null,
                Modifier.size(350.dp, 261.dp)
            )

            Spacer(Modifier.height(20.dp))

            Text(
                "Easy Time Management",
                fontFamily = poppinsFontFamily,
                fontWeight = FontWeight.Bold
            )

            Spacer(Modifier.height(20.dp))

            Text(
                "With management based on priority and daily tasks, it will give you convenience in managing and determining the tasks that must be done first ",
                fontFamily = poppinsFontFamily,
                textAlign = TextAlign.Center,
                fontSize = 15.sp
            )

        }

        Row(
            verticalAlignment = Alignment.Bottom,
            modifier = Modifier.fillMaxHeight()
        ) {
            Button(
                onClick = { navController.navigate("secondPage") },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF1A96F3),
                    contentColor = Color.White
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(53.dp)
            ) {
                Text("Next",
                    fontFamily = poppinsFontFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp)
            }
        }
    }
}

@Composable
fun SecondPage(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
    ) {
        // Header
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row {
                Image(
                    painterResource(R.drawable.ellipse_null),
                    contentDescription = null,
                    modifier = Modifier.size(10.dp, 10.dp)
                )
                Spacer(Modifier.width(3.dp))
                Image(
                    painterResource(R.drawable.ellipse),
                    contentDescription = null,
                    modifier = Modifier.size(10.dp, 10.dp)
                )
                Spacer(Modifier.width(3.dp))
                Image(
                    painterResource(R.drawable.ellipse_null),
                    contentDescription = null,
                    modifier = Modifier.size(10.dp, 10.dp)
                )
            }


            Text(
                "Skip", color = Color(0xFF006EE9),
                textAlign = TextAlign.End
            )
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                painterResource(R.drawable.bro2),
                contentDescription = null,
                Modifier.size(350.dp, 261.dp)
            )

            Spacer(Modifier.height(20.dp))

            Text(
                "Increase Work Effectiveness",
                fontFamily = poppinsFontFamily,
                fontWeight = FontWeight.Bold
            )

            Spacer(Modifier.height(20.dp))

            Text(
                "Time management and the determination of more important tasks will give your job statistics better and always improve",
                fontFamily = poppinsFontFamily,
                textAlign = TextAlign.Center,
                fontSize = 15.sp
            )

        }

        Row(
            // Chỉnh cho nút ở cuối Page
            verticalAlignment = Alignment.Bottom,
            modifier = Modifier.fillMaxHeight(),
        ) {
            Row(
                // Căn giữa theo chiều dọc hàng Button
                verticalAlignment = Alignment.CenterVertically
            ) {

                Button(
                    onClick = { navController.navigate("firstPage") },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF1A96F3)
                    ),
                    contentPadding = PaddingValues(0.dp),
                    modifier = Modifier.size(53.dp, 53.dp)
                ) {
                    Image(
                        painterResource(R.drawable.icons_back),
                        contentDescription = null,
                        modifier = Modifier.size(40.dp)
                    )
                }

                Button(
                    onClick = { navController.navigate("thirdPage") },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF1A96F3),
                        contentColor = Color.White
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(53.dp)
                        .padding(start = 20.dp)
                ) {
                    Text("Next",
                        fontFamily = poppinsFontFamily,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp)
                }
            }

        }
    }
}

@Composable
fun ThirdPage(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
    ) {
        // Header
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row {
                Image(
                    painterResource(R.drawable.ellipse_null),
                    contentDescription = null,
                    modifier = Modifier.size(10.dp, 10.dp)
                )
                Spacer(Modifier.width(3.dp))
                Image(
                    painterResource(R.drawable.ellipse_null),
                    contentDescription = null,
                    modifier = Modifier.size(10.dp, 10.dp)
                )
                Spacer(Modifier.width(3.dp))
                Image(
                    painterResource(R.drawable.ellipse),
                    contentDescription = null,
                    modifier = Modifier.size(10.dp, 10.dp)
                )
            }


            Text(
                "Skip", color = Color(0xFF006EE9),
                textAlign = TextAlign.End
            )
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                painterResource(R.drawable.bro3),
                contentDescription = null,
                Modifier.size(350.dp, 261.dp)
            )

            Spacer(Modifier.height(20.dp))

            Text(
                "Reminder Notification",
                fontFamily = poppinsFontFamily,
                fontWeight = FontWeight.Bold
            )

            Spacer(Modifier.height(20.dp))

            Text(
                "The advantage of this application is that it also provides reminders for you so you don't forget to keep doing your assignments well and according to the time you have set",
                fontFamily = poppinsFontFamily,
                textAlign = TextAlign.Center,
                fontSize = 15.sp
            )

        }

        Row(
            // Chỉnh cho nút ở cuối Page
            verticalAlignment = Alignment.Bottom,
            modifier = Modifier.fillMaxHeight(),
        ) {
            Row(
                // Căn giữa theo chiều dọc hàng Button
                verticalAlignment = Alignment.CenterVertically
            ) {

                Button(
                    onClick = { navController.navigate("secondPage") },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF1A96F3)
                    ),
                    contentPadding = PaddingValues(0.dp),
                    modifier = Modifier.size(53.dp, 53.dp)
                ) {
                    Image(
                        painterResource(R.drawable.icons_back),
                        contentDescription = null,
                        modifier = Modifier.size(40.dp)
                    )
                }

                Button(
                    onClick = {  },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF1A96F3),
                        contentColor = Color.White
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(53.dp)
                        .padding(start = 20.dp)
                ) {
                    Text("Get Started",
                        fontFamily = poppinsFontFamily,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp)
                }
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Bai2Theme {
        Controller()
    }
}
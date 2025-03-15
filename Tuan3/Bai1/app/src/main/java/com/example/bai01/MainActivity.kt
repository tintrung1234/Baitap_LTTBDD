package com.example.bai01

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.bai01.ui.theme.Bai01Theme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.*


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Bai01Theme {
                Controller()
            }
        }
    }
}

@Composable
fun Controller() {
    val navConTroller = rememberNavController()
    NavHost(navController = navConTroller, startDestination = "home") {
        composable("home") { MainScreen(navConTroller) }
        composable("secondPage") { SecondPage(navConTroller) }
        composable("thirdPage") { ThirdPage(navConTroller) }
    }
}

@Composable
fun MainScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(19.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(R.drawable.logo),
                contentDescription = null,
                modifier = Modifier
                    .padding(top = 120.dp)
                    .size(216.dp, 233.dp)
            )

            Spacer(Modifier.height(20.dp))

            Text("JetPack Compose", fontSize = 18.sp, fontWeight = FontWeight.Bold)

            Spacer(Modifier.height(20.dp))

            Text(
                "Jetpack Compose is a modern UI toolkit for building native Android applications using a declarative programming approach.",
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
                color = Color.Gray,
            )
        }

        Button(
            modifier = Modifier.padding(bottom = 20.dp),
            onClick = { navController.navigate("secondPage") },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF2196F3),
                contentColor = Color.White,
            )
        ) {
            Text(
                "I'm ready",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun SecondPage(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(19.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            "UI Components List",
            color = Color(0xFF2196F3),
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Start,
            modifier = Modifier.padding(top = 20.dp)
        )

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.Start
        ) {
            Spacer(Modifier.height(20.dp))

            Text("Display", fontSize = 17.sp, fontWeight = FontWeight.Bold)

            Spacer(Modifier.height(10.dp))

            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp),
                onClick = { navController.navigate("thirdPage") },
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(
                    Color(0xFF2196F3).copy(alpha = 0.3f),
                ),
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        "Text",
                        color = Color.Black,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text("Display Text", color = Color.Black, fontSize = 18.sp)
                }
            }

            Spacer(Modifier.height(15.dp))

            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp),
                onClick = { navController.navigate("thirdPage") },
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(
                    Color(0xFF2196F3).copy(alpha = 0.3f),
                ),
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        "Image",
                        color = Color.Black,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text("Display an image", color = Color.Black, fontSize = 18.sp)
                }
            }

            Spacer(Modifier.height(20.dp))

            Text("Input", fontSize = 17.sp, fontWeight = FontWeight.Bold)

            Spacer(Modifier.height(10.dp))

            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp),
                onClick = { navController.navigate("thirdPage") },
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(
                    Color(0xFF2196F3).copy(alpha = 0.3f),
                ),
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        "TextField",
                        color = Color.Black,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text("Input field for text", color = Color.Black, fontSize = 18.sp)
                }
            }

            Spacer(Modifier.height(15.dp))

            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp),
                onClick = { navController.navigate("thirdPage") },
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(
                    Color(0xFF2196F3).copy(alpha = 0.3f),
                ),
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        "PasswordField",
                        color = Color.Black,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text("Input field for passwords", color = Color.Black, fontSize = 18.sp)
                }
            }
            Spacer(Modifier.height(20.dp))

            Text("Layout", fontSize = 17.sp, fontWeight = FontWeight.Bold)

            Spacer(Modifier.height(10.dp))

            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp),
                onClick = { navController.navigate("thirdPage") },
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(
                    Color(0xFF2196F3).copy(alpha = 0.3f),
                ),
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        "Column",
                        color = Color.Black,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text("Arranges elements vertically", color = Color.Black, fontSize = 18.sp)
                }
            }

            Spacer(Modifier.height(15.dp))

            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp),
                onClick = { navController.navigate("thirdPage") },
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(
                    Color(0xFF2196F3).copy(alpha = 0.3f),
                ),
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        "Row",
                        color = Color.Black,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text("Arranges elements horizontally", color = Color.Black, fontSize = 18.sp)
                }
            }
        }
    }
}

@Composable
fun ThirdPage(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(19.dp)
    ) {
        Box(
            modifier = Modifier
                .padding(top = 20.dp),

        ) {
            Text(
                "Text Detail",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                color = Color(0xFF2196F3),
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
            )
            Button(
                onClick = { navController.navigate("secondPage") },
                modifier = Modifier.height(40.dp),
                colors = ButtonDefaults.buttonColors(Color.White),
            )
            {
                Text(
                    "<", fontSize = 22.sp,
                    modifier = Modifier
                        .height(40.dp),
                    color = Color(0xFF2196F3)
                )
            }
        }

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.End
        ) {
            Image(
                painter = painterResource(R.drawable.image2),
                contentDescription = null,
                modifier = Modifier
                    .size(240.dp, 354.dp)
                    .padding(top = 200.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Controller()
}
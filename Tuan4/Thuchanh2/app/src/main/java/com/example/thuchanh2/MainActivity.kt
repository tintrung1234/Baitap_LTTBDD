package com.example.thuchanh2

import android.graphics.Color.rgb
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.thuchanh2.ui.theme.Thuchanh2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Thuchanh2Theme {
                Home()
            }
        }
    }
}

@Composable
fun Home() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
    )
    {
        Image(
            painterResource(R.drawable.image18),
            contentDescription = null,
            modifier = Modifier
                .size(255.dp, 176.dp)
                .align(alignment = Alignment.TopEnd)
        )
        Column(
            modifier = Modifier.padding(horizontal = 20.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.2f),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                //Logo
                Box(
                    modifier = Modifier
                        .background(
                            color = Color(rgb(33, 150, 243)).copy(alpha = 0.1f),
                            shape = RoundedCornerShape(5.dp)
                        )
                        .padding(12.dp, 20.dp),
                )
                {
                    Image(
                        painterResource(R.drawable.image16),
                        contentDescription = null,
                        modifier = Modifier.size(60.dp, 40.dp)
                    )
                }


                Column {
                    Text("SmartTasks"
                        , fontSize = 18.sp
                        , fontWeight = FontWeight.Bold
                        , color = Color(rgb(33, 150, 243)))
                    Text(
                        "A simple and efficient to-do app", fontSize = 11.sp,
                        color = Color(rgb(33, 150, 243))
                    )
                }

                Spacer(Modifier.width(20.dp))

                Image(
                    painterResource(R.drawable.image17),
                    contentDescription = null,
                    modifier = Modifier.size(27.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Thuchanh2Theme {
        Home()
    }
}
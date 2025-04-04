package com.example.SmartTasks

import android.content.Intent
import android.graphics.Color.rgb
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController


@Composable
fun DetailPage(taskId: String, navController: NavController) {
    var task by remember { mutableStateOf<Task?>(null) }
    LaunchedEffect(taskId) {
        task = fetchTask(taskId)
    }
    val checked = remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Spacer(Modifier.height(20.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = { navController.navigate("home") },
                modifier = Modifier.size(50.dp),
                contentPadding = PaddingValues(0.dp),
                shape = RoundedCornerShape(20.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(rgb(33, 150, 243)))
            ) {
                Icon(
                    Icons.Filled.KeyboardArrowLeft,
                    contentDescription = null,
                    Modifier.size(40.dp)
                )
            }

            Text(
                "Detail",
                fontWeight = FontWeight.Bold,
                color = Color(rgb(33, 150, 243)),
                fontSize = 25.sp
            )

            Image(
                painterResource(R.drawable.image15),
                contentDescription = null,
                Modifier.size(50.dp)
            )
        }

        Spacer(Modifier.height(20.dp))

        //Title
        Text(
            "${task?.title}",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        //Discription
        Text(
            "${task?.description}",
            fontSize = 14.sp
        )

        Spacer(Modifier.height(20.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = Color(rgb(225, 187, 193)),
                    shape = RoundedCornerShape(10.dp)
                )
                .padding(10.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            //Column1
            Column {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painterResource(R.drawable.image8),
                        contentDescription = null,
                        Modifier.size(28.dp)
                    )
                    Column(
                        Modifier.padding(start = 5.dp)
                    ) {
                        Text("Category", fontSize = 8.sp)
                        Text("${task?.category}", fontWeight = FontWeight.Bold, fontSize = 8.sp)
                    }
                }
            }
            Spacer(Modifier.width(5.dp))

            //Column2
            Column {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painterResource(R.drawable.image9),
                        contentDescription = null,
                        Modifier.size(28.dp)
                    )
                    Column(
                        Modifier.padding(start = 5.dp)
                    ) {
                        Text("Status", fontSize = 8.sp)
                        Text("${task?.status}", fontSize = 8.sp, fontWeight = FontWeight.Bold)
                    }
                }
            }
            Spacer(Modifier.width(5.dp))

            //Column3
            Column {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painterResource(R.drawable.image10),
                        contentDescription = null,
                        Modifier.size(28.dp)
                    )
                    Column(
                        Modifier.padding(start = 5.dp)
                    ) {
                        Text("Priority", fontSize = 8.sp)
                        Text("${task?.priority}", fontSize = 8.sp, fontWeight = FontWeight.Bold)
                    }
                }
            }
        }

        Text(
            "Subtasks", fontSize = 18.sp, fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(vertical = 20.dp)
        )
        Column {
            task?.subtasks?.forEach { subtask ->
                Row(
                    modifier = Modifier
                        .background(
                            color = Color(0xFFE6E6E6),
                            shape = RoundedCornerShape(10.dp)
                        )
                        .fillMaxWidth()
                        .padding(10.dp)
                ) {
                    Checkbox(
                        checked = checked.value,
                        onCheckedChange = { isChecked -> checked.value = isChecked },
                        colors = CheckboxDefaults.colors(
                            checkedColor = Color.Black,
                            checkmarkColor = Color.White
                        )
                    )
                    Text("${subtask.title}")
                }
                Spacer(Modifier.height(10.dp))
            }
        }

        Text(
            "Attachments", fontSize = 18.sp, fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(vertical = 20.dp)
        )

        val context = LocalContext.current

        Column {
            task?.attachments?.forEach { attachment ->
                Log.d("Attachments", "$attachment")

                Row(
                    modifier = Modifier
                        .background(
                            color = Color(0xFFE6E6E6),
                            shape = RoundedCornerShape(10.dp)
                        )
                        .padding(10.dp)
                        .fillMaxWidth()
                        .clickable {
                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(attachment.fileUrl))
                            context.startActivity(intent)
                        }
                ) {
                    Icon(
                        painterResource(R.drawable.image11),
                        contentDescription = null,
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(Modifier.width(10.dp))
                    Text(attachment.fileName, fontSize = 18.sp)
                }

                Spacer(Modifier.height(10.dp))
            }
        }
    }
}

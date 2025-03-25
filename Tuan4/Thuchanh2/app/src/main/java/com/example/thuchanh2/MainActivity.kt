package com.example.thuchanh2

import android.content.Intent
import android.graphics.Color.rgb
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
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
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.thuchanh2.ui.theme.Thuchanh2Theme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONArray
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Thuchanh2Theme {
                ConTroller()
            }
        }
    }
}

@Composable
fun ConTroller() {
    val navConTroller = rememberNavController()
    NavHost(navController = navConTroller, startDestination = "detail/1") {
        composable("home") { Home(navConTroller) }
        composable("detail/{taskId}") { backStackEntry ->
            val taskId = backStackEntry.arguments?.getString("taskId") ?: return@composable
            DetailPage(taskId, navConTroller)
        }
    }
}

data class Task(
    val id: Int,
    val title: String,
    val description: String,
    val category: String,
    val status: String,
    val priority: String,
    val dueDate: String,
    val subtasks: List<Subtask>,
    val attachments: List<Attachment>
)

data class Subtask(
    val id: Int,
    val title: String,
    val isCompleted: Boolean
)

data class Attachment(
    val id: Int,
    val fileName: String,
    val fileUrl: String
)


object ApiClient {
    fun getTasks(): List<Task> {
        val url = URL("https://amock.io/api/researchUTH/tasks")
        val connection = url.openConnection() as HttpURLConnection
        connection.requestMethod = "GET"
        connection.connect()

        val response = connection.inputStream.bufferedReader().use { it.readText() }
        connection.disconnect()

        val jsonObject = JSONObject(response)
        val jsonArray: JSONArray = jsonObject.getJSONArray("data")

        val tasks = mutableListOf<Task>()
        for (i in 0 until jsonArray.length()) {
            val obj = jsonArray.getJSONObject(i)
            tasks.add(
                Task(
                    id = obj.getInt("id"),
                    title = obj.getString("title"),
                    description = obj.getString("description"),
                    status = obj.getString("status"),
                    dueDate = obj.getString("dueDate"),
                    priority = obj.getString("priority"),
                    subtasks = emptyList(),
                    category = obj.optString("category", "Uncategorized"),
                    attachments = emptyList()
                )
            )
        }
        return tasks
    }
}

@Composable
fun Home(navController: NavController) {
    var tasks by remember { mutableStateOf<List<Task>>(emptyList()) }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        coroutineScope.launch {
            val result = withContext(Dispatchers.IO) { ApiClient.getTasks() }
            tasks = result
        }
    }
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
                        .padding(10.dp, 20.dp),
                )
                {
                    Image(
                        painterResource(R.drawable.image16),
                        contentDescription = null,
                        modifier = Modifier.size(60.dp, 40.dp)
                    )
                }


                Column(
                    modifier = Modifier.padding(start = 5.dp)
                ) {
                    Text(
                        "SmartTasks",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(rgb(33, 150, 243))
                    )
                    Text(
                        "A simple and efficient to-do app", fontSize = 9.sp,
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


            if (tasks.isEmpty()) {
                Column(
                    modifier = Modifier
                        .background(
                            color = Color(rgb(230, 230, 230)), shape = RoundedCornerShape(10.dp)
                        )
                        .fillMaxWidth()
                        .padding(top = 40.dp, bottom = 30.dp, start = 20.dp, end = 20.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painterResource(R.drawable.image12),
                        contentDescription = null,
                        modifier = Modifier.size(112.dp)
                    )
                    Spacer(Modifier.height(20.dp))
                    Text("No Tasks Yet!", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                    Text("Stay productive—add something to do", fontSize = 12.sp)
                }
            } else {
                LazyColumn() {
                    items(tasks) { task ->
                        TaskCard(task, navController)
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                }
            }
        }

        Box(
            modifier = Modifier
                .fillMaxHeight()
                .padding(20.dp),
            contentAlignment = Alignment.BottomCenter // 🔥 Căn tất cả về đáy
        ) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(20.dp),
                elevation = CardDefaults.elevatedCardElevation(10.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Icon(
                        painter = painterResource(R.drawable.baseline_home_filled_24),
                        contentDescription = null,
                        modifier = Modifier.size(35.dp),
                        tint = Color(0xFF2196F3)
                    )
                    Icon(
                        imageVector = Icons.Filled.DateRange,
                        contentDescription = null,
                        modifier = Modifier.size(35.dp),
                        tint = Color(0xFF333333B2)
                    )
                    Spacer(modifier = Modifier.size(50.dp))
                    Icon(
                        painterResource(R.drawable.baseline_library_books_24),
                        contentDescription = null,
                        modifier = Modifier.size(35.dp),
                        tint = Color(0xFF333333B2)
                    )
                    Icon(
                        imageVector = Icons.Filled.Settings,
                        contentDescription = null,
                        modifier = Modifier.size(35.dp),
                        tint = Color(0xFF333333B2)
                    )
                }
            }


            Box(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .offset(y = (-27).dp)
                    .background(Color(0xFF2196F3), shape = RoundedCornerShape(40.dp))
                    .padding(16.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = null,
                    modifier = Modifier.size(35.dp),
                    tint = Color.White
                )
            }
        }
    }
}

@Composable
fun TaskCard(task: Task, navController: NavController) {
    val checked = remember { mutableStateOf(false) }

    val backgroundColor = when (task.priority) {
        "High" -> Color(rgb(225, 187, 193))
        "Medium" -> Color(rgb(141, 156, 11)).copy(alpha = 0.3f)
        "Low" -> Color(rgb(183, 233, 255))
        else -> Color.LightGray
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = backgroundColor,
                shape = RoundedCornerShape(8.dp)
            )
            .clickable { navController.navigate("detail/${task.id}") }
    ) {
        Row(
            modifier = Modifier.padding(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Column(
                modifier = Modifier.padding(end = 4.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Checkbox(
                    checked = checked.value,
                    onCheckedChange = { isChecked -> checked.value = isChecked },
                    colors = CheckboxDefaults.colors(
                        checkedColor = Color.Black,
                        checkmarkColor = Color.White
                    )
                )
            }
            Column {
                Text(
                    text = "${task.title}",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
                Spacer(modifier = Modifier.height(8.dp))

                Text(text = "${task.description}")


            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "${task.status}",
                fontWeight = FontWeight.Bold, fontSize = 12.sp
            )
            Text(
                text = "${task.dueDate}", color = Color.Gray, fontSize = 12.sp
            )
        }
    }
}

suspend fun fetchTask(taskId: String): Task? = withContext(Dispatchers.IO) {
    val url = URL("https://amock.io/api/researchUTH/task/$taskId")
    Log.d("TaskCard", "Fetching URL: $url")

    val connection = url.openConnection() as HttpURLConnection
    return@withContext try {
        connection.requestMethod = "GET"
        connection.connectTimeout = 5000
        connection.readTimeout = 5000

        val responseCode = connection.responseCode
        Log.d("TaskCard", "Response Code: $responseCode")

        if (responseCode == HttpURLConnection.HTTP_OK) {
            val json = connection.inputStream.bufferedReader().use { it.readText() }
            Log.d("Json", "Received JSON: $json")
            parseTask(json)
        } else {
            Log.e("TaskCard", "Failed to fetch data. Response Code: $responseCode")
            null
        }
    } catch (e: Exception) {
        Log.e("Error", "Exception: ${e.message}", e)
        null
    } finally {
        connection.disconnect()
    }
}


fun parseTask(json: String): Task {
    val jsonObject = JSONObject(json)
    val data = jsonObject.getJSONObject("data") // Extract "data" field

    val id = data.getInt("id")
    val title = data.getString("title")
    val description = data.getString("description")
    val category = data.getString("category")
    val status = data.getString("status")
    val priority = data.getString("priority")
    val dueDate = data.getString("dueDate")

    // Get Subtasks
    val subtasksJson = data.getJSONArray("subtasks")
    val subtasks = mutableListOf<Subtask>()
    for (i in 0 until subtasksJson.length()) {
        val subtaskObj = subtasksJson.getJSONObject(i)
        subtasks.add(
            Subtask(
                id = subtaskObj.getInt("id"),
                title = subtaskObj.getString("title"),
                isCompleted = subtaskObj.getBoolean("isCompleted")
            )
        )
    }

    val jsonAttachments = data.getJSONArray("attachments")
    val attachments = mutableListOf<Attachment>()

    for (i in 0 until jsonAttachments.length()) {
        val obj = jsonAttachments.getJSONObject(i)
        attachments.add(
            Attachment(
                id = obj.getInt("id"),
                fileName = obj.getString("fileName"),
                fileUrl = obj.getString("fileUrl")
            )
        )
    }

    Log.d("Parsed Attachments", attachments.toString())

    Log.d("subtask", "$subtasks")

    return Task(id, title, description, category, status, priority, dueDate, subtasks,attachments)
}


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

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Thuchanh2Theme {
        ConTroller()
    }
}
package com.example.SmartTasks

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseUser
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun information(navController: NavController, firebaseAuthManager: FirebaseAuthManager) {
    val user = remember { mutableStateOf<FirebaseUser?>(null) }
    // Fetch user details from FirebaseAuthManager
    LaunchedEffect(Unit) {
        firebaseAuthManager.getCurrentUser { fetchedUser ->
            Log.d("UserInfo", "Fetched user: $fetchedUser")
            user.value = fetchedUser
        }
    }


    Column(
        modifier = Modifier
            .background(color = Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
        ) {
            Row {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp)
                ) {
                    Button(
                        onClick = { navController.navigate("signinPage") },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2196F3)),
                        contentPadding = PaddingValues(0.dp),
                        modifier = Modifier.size(40.dp),
                        shape = RoundedCornerShape(17.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Filled.KeyboardArrowLeft,
                            contentDescription = null,
                            modifier = Modifier.size(30.dp)
                        )
                    }

                    Text(
                        "Profile",
                        color = Color(0xFF2196F3),
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .align(Alignment.Center)
                    )
                }
            }

            Spacer(Modifier.height(20.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    //picture
                    Column {

                        Column(
                            modifier = Modifier
                                .size(133.dp, 129.dp)
                                .clip(shape = CircleShape)
                                .border(2.dp, Color.Black, shape = CircleShape)
                        ) {
                            Image(
                                painterResource(R.drawable.profile),
                                contentDescription = null,
                                contentScale = ContentScale.Crop
                            )

                        }
                        Image(
                            painterResource(R.drawable.vector5),
                            contentDescription = null,
                            modifier = Modifier
                                .size(30.dp, 28.dp)
                                .align(Alignment.End)
                                .offset(x = 10.dp, y = -20.dp)
                        )
                    }

                    Spacer(Modifier.height(20.dp))

                    val text1 = remember { derivedStateOf { user.value?.displayName ?: "Unknown" } }
                    val email = remember { derivedStateOf { user.value?.email ?: "Unknown" } }

                    Text(
                        "Name",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        modifier = Modifier.align(Alignment.Start)
                    )

                    Spacer(Modifier.height(5.dp))

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(
                                2.dp,
                                color = Color(0xFF544C4C24).copy(0.14f),
                                shape = RoundedCornerShape(8.dp)
                            )
                            .padding(horizontal = 20.dp, vertical = 10.dp)
                    ) {
                        BasicTextField(
                            value = text1.value,
                            onValueChange = { text1.value },
                            textStyle = TextStyle(fontSize = 16.sp, color = Color.Black),
                            modifier = Modifier.fillMaxWidth()
                        )
                    }

                    Spacer(Modifier.height(15.dp))

                    Text(
                        "Email",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        modifier = Modifier.align(Alignment.Start)
                    )

                    Spacer(Modifier.height(5.dp))

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(
                                2.dp,
                                color = Color(0xFF544C4C24).copy(0.14f),
                                shape = RoundedCornerShape(8.dp)
                            )
                            .padding(horizontal = 20.dp, vertical = 10.dp)
                    ) {
                        BasicTextField(
                            value = email.value,
                            onValueChange = { email.value },
                            textStyle = TextStyle(fontSize = 16.sp, color = Color.Black),
                            modifier = Modifier.fillMaxWidth()
                        )
                    }

                    Spacer(Modifier.height(15.dp))

                    var selectedDate by remember { mutableStateOf(LocalDate.now()) }
                    var showDatePicker by remember { mutableStateOf(false) }
                    Text(
                        "Date of Birth",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        modifier = Modifier.align(Alignment.Start)
                    )

                    Spacer(Modifier.height(5.dp))

                    Row(
                        modifier = Modifier
                            .wrapContentSize(Alignment.TopStart)
                            .fillMaxWidth()
                            .border(
                                2.dp,
                                color = Color(0xFF544C4C24).copy(0.14f),
                                shape = RoundedCornerShape(8.dp)
                            )
                            .padding(horizontal = 20.dp, vertical = 10.dp)
                    ) {

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable(onClick = { showDatePicker = true }),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {

                            Text(
                                text = selectedDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                                color = Color.Black,
                                fontSize = 16.sp
                            )

                            Icon(
                                Icons.Filled.KeyboardArrowDown,
                                contentDescription = null,
                                modifier = Modifier.size(20.dp, 27.dp),
                                tint = Color.Black
                            )
                        }

//                }

                        if (showDatePicker) {
                            DatePickerDialog(
                                onDismissRequest = { showDatePicker = false },
                                confirmButton = {
                                    TextButton(onClick = { showDatePicker = false }) {
                                        Text("OK")
                                    }
                                }
                            ) {
                                val datePickerState = rememberDatePickerState(
                                    initialSelectedDateMillis = selectedDate.toEpochDay() * 86400000
                                )
                                DatePicker(state = datePickerState)

                                LaunchedEffect(datePickerState.selectedDateMillis) {
                                    datePickerState.selectedDateMillis?.let { millis ->
                                        selectedDate = Instant.ofEpochMilli(millis)
                                            .atZone(ZoneId.systemDefault())
                                            .toLocalDate()
                                    }
                                }
                            }
                        }
                    }
                }
                Column {
                    Button(
                        modifier = Modifier
                            .padding(top = 90.dp)
                            .size(330.dp, 50.dp),
                        onClick = { navController.navigate("signinPage") },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF2196F3),
                            contentColor = Color.White,
                        )
                    ) {
                        Text(
                            "BACK",
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp,
                            textAlign = TextAlign.Center
                        )
                    }

                    Spacer(Modifier.height(10.dp))

                    Button(
                        modifier = Modifier
                            .size(330.dp, 50.dp),
                        onClick = {
                            firebaseAuthManager.signOut()
                            navController.navigate("signinPage") {
                                popUpTo("signinPage") { inclusive = true }
                            }
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF2196F3),
                            contentColor = Color.White,
                        )
                    ) {
                        Text(
                            "Sign Out",
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }
    }
}

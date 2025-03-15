package com.example.htql_thu_vien

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.clickable
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Text
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import com.example.htql_thu_vien.ui.theme.HTQL_Thu_VienTheme
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.*


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HTQL_Thu_VienTheme {
                val navController = rememberNavController()
                MainScreen(navController)
            }
        }
    }
}

@Composable
fun MainScreen(navController: NavHostController) {
    val bookList = remember { mutableStateListOf("Sách 01", "Sách 02") }
    val employeeList = remember { mutableStateListOf("Nguyen Van A", "Tran Thi B", "Le Van C") }

    NavHost(navController, startDestination = "library_management") {
        composable("library_management") {
            LibraryManagementScreen(navController, bookList, employeeList)
        }
        composable("book_list") {
            BookListScreen(navController, bookList)
        }
        composable("employee_list") {
            EmployeeListScreen(navController, employeeList)
        }
    }
}

@Composable
fun LibraryManagementScreen(
    navController: NavHostController,
    bookList: MutableList<String>,
    employeeList: MutableList<String>
) {
    var selectedEmployee by remember { mutableStateOf(employeeList.first()) }
    var expanded by remember { mutableStateOf(false) }
    val checkedState = remember { mutableStateListOf(*Array(bookList.size) { false }) }
    val borrowedBooks = remember { mutableStateListOf<Pair<String, String>>() }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(20.dp))

            Text("Hệ thống", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Text("Quản lý Thư viện", fontSize = 20.sp, fontWeight = FontWeight.Bold)

            Spacer(modifier = Modifier.height(50.dp))

            Text("Nhân viên", fontSize = 16.sp, fontWeight = FontWeight.Bold, modifier = Modifier.align(Alignment.Start))

            Box {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    BasicTextField(
                        value = selectedEmployee,
                        onValueChange = { selectedEmployee = it },
                        readOnly = true,
                        modifier = Modifier
                            .background(Color.White, RoundedCornerShape(8.dp))
                            .border(width = 1.dp, color = Color.Gray, shape = RoundedCornerShape(8.dp))
                            .padding(horizontal = 12.dp, vertical = 8.dp)
                            .weight(1f)
                            .clickable { expanded = true }
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Button(
                        onClick = { expanded = true },
                        shape = RoundedCornerShape(4.dp),
                        modifier = Modifier
                            .width(90.dp)
                            .height(40.dp)
                    ) {
                        Text("Đổi")
                    }
                }

                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                    modifier = Modifier.align(Alignment.BottomStart)
                ) {
                    employeeList.forEach { name ->
                        DropdownMenuItem(
                            text = { Text(name) },
                            onClick = {
                                selectedEmployee = name
                                expanded = false
                            }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            Text("Danh sách sách", fontSize = 16.sp, fontWeight = FontWeight.Bold, modifier = Modifier.align(Alignment.Start))
            Spacer(modifier = Modifier.height(8.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp) // Set a fixed height for scrolling
                    .background(Color.LightGray, RoundedCornerShape(8.dp))
                    .padding(horizontal = 12.dp, vertical = 8.dp)
            ) {
                LazyColumn {
                    itemsIndexed(bookList) { index, book ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .shadow(8.dp, shape = RoundedCornerShape(50.dp))
                                .background(Color.White, RoundedCornerShape(50.dp))
                                .padding(8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Checkbox(
                                checked = checkedState[index],
                                onCheckedChange = { checkedState[index] = it },
                                colors = CheckboxDefaults.colors(
                                    checkedColor = Color(0xFFC22547),
                                    uncheckedColor = Color(0xFFC22547),
                                    checkmarkColor = Color.White
                                )
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(book)
                        }
                        Spacer(modifier = Modifier.height(4.dp))
                    }
                }
            }


            Spacer(modifier = Modifier.height(40.dp))

            Button(
                onClick = {
                    bookList.forEachIndexed { index, book ->
                        if (checkedState[index]) {
                            borrowedBooks.add(selectedEmployee to book)
                        }
                    }
                },
                shape = RoundedCornerShape(4.dp),
                modifier = Modifier.width(200.dp).height(40.dp)
            ) {
                Text("Thêm")
            }

            // Inside LibraryManagementScreen() function
            Spacer(modifier = Modifier.height(20.dp))

            Text("Danh sách mượn sách", fontSize = 16.sp, fontWeight = FontWeight.Bold, modifier = Modifier.align(Alignment.Start))
            Spacer(modifier = Modifier.height(8.dp))

            // Scrollable borrowed books list
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f) // Allows list to expand and be scrollable
                    .background(Color.LightGray, RoundedCornerShape(8.dp))
                    .padding(horizontal = 12.dp, vertical = 18.dp)
            ) {
                items(borrowedBooks) { (employee, book) ->
                    Text("$employee đã mượn $book", fontSize = 14.sp)
                    Spacer(modifier = Modifier.height(4.dp))
                }
            }

            BottomNavBar(navController)
        }
    }

@Composable
fun BottomNavBar(navController: NavHostController) {
    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route

    NavigationBar(containerColor = Color.White) {
        BottomNavItem("Quản lý", Icons.Filled.Home, "library_management", currentRoute) {
            navController.navigate("library_management") {
                popUpTo(navController.graph.startDestinationId) { saveState = true }
                launchSingleTop = true
                restoreState = true
            }
        }
        BottomNavItem("DS Sách", Icons.Filled.Book, "book_list", currentRoute) {
            navController.navigate("book_list") {
                popUpTo(navController.graph.startDestinationId) { saveState = true }
                launchSingleTop = true
                restoreState = true
            }
        }
        BottomNavItem("Nhân viên", Icons.Filled.Person, "employee_list", currentRoute) {
            navController.navigate("employee_list") {
                popUpTo(navController.graph.startDestinationId) { saveState = true }
                launchSingleTop = true
                restoreState = true
            }
        }
    }
}

@Composable
fun RowScope.BottomNavItem(label: String, icon: ImageVector, route: String, currentRoute: String?, onClick: () -> Unit) {
    NavigationBarItem(
        selected = currentRoute == route,
        onClick = onClick,
        icon = {
            Icon(
                imageVector = icon,
                contentDescription = label,
                tint = if (currentRoute == route) Color(0xFF1E88E5) else Color.Gray.copy(alpha = 0.5f)
            )
        },
        label = {
            Text(
                text = label,
                color = if (currentRoute == route) Color(0xFF1E88E5) else Color.Gray.copy(alpha = 0.5f)
            )
        }
    )
}


@Composable
fun BookListScreen(navController: NavHostController, bookList: MutableList<String>) {
    var bookName by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween) {
        Text("Danh sách Sách", fontSize = 20.sp, fontWeight = FontWeight.Bold)

        Row {
            TextField(
                value = bookName,
                onValueChange = { bookName = it },
                label = { Text("Nhập tên sách") },
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = {
                if (bookName.isNotEmpty()) {
                    bookList.add(bookName)
                    bookName = ""
                }
            }) {
                Text("Thêm")
            }
        }

        LazyColumn {
            items(bookList) { book ->
                Text(book)
            }
        }

        BottomNavBar(navController)
    }
}

@Composable
fun EmployeeListScreen(navController: NavHostController, employeeList: MutableList<String>) {
    var employeeName by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween) {
        Text("Danh sách Nhân viên", fontSize = 20.sp, fontWeight = FontWeight.Bold)

        Row {
            TextField(
                value = employeeName,
                onValueChange = { employeeName = it },
                label = { Text("Nhập tên nhân viên") },
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = {
                if (employeeName.isNotEmpty()) {
                    employeeList.add(employeeName)
                    employeeName = ""
                }
            }) {
                Text("Thêm")
            }
        }

        LazyColumn {
            items(employeeList) { employee ->
                Text(employee)
            }
        }

        BottomNavBar(navController)
    }
}


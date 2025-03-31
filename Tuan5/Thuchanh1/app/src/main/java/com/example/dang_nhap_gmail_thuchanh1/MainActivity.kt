package com.example.dang_nhap_gmail_thuchanh1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.ui.platform.LocalContext

class MainActivity : ComponentActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = FirebaseAuth.getInstance()

        setContent {
            LoginScreen(auth = auth)
        }
    }
}

@Composable
fun LoginScreen(auth: FirebaseAuth) {
    var loginStatus by remember { mutableStateOf<String?>(null) }
    var userEmail by remember { mutableStateOf<String?>(null) }
    val context = LocalContext.current
    val activity = context as ComponentActivity

    // Configure Google Sign In
    val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestIdToken("AIzaSyADXNfcTk_gcLOkLUakzGRKfX0cD4-rSeU") // Get this from Firebase Console
        .requestEmail()
        .build()

    val googleSignInClient = GoogleSignIn.getClient(activity, gso)

    // Launcher for Google Sign In
    val launcher =
        activity.registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
            try {
                val account = task.result
                val credential = GoogleAuthProvider.getCredential(account.idToken, null)
                auth.signInWithCredential(credential)
                    .addOnCompleteListener { authTask ->
                        if (authTask.isSuccessful) {
                            loginStatus = "success"
                            userEmail = auth.currentUser?.email
                        } else {
                            loginStatus = "failed"
                        }
                    }
            } catch (e: Exception) {
                loginStatus = "failed"
            }
        }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = {
                    launcher.launch(googleSignInClient.signInIntent)
                }
            ) {
                Text("Login by Gmail")
            }

            // Display login status
            loginStatus?.let { status ->
                Spacer(modifier = Modifier.height(16.dp))
                Card(
                    modifier = Modifier.padding(8.dp)
                ) {
                    Text(
                        text = when (status) {
                            "success" -> "Successful! Welcome $userEmail"
                            "failed" -> "Failed. Please try again"
                            else -> ""
                        },
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
        }
    }
}

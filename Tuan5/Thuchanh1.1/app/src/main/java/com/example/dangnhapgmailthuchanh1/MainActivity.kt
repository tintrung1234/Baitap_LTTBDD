package com.example.dangnhapgmailthuchanh1

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.google.android.gms.auth.api.signin.*
import com.google.android.gms.common.api.ApiException
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.*

class MainActivity : ComponentActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var googleSignInLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        FirebaseApp.initializeApp(this)

        super.onCreate(savedInstanceState)
        auth = FirebaseAuth.getInstance()
        Log.d("asf","asf")
        googleSignInLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            Log.d("Message", "Sign in Launcher")
            val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
            try {
                val account = task.getResult(ApiException::class.java)!!
                Log.d("Google Account Retrieved:"," ${account.email}")
                firebaseAuthWithGoogle(account.idToken!!)
            } catch (e: ApiException) {
                Log.d("Google Sign-In failed:", "${e.statusCode} - ${e.localizedMessage}")
            } catch (e: Exception) {
                Log.d("Unexpected error:" ,"${e.message}")
                e.printStackTrace()
            }
        }


        setContent {
            Log.d("Message", "setContent Called - Rendering UI")
            Mainscreen { signInWithGoogle() }
        }
    }

    private fun signInWithGoogle() {
        Log.d("Message","signin func")
        val googleSignInClient = GoogleSignIn.getClient(
            this, GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id)) // Ensure this matches the google-services.json
                .requestEmail()
                .build()
        )
        Log.d("Message", "Google Sign-In Intent Created")
        googleSignInLauncher.launch(googleSignInClient.signInIntent)
    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        println("Authenticating with Firebase...") // Debug log

        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Log.d("Login Success:"," ${auth.currentUser?.email}")
                } else {
                    Log.d("Login Failed:" ,"${task.exception?.message}")
                }
            }
    }

}

@Composable
fun Mainscreen(onGoogleSignIn: () -> Unit) {
    var showPopUp by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = {
            println("Sign-In Button Clicked") // Debugging log
            onGoogleSignIn()
        Log.d("Message","Clicked")}) {
            Text("Login with Google")
        }

        if (showPopUp) {
            Box(modifier = Modifier.size(200.dp, 200.dp)) {
                Text("This is the box")
            }
        }
    }
}

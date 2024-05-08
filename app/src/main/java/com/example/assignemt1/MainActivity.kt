package com.example.assignemt1

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.example.assignemt1.ui.theme.Assignemt1Theme
import com.example.screens.BottomNavigationBar
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.GoogleAuthProvider

class MainActivity : ComponentActivity() {
    private lateinit var googleSignInClient: GoogleSignInClient

    private val launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
            handleSignInResult(task)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id)) // ensure you have this in your strings.xml
            .requestEmail()
            .build()

        // Build a GoogleSignInClient with the options specified by gso.
        googleSignInClient = GoogleSignIn.getClient(this, gso)

        setContent {
            Assignemt1Theme {
                AuthScreen(googleSignInClient, launcher)

                if (isUserSignedIn) {
                    BottomNavigationBar()
                }
            }
        }
    }

    private fun handleSignInResult(task: Task<GoogleSignInAccount>) {
        try {
            val account = task.getResult(ApiException::class.java)
            firebaseAuthWithGoogle(account.idToken!!)
        } catch (e: ApiException) {
            Log.w("Google Sign In Error", "signInResult:failed code=" + e.statusCode)
        }
    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        FirebaseAuth.getInstance().signInWithCredential(credential).addOnCompleteListener(this) { task ->
            isUserSignedIn = task.isSuccessful
        }
    }
}

@Composable
fun AuthScreen(googleSignInClient: GoogleSignInClient, launcher: ActivityResultLauncher<Intent>) {
    var email by remember { mutableStateOf(TextFieldValue()) }
    var password by remember { mutableStateOf(TextFieldValue()) }
    var confirmPassword by remember { mutableStateOf(TextFieldValue()) }
    var isSignUp by remember { mutableStateOf(false) }
    var authMessage by remember { mutableStateOf("") }
    var showAuthMessage by remember { mutableStateOf(false) }
    val backgroundColor = Color(0xFFAFE1AF)
    val coroutineScope = rememberCoroutineScope()
    val auth = FirebaseAuth.getInstance()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = backgroundColor)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        if (showAuthMessage) {
            Text(
                text = authMessage,
                color = Color.Red,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            LaunchedEffect(authMessage) {
                kotlinx.coroutines.delay(3000) // Message shown for 3 seconds
                showAuthMessage = false
            }
        }

        TextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            singleLine = true,
            keyboardOptions = KeyboardOptions.Default,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        )
        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            singleLine = true,
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        )
        if (isSignUp) {
            TextField(
                value = confirmPassword,
                onValueChange = { confirmPassword = it },
                label = { Text("Confirm Password") },
                singleLine = true,
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp)
            )
        }

        Button(
            onClick = {
                coroutineScope.launch {
                    if (isSignUp) {
                        signUp(auth, email.text, password.text) { success, message ->
                            isUserSignedIn = success
                            authMessage = message
                            showAuthMessage = true
                        }
                    } else {
                        signIn(auth, email.text, password.text) { success, message ->
                            isUserSignedIn = success
                            authMessage = message
                            showAuthMessage = true
                        }
                    }
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(6.dp)
        ) {
            Text(if (isSignUp) "Sign Up" else "Sign In")
        }
        Button(
            onClick = {
                val signInIntent = googleSignInClient.signInIntent
                launcher.launch(signInIntent)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(6.dp)
        ) {
            Text(text = "Sign in with Google")
        }

        Spacer(modifier = Modifier.height(16.dp))

        TextButton(onClick = { isSignUp = !isSignUp }) {
            Text(if (isSignUp) "Already have an account? Sign In" else "Don't have an account? Sign Up")
        }
    }
}

var isUserSignedIn by mutableStateOf(false)

private suspend fun signUp(auth: FirebaseAuth, email: String, password: String, onResult: (Boolean, String) -> Unit) {
    try {
        auth.createUserWithEmailAndPassword(email, password).await()
        onResult(true, "Sign-up successful!")
    } catch (e: Exception) {
        onResult(false, "Failed to sign up. Please check the email and password.")
    }
}

private suspend fun signIn(auth: FirebaseAuth, email: String, password: String, onResult: (Boolean, String) -> Unit) {
    try {
        auth.signInWithEmailAndPassword(email, password).await()
        onResult(true, "Sign-in successful!")
    } catch (e: Exception) {
        onResult(false, "Failed to sign in. Please check the email and password.")
    }
}

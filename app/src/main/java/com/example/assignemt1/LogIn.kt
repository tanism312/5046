    package com.example.assignment1

    import android.content.Intent
    import androidx.activity.result.ActivityResultLauncher
    import androidx.compose.foundation.background
    import androidx.compose.foundation.layout.Arrangement
    import androidx.compose.foundation.layout.Column
    import androidx.compose.foundation.layout.Spacer
    import androidx.compose.foundation.layout.fillMaxSize
    import androidx.compose.foundation.layout.fillMaxWidth
    import androidx.compose.foundation.layout.height
    import androidx.compose.foundation.layout.padding
    import androidx.compose.foundation.text.KeyboardOptions
    import androidx.compose.material3.Button
    import androidx.compose.material3.Text
    import androidx.compose.material3.TextButton
    import androidx.compose.material3.TextField
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
    import androidx.compose.ui.text.input.PasswordVisualTransformation
    import androidx.compose.ui.text.input.TextFieldValue
    import androidx.compose.ui.unit.dp
    import com.google.android.gms.auth.api.signin.GoogleSignInClient
    import com.google.firebase.auth.FirebaseAuth
    import kotlinx.coroutines.launch
    import kotlinx.coroutines.tasks.await



    var isUserSignedIn by mutableStateOf(false)


    @Composable
    fun LogIn(googleSignInClient: GoogleSignInClient, launcher: ActivityResultLauncher<Intent>) {
        var email by remember { mutableStateOf(TextFieldValue()) }
        var isSignUp by remember { mutableStateOf(false) }
        var password by remember { mutableStateOf(TextFieldValue()) }
        var confirmPassword by remember { mutableStateOf(TextFieldValue()) }
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
                Text("Sign in with Google")
            }

            Spacer(modifier = Modifier.height(16.dp))

            TextButton(onClick = { isSignUp = !isSignUp }) {
                Text(if (isSignUp) "Already have an account? Sign In" else "Don't have an account? Sign Up")
            }
        }
    }

    suspend fun signUp(auth: FirebaseAuth, email: String, password: String, onResult: (Boolean, String) -> Unit) {
        try {
            auth.createUserWithEmailAndPassword(email, password).await()
            onResult(true, "Sign-up successful!")
        } catch (e: Exception) {
            onResult(false, "Failed to sign up. Please check the email and password.")
        }
    }

    suspend fun signIn(auth: FirebaseAuth, email: String, password: String, onResult: (Boolean, String) -> Unit) {
        try {
            auth.signInWithEmailAndPassword(email, password).await()
            onResult(true, "Sign-in successful!")
        } catch (e: Exception) {
            onResult(false, "Failed to sign in. Please check the email and password.")
        }
    }

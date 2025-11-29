package com.example.mentorify.auth.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.mentorify.R
import com.example.mentorify.R.drawable.logo_mentorify
import com.example.mentorify.ui.theme.MentorifyTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: LoginViewModel = viewModel(),
    onLoginSuccess: () -> Unit,
    onNavigateToRegister: () -> Unit,
    onNavigateToForgotPassword: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Image(
            painter = painterResource(id = logo_mentorify), // Replace with your actual logo
            contentDescription = "Mentorify Logo",
            modifier = Modifier.size(250.dp)
        )

        // Email Input
        Text(
            text = "E-mail",
            modifier = Modifier.fillMaxWidth().align(Alignment.Start)
        )
        OutlinedTextField(
            value = viewModel.email,
            onValueChange = { viewModel.onEmailChange(it) },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Password Input
        Text(
            text = "Kata Sandi",
            modifier = Modifier.fillMaxWidth().align(Alignment.Start)
        )
        OutlinedTextField(
            value = viewModel.password,
            onValueChange = { viewModel.onPasswordChange(it) },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )
        Spacer(modifier = Modifier.height(8.dp))

        // Forgot Password
        TextButton(
            onClick = onNavigateToForgotPassword,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Lupa kata sandi?", modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.End)
        }
        Spacer(modifier = Modifier.height(16.dp))

        // Login Button
        Button(
            onClick = { 
                viewModel.login()
//                if (viewModel.loginMessage == "Login successful!") {
                    onLoginSuccess()
//                }
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
        ) {
            Text("Login")
        }
        Spacer(modifier = Modifier.height(16.dp))

        // Separator
        Text("atau", style = MaterialTheme.typography.bodyMedium, color = Color.Gray)
        Spacer(modifier = Modifier.height(16.dp))

        // Google Login Button
        Button(
            onClick = { viewModel.loginWithGoogle() },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Color.White, contentColor = Color.Black)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_foreground), // Replace with actual Google logo
                contentDescription = "Google Logo",
                modifier = Modifier.size(24.dp).padding(end = 8.dp)
            )
            Text("Masuk dengan Google")
        }
        Spacer(modifier = Modifier.height(32.dp))

        // Register Text Button
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            Text("Belum punya akun? ")
            TextButton(onClick = onNavigateToRegister) {
                Text("Daftar")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    MentorifyTheme {
        LoginScreen(
            navController = rememberNavController(),
            onLoginSuccess = {},
            onNavigateToRegister = {},
            onNavigateToForgotPassword = {}
        )
    }
}
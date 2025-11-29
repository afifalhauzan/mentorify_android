package com.example.mentorify.auth.register

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.mentorify.ui.theme.MentorifyTheme

// --- Composables ---

@Composable
private fun LabeledOutlinedTextField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    keyboardType: KeyboardType,
    visualTransformation: VisualTransformation = VisualTransformation.None
) {
    Text(
        text = label,
        modifier = Modifier.fillMaxWidth().padding(bottom = 4.dp),
        style = MaterialTheme.typography.bodyLarge
    )
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier.fillMaxWidth(),
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        visualTransformation = visualTransformation,
        singleLine = true,
    )
    Spacer(modifier = Modifier.height(16.dp))
}

// --- Main Screen ---

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(
    navController: NavController,
    viewModel: RegisterViewModel = viewModel(),
    onRegisterSuccess: () -> Unit,
    onNavigateToLogin: () -> Unit,
    onBack: () -> Unit
) {
    LaunchedEffect(viewModel.registrationMessage) {
        if (viewModel.registrationMessage == "Registration successful!") {
            onRegisterSuccess()
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background,
                    titleContentColor = MaterialTheme.colorScheme.onBackground
                ),
                title = { Text("Daftar") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Kembali")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Spacer(modifier = Modifier.height(32.dp))

            // **Input Fields**
            LabeledOutlinedTextField(
                label = "Nama",
                value = viewModel.name,
                onValueChange = viewModel::onNameChange,
                keyboardType = KeyboardType.Text
            )

            LabeledOutlinedTextField(
                label = "E-mail",
                value = viewModel.email,
                onValueChange = viewModel::onEmailChange,
                keyboardType = KeyboardType.Email
            )

            LabeledOutlinedTextField(
                label = "No Telepon",
                value = viewModel.phoneNumber,
                onValueChange = viewModel::onPhoneNumberChange,
                keyboardType = KeyboardType.Phone
            )

            LabeledOutlinedTextField(
                label = "Kata Sandi",
                value = viewModel.password,
                onValueChange = viewModel::onPasswordChange,
                keyboardType = KeyboardType.Password,
                visualTransformation = PasswordVisualTransformation()
            )

            LabeledOutlinedTextField(
                label = "Konfirmasi Kata Sandi",
                value = viewModel.confirmPassword,
                onValueChange = viewModel::onConfirmPasswordChange,
                keyboardType = KeyboardType.Password,
                visualTransformation = PasswordVisualTransformation()
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Display registration message
            viewModel.registrationMessage?.let { message ->
                Text(
                    text = message,
                    color = if (message == "Registration successful!") MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.error,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }
            // **Register Button**
            Button(
                onClick = viewModel::register,
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
                enabled = viewModel.isFormValid
            ) {
                Text("Daftar")
            }
            Spacer(modifier = Modifier.height(16.dp))

            // **Login Navigation**
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    "Sudah punya akun? ",
                    color = Color.Gray,
                    style = MaterialTheme.typography.bodyMedium
                )
                TextButton(onClick = onNavigateToLogin) {
                    Text("Masuk")
                }
            }
        }
    }
}

// --- Preview ---

@Preview(showBackground = true)
@Composable
fun RegisterScreenPreview() {
    MentorifyTheme {
        RegisterScreen(
            navController = rememberNavController(),
            onRegisterSuccess = {},
            onNavigateToLogin = {},
            onBack = {}
        )
    }
}
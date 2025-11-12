// LoginScreen.kt
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun LoginScreen(navController: NavController, viewModel: UserViewModel) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Login", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(value = email, onValueChange = { email = it }, label = { Text("Email") })
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(value = password, onValueChange = { password = it }, label = { Text("Password") })
        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            // Panggil fungsi login di ViewModel untuk validasi
            val loginSuccess = viewModel.attemptLogin(email, password)
            if (loginSuccess) {
                navController.navigate("profile") {
                    popUpTo("login") { inclusive = true }
                }
            }
        }) {
            Text("Login")
        }

        TextButton(onClick = {
            // "Register" -> Navigasi ke Registrasi
            navController.navigate("registration")
        }) {
            Text("Belum punya akun? Register")
        }
    }
}
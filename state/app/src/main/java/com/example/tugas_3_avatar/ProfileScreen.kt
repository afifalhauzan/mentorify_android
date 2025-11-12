// ProfileScreen.kt
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import android.content.Intent
import androidx.compose.ui.unit.sp

@Composable
fun ProfileScreen(navController: NavController, viewModel: UserViewModel) {
    val context = LocalContext.current

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Profil Pengguna", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(32.dp))

        // Tampilkan data dari ViewModel
        Text("Nama:")
        Text(viewModel.name, fontSize = 20.sp) // Mengambil nama dari ViewModel

        Spacer(modifier = Modifier.height(16.dp))

        Text("Email:")
        Text(viewModel.email, fontSize = 20.sp) // Mengambil email dari ViewModel

        Spacer(modifier = Modifier.height(32.dp))

        Button(onClick = {
            // "Show avatar" -> Navigasi ke Avatar
            navController.navigate("avatar")
        }) {
            Text("Show Avatar")
        }
        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            // "Send Data"
            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, "Ini data profil saya.")
                type = "text/plain"
            }
            val shareIntent = Intent.createChooser(sendIntent, null)
            context.startActivity(shareIntent)
        }) {
            Text("Send Data")
        }
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedButton(onClick = {
            // "Logout" -> Kembali ke Login
            navController.navigate("login") {
                // Hapus semua backstack sampai ke awal
                popUpTo(0)
            }
        }) {
            Text("Logout")
        }
    }
}
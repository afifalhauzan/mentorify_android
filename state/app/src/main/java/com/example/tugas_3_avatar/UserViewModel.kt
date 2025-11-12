// UserViewModel.kt
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

class UserViewModel : ViewModel() {
    // Properti untuk menyimpan data registrasi.
    // 'private set' berarti hanya bisa diubah dari dalam ViewModel ini.
    var name by mutableStateOf("")
        private set
    var email by mutableStateOf("")
        private set
    var password by mutableStateOf("")
        private set

    /**
     * Fungsi untuk menyimpan data pengguna saat registrasi.
     */
    fun registerUser(newName: String, newEmail: String, newPass: String) {
        name = newName
        email = newEmail
        password = newPass
    }

    /**
     * Fungsi untuk memeriksa kredensial login.
     * Mengembalikan 'true' jika berhasil, 'false' jika gagal.
     */
    fun attemptLogin(loginEmail: String, loginPass: String): Boolean {
        // Memeriksa apakah email dan password yang dimasukkan
        // cocok dengan data yang disimpan saat registrasi.
        return loginEmail == email && loginPass == password
    }
}
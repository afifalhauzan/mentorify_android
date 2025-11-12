@file:OptIn(ExperimentalMaterial3Api::class)


import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.BottomCenter
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tugas_3_avatar.R
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController

@Composable
fun FaceAvatarScreen(navController: NavController) {
    val showNose = remember { mutableStateOf(true) }
    val showEyebrows = remember { mutableStateOf(true) }
    val showMouth = remember { mutableStateOf(true) }
    val showEyes = remember { mutableStateOf(true) }

    // Scaffold provides the structure for the TopAppBar and content
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Face Avatar") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { innerPadding -> // This padding ensures your content is not hidden by the TopAppBar
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding) // Apply the padding from the Scaffold here
                .background(Color(0xFFFFE4E1))
                .padding(16.dp), // Your original padding
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                contentAlignment = Alignment.TopCenter
            ) {
                Image(
                    painter = painterResource(id = R.drawable.face_0004),
                    contentDescription = "Body",
                    modifier = Modifier.offset(y = 50.dp)
                )
                if (showEyes.value) {
                    Image(
                        painter = painterResource(id = R.drawable.face_0003),
                        contentDescription = "Eyes",
                        modifier = Modifier.offset(y = 230.dp)
                    )
                }
                if (showNose.value) {
                    Image(
                        painter = painterResource(id = R.drawable.face_0002),
                        contentDescription = "Nose",
                        modifier = Modifier.offset(y = 290.dp)
                    )
                }
                if (showEyebrows.value) {
                    Image(
                        painter = painterResource(id = R.drawable.face_0001),
                        contentDescription = "Eyebrows",
                        modifier = Modifier.offset(y = 220.dp)
                    )
                }
                if (showMouth.value) {
                    Image(
                        painter = painterResource(id = R.drawable.face_0000),
                        contentDescription = "Mouth",
                        modifier = Modifier.offset(y = 355.dp)
                    )
                }
            }

            // This Column contains the controls (Checkboxes and Button)
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp), // Adjusted padding slightly
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    LabeledCheckbox(label = "Hidung", state = showNose)
                    LabeledCheckbox(label = "Alis", state = showEyebrows)
                }
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    LabeledCheckbox(label = "Mulut", state = showMouth)
                    LabeledCheckbox(label = "Mata", state = showEyes)
                }

                Spacer(modifier = Modifier.height(24.dp))

                val context = LocalContext.current
                Button(
                    onClick = {
                        val sendIntent: Intent = Intent().apply {
                            action = Intent.ACTION_SEND
                            putExtra(Intent.EXTRA_TEXT, "Lihat avatar keren saya!")
                            type = "text/plain"
                        }
                        val shareIntent = Intent.createChooser(sendIntent, null)
                        context.startActivity(shareIntent)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                ) {
                    Text("Send Data")
                }
            }
        }
    }
}

@Composable
fun LabeledCheckbox(label: String, state: MutableState<Boolean>) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Checkbox(
            checked = state.value,
            onCheckedChange = { isChecked ->
                state.value = isChecked
            },
            colors = CheckboxDefaults.colors(
                checkedColor = MaterialTheme.colorScheme.primary,
                uncheckedColor = Color.Gray
            )
        )
        Text(text = label, modifier = Modifier.padding(start = 4.dp))
    }
}

//
//@Preview(showBackground = true)
//@Composable
//fun DefaultPreviewFaceAvatar() {
//    Surface(modifier = Modifier.fillMaxSize()) {
//        FaceAvatarScreen()
//    }
//}
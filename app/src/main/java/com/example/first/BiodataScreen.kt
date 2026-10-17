package com.example.first

import android.util.Patterns
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext

@Composable
fun BiodataScreen() {
    val context = LocalContext.current

    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var birthDate by remember { mutableStateOf("") }
    var experience by remember { mutableStateOf("") }

    var androidSkill by remember { mutableStateOf(false) }
    var uiuxSkill by remember { mutableStateOf(false) }
    var webSkill by remember { mutableStateOf(false) }

    var showSuccess by remember { mutableStateOf(false) }
    var showError by remember { mutableStateOf(false) }
    var showExperienceMenu by remember { mutableStateOf(false) }

    val validEmail = Patterns.EMAIL_ADDRESS.matcher(email.trim()).matches()
    val formIsValid = firstName.isNotBlank() &&
            lastName.isNotBlank() &&
            validEmail &&
            birthDate.isNotBlank() &&
            experience.isNotBlank() &&
            (androidSkill || uiuxSkill || webSkill)
}

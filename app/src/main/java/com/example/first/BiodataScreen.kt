package com.example.first

import android.util.Patterns
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource

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

    val whiteColor = colorResource(R.color.white)

    val validEmail = Patterns.EMAIL_ADDRESS.matcher(email.trim()).matches()
    val formIsValid = firstName.isNotBlank() &&
            lastName.isNotBlank() &&
            validEmail &&
            birthDate.isNotBlank() &&
            experience.isNotBlank() &&
            (androidSkill || uiuxSkill || webSkill)

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.spider_background),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(dimensionResource(id = R.dimen.header_image_height))
                .align(Alignment.TopCenter)
        )

        Scaffold(
            containerColor = Color.Transparent
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(innerPadding)
                    .padding(top = dimensionResource(id = R.dimen.card_top_padding))
                    .clip(
                        RoundedCornerShape(
                            topStart = dimensionResource(id = R.dimen.corner_card_top),
                            topEnd = dimensionResource(id = R.dimen.corner_card_top)
                        )
                    )
                    .background(whiteColor)
                    .verticalScroll(rememberScrollState())
                    .padding(
                        horizontal = dimensionResource(id = R.dimen.padding_card),
                        vertical = dimensionResource(id = R.dimen.padding_card)
                    ),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
            }
        }
    }
}

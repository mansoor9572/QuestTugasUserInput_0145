package com.example.first

import android.app.DatePickerDialog
import android.util.Patterns
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import java.util.Calendar
import java.util.Locale

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

    val purple = colorResource(R.color.purple_primary)
    val fieldBorder = colorResource(R.color.field_border)
    val textColor = colorResource(R.color.text_primary)
    val secondaryText = colorResource(R.color.text_secondary)
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
                Text(
                    text = stringResource(R.string.form_title),
                    color = purple,
                    fontSize = dimensionResource(id = R.dimen.font_title).value.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.spacing_xs)))

                Text(
                    text = stringResource(R.string.form_subtitle),
                    color = secondaryText,
                    fontSize = dimensionResource(id = R.dimen.font_caption).value.sp
                )

                Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.spacing_card_title)))

                // First name & Last name
                Row(
                    horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.spacing_md)),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    OutlinedTextField(
                        value = firstName,
                        onValueChange = { firstName = it },
                        placeholder = {
                            Text(
                                stringResource(R.string.first_name),
                                fontSize = dimensionResource(id = R.dimen.font_label).value.sp
                            )
                        },
                        singleLine = true,
                        shape = RoundedCornerShape(dimensionResource(id = R.dimen.corner_field)),
                        modifier = Modifier.weight(1f),
                        colors = fieldColors()
                    )

                    OutlinedTextField(
                        value = lastName,
                        onValueChange = { lastName = it },
                        placeholder = {
                            Text(
                                stringResource(R.string.last_name),
                                fontSize = dimensionResource(id = R.dimen.font_label).value.sp
                            )
                        },
                        singleLine = true,
                        shape = RoundedCornerShape(dimensionResource(id = R.dimen.corner_field)),
                        modifier = Modifier.weight(1f),
                        colors = fieldColors()
                    )
                }

                Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.spacing_lg)))

                // Email
                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    placeholder = {
                        Text(
                            stringResource(R.string.email),
                            fontSize = dimensionResource(id = R.dimen.font_label).value.sp
                        )
                    },
                    singleLine = true,
                    shape = RoundedCornerShape(dimensionResource(id = R.dimen.corner_field)),
                    modifier = Modifier.fillMaxWidth(),
                    colors = fieldColors()
                )

                Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.spacing_lg)))

                // Birth Date & Experience Row
                Row(
                    horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.spacing_md)),
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    OutlinedButton(
                        onClick = {
                            val calendar = Calendar.getInstance()
                            DatePickerDialog(
                                context,
                                { _, year, month, day ->
                                    birthDate = String.format(
                                        Locale.US,
                                        "%02d/%02d/%04d",
                                        day,
                                        month + 1,
                                        year
                                    )
                                },
                                calendar.get(Calendar.YEAR),
                                calendar.get(Calendar.MONTH),
                                calendar.get(Calendar.DAY_OF_MONTH)
                            ).show()
                        },
                        shape = RoundedCornerShape(dimensionResource(id = R.dimen.corner_field)),
                        modifier = Modifier
                            .weight(1f)
                            .height(dimensionResource(id = R.dimen.field_height)),
                        border = BorderStroke(
                            dimensionResource(id = R.dimen.border_thin),
                            fieldBorder
                        ),
                        colors = ButtonDefaults.outlinedButtonColors(
                            contentColor = if (birthDate.isNotBlank()) textColor else secondaryText
                        )
                    ) {
                        Text(
                            text = birthDate.ifBlank {
                                stringResource(R.string.birth_date)
                            },
                            modifier = Modifier.weight(1f),
                            fontSize = dimensionResource(id = R.dimen.font_label).value.sp,
                            maxLines = 1
                        )
                        Icon(
                            imageVector = Icons.Default.CalendarMonth,
                            contentDescription = stringResource(R.string.select_date),
                            tint = textColor,
                            modifier = Modifier.size(dimensionResource(id = R.dimen.icon_calendar_size))
                        )
                    }

                    Box(modifier = Modifier.weight(1f)) {
                        OutlinedButton(
                            onClick = { showExperienceMenu = true },
                            shape = RoundedCornerShape(dimensionResource(id = R.dimen.corner_field)),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(dimensionResource(id = R.dimen.field_height)),
                            border = BorderStroke(
                                dimensionResource(id = R.dimen.border_thin),
                                fieldBorder
                            ),
                            colors = ButtonDefaults.outlinedButtonColors(
                                contentColor = if (experience.isNotBlank()) textColor else secondaryText
                            )
                        ) {
                            Text(
                                text = experience.ifBlank {
                                    stringResource(R.string.experience)
                                },
                                modifier = Modifier.weight(1f),
                                fontSize = dimensionResource(id = R.dimen.font_label).value.sp,
                                maxLines = 1
                            )
                            Icon(
                                imageVector = Icons.Default.ArrowDropDown,
                                contentDescription = stringResource(R.string.select_experience),
                                tint = textColor
                            )
                        }

                        val experienceOptions = listOf(
                            stringResource(R.string.experience_beginner),
                            stringResource(R.string.experience_intermediate),
                            stringResource(R.string.experience_advanced)
                        )

                        DropdownMenu(
                            expanded = showExperienceMenu,
                            onDismissRequest = { showExperienceMenu = false }
                        ) {
                            experienceOptions.forEach { optionText ->
                                DropdownMenuItem(
                                    text = { Text(optionText) },
                                    onClick = {
                                        experience = optionText
                                        showExperienceMenu = false
                                    }
                                )
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.spacing_xl)))

                // Skill selection header label
                Text(
                    text = stringResource(R.string.choose_skill),
                    color = textColor,
                    fontWeight = FontWeight.Bold,
                    fontSize = dimensionResource(id = R.dimen.font_label).value.sp,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.spacing_sm)))
            }
        }
    }
}

@Composable
private fun fieldColors() = OutlinedTextFieldDefaults.colors(
    focusedBorderColor = colorResource(R.color.purple_primary),
    unfocusedBorderColor = colorResource(R.color.field_border),
    focusedTextColor = colorResource(R.color.text_primary),
    unfocusedTextColor = colorResource(R.color.text_primary),
    focusedPlaceholderColor = colorResource(R.color.text_secondary),
    unfocusedPlaceholderColor = colorResource(R.color.text_secondary)
)

@Composable
private fun SkillCheckbox(
    label: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = checked,
            onCheckedChange = onCheckedChange,
            colors = CheckboxDefaults.colors(
                checkedColor = colorResource(R.color.purple_primary),
                uncheckedColor = colorResource(R.color.field_border)
            ),
            modifier = Modifier.size(dimensionResource(id = R.dimen.checkbox_size))
        )

        Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.spacing_xs)))

        Text(
            text = label,
            fontSize = dimensionResource(id = R.dimen.font_body).value.sp,
            color = colorResource(R.color.text_primary)
        )
    }
}

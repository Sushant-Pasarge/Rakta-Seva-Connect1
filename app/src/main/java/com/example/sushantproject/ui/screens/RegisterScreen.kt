package com.example.sushantproject.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.sushantproject.viewmodel.AuthenticationViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(
    onRegisterSuccess: () -> Unit,
    onLoginClick: () -> Unit,
    viewModel: AuthenticationViewModel = viewModel()
) {

    var name by remember {
        mutableStateOf("")
    }

    var email by remember {
        mutableStateOf("")
    }

    var phone by remember {
        mutableStateOf("")
    }

    var bloodGroup by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }

    val bloodGroups = listOf(

        "A+",
        "A-",
        "B+",
        "B-",
        "AB+",
        "AB-",
        "O+",
        "O-",
        "Bombay Blood Group (hh)",
        "Rh-null",
        "A1+",
        "A1-",
        "A2+",
        "A2-",
        "A1B+",
        "A1B-",
        "A2B+",
        "A2B-"
    )

    var expanded by remember {
        mutableStateOf(false)
    }

    Column(

        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
            .verticalScroll(
                rememberScrollState()
            ),

        horizontalAlignment =
            Alignment.CenterHorizontally,

        verticalArrangement =
            Arrangement.Center
    ) {

        Text(

            text = "Create Account",

            style =
                MaterialTheme.typography.displayLarge,

            color =
                MaterialTheme.colorScheme.primary
        )

        Text(

            text =
                "Join our community of lifesavers",

            style =
                MaterialTheme.typography.bodyLarge,

            color =
                MaterialTheme.colorScheme.secondary,

            modifier =
                Modifier.padding(bottom = 32.dp)
        )

        // NAME
        OutlinedTextField(

            value = name,

            onValueChange = {
                name = it
            },

            label = {
                Text("Full Name")
            },

            leadingIcon = {

                Icon(
                    Icons.Default.Person,
                    contentDescription = null
                )
            },

            modifier =
                Modifier.fillMaxWidth(),

            singleLine = true
        )

        Spacer(
            modifier = Modifier.height(16.dp)
        )

        // EMAIL
        OutlinedTextField(

            value = email,

            onValueChange = {
                email = it
            },

            label = {
                Text("Email")
            },

            leadingIcon = {

                Icon(
                    Icons.Default.Email,
                    contentDescription = null
                )
            },

            modifier =
                Modifier.fillMaxWidth(),

            keyboardOptions =
                KeyboardOptions(
                    keyboardType =
                        KeyboardType.Email
                ),

            singleLine = true
        )

        Spacer(
            modifier = Modifier.height(16.dp)
        )

        // PHONE
        OutlinedTextField(

            value = phone,

            onValueChange = {
                phone = it
            },

            label = {
                Text("Phone Number")
            },

            leadingIcon = {

                Icon(
                    Icons.Default.Phone,
                    contentDescription = null
                )
            },

            modifier =
                Modifier.fillMaxWidth(),

            keyboardOptions =
                KeyboardOptions(
                    keyboardType =
                        KeyboardType.Phone
                ),

            singleLine = true
        )

        Spacer(
            modifier = Modifier.height(16.dp)
        )

        // BLOOD GROUP
        ExposedDropdownMenuBox(

            expanded = expanded,

            onExpandedChange = {
                expanded = !expanded
            },

            modifier =
                Modifier.fillMaxWidth()
        ) {

            OutlinedTextField(

                value = bloodGroup,

                onValueChange = {},

                readOnly = true,

                label = {
                    Text("Blood Group")
                },

                leadingIcon = {

                    Icon(
                        Icons.Default.Info,
                        contentDescription = null
                    )
                },

                trailingIcon = {

                    ExposedDropdownMenuDefaults
                        .TrailingIcon(
                            expanded = expanded
                        )
                },

                modifier =
                    Modifier.menuAnchor(
                        MenuAnchorType
                            .PrimaryNotEditable,
                        true
                    ),

                colors =
                    ExposedDropdownMenuDefaults
                        .outlinedTextFieldColors()
            )

            ExposedDropdownMenu(

                expanded = expanded,

                onDismissRequest = {
                    expanded = false
                }
            ) {

                bloodGroups.forEach {

                        selectionOption ->

                    DropdownMenuItem(

                        text = {
                            Text(selectionOption)
                        },

                        onClick = {

                            bloodGroup =
                                selectionOption

                            expanded = false
                        }
                    )
                }
            }
        }

        Spacer(
            modifier = Modifier.height(16.dp)
        )

        // PASSWORD
        OutlinedTextField(

            value = password,

            onValueChange = {
                password = it
            },

            label = {
                Text("Password")
            },

            leadingIcon = {

                Icon(
                    Icons.Default.Lock,
                    contentDescription = null
                )
            },

            modifier =
                Modifier.fillMaxWidth(),

            visualTransformation =
                PasswordVisualTransformation(),

            keyboardOptions =
                KeyboardOptions(
                    keyboardType =
                        KeyboardType.Password
                ),

            singleLine = true
        )

        Spacer(
            modifier = Modifier.height(32.dp)
        )

        // REGISTER BUTTON
        Button(

            onClick = {

                viewModel.register(
                    name,
                    email,
                    phone,
                    bloodGroup,
                    password
                ) { success ->

                    if (success) {

                        // GO TO LOGIN PAGE
                        onLoginClick()
                    }
                }
            },

            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),

            shape =
                MaterialTheme.shapes.medium
        ) {

            Text(

                "REGISTER",

                style =
                    MaterialTheme.typography.titleLarge,

                fontWeight = FontWeight.Bold
            )
        }

        Spacer(
            modifier = Modifier.height(16.dp)
        )

        // LOGIN BUTTON
        TextButton(
            onClick = onLoginClick
        ) {

            Text(
                "Already have an account? Login"
            )
        }
    }
}
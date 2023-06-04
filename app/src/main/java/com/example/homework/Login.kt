package com.example.homework

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material.*
import androidx.compose.ui.focus.FocusManager
import androidx.navigation.NavHostController
import com.example.homework.component.alertDialog
import com.example.homework.compositionLocal.LocalUserViewModel
import com.example.homework.viewmodel.UserViewModel
import kotlinx.coroutines.launch


@Composable
fun LoginView(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
    titleId: Int,
    bottomTextId: Int,
    bottomTextClickFunc: () -> Unit,
    isRegister: Boolean,
) {

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        LoginBox(
            navHostController = navHostController,
            titleId = titleId,
            bottomTextId = bottomTextId,
            bottomTextClickFunc = bottomTextClickFunc,
            isRegister = isRegister,
        )
    }
}

@Composable
fun LoginBox(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
    titleId: Int,
    bottomTextId: Int,
    bottomTextClickFunc: () -> Unit,
    isRegister: Boolean,
) {

    var confirmPassword by remember { mutableStateOf("") }
    var showPassword1 by remember { mutableStateOf(false) }
    var showPassword2 by remember { mutableStateOf(false) }
    var showAlertDialog by remember { mutableStateOf(false) }
    var alertDialogType by remember { mutableStateOf(0) }
    var savePassword by remember { mutableStateOf(false) }

    val userViewModel = LocalUserViewModel.current
    val coroutineScope = rememberCoroutineScope()
    var focusManager = LocalFocusManager.current

    Column(
        modifier = modifier.fillMaxWidth(0.8f),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = titleId),
            fontSize = 30.sp,
            modifier = Modifier.padding(10.dp)
        )

        LoginInputField(
            value = if (isRegister) userViewModel.username else userViewModel.account,
            onValueChange = {
                if (isRegister) {
                    if (it.length < 10)
                        userViewModel.username = it
                    else {
                        alertDialogType = 5
                        showAlertDialog = true
                    }
                } else
                    userViewModel.account = it
            },
            labelId = if (isRegister) R.string.username else R.string.account,
            keyboardOptions = KeyboardOptions(
                keyboardType = if (isRegister) KeyboardType.Text else KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = { focusManager.moveFocus(FocusDirection.Down) }
            ),
            modifier = Modifier.padding(top = 10.dp, bottom = 5.dp),
            icon = Icons.Default.Person,
            visualTransformation = VisualTransformation.None,
            trailingIcon = null
        )

        LoginInputField(
            value = userViewModel.password,
            onValueChange = { userViewModel.password = it },
            labelId = R.string.password,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = { focusManager.clearFocus() }
            ),
            modifier = Modifier.padding(top = 10.dp, bottom = 5.dp),
            icon = Icons.Default.Lock,
            visualTransformation = if (!showPassword1) PasswordVisualTransformation() else VisualTransformation.None,
            trailingIcon = {
                Icon(
                    imageVector = if (showPassword1) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                    contentDescription = null,
                    modifier = Modifier.clickable {
                        showPassword1 = !showPassword1
                    }
                )
            }
        )
        if (isRegister) {
            LoginInputField(
                value = confirmPassword,
                onValueChange = { confirmPassword = it },
                labelId = R.string.confirm_password,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onNext = { focusManager.moveFocus(FocusDirection.Down) }
                ),
                modifier = Modifier.padding(top = 10.dp, bottom = 5.dp),
                icon = Icons.Default.Lock,
                visualTransformation = if (!showPassword2) PasswordVisualTransformation() else VisualTransformation.None,
                trailingIcon = {
                    Icon(
                        imageVector = if (showPassword2) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                        contentDescription = null,
                        modifier = Modifier.clickable {
                            showPassword2 = !showPassword2
                        }
                    )
                }
            )
        }


        Row(
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth(0.8f)
        ) {
            Checkbox(checked = savePassword, onCheckedChange = { savePassword = it })
            Text(
                text = stringResource(id = R.string.save_password),
                fontSize = 15.sp
            )
        }
        Spacer(modifier = Modifier.height(10.dp))

        Button(
            onClick = {
                if (!isRegister) {
                    if (userViewModel.account.isEmpty() || userViewModel.password.isEmpty()) {
                        showAlertDialog = true
                        alertDialogType = 1
                        return@Button
                    } else {
                        coroutineScope.launch {
                            userViewModel.login(savePassword = savePassword)
                            if (userViewModel.userInfo?.userName != null) {
                                navHostController.navigate("HomePage") {
                                    popUpTo("LoginPage") { inclusive = true }
                                }
                            } else {
                                showAlertDialog = true
                                alertDialogType = 2
                            }
                        }
                    }
                } else {    // register
                    if (userViewModel.username.isEmpty() || userViewModel.password.isEmpty() || confirmPassword.isEmpty()) {
                        showAlertDialog = true
                        alertDialogType = 1
                        return@Button
                    } else if (userViewModel.password != confirmPassword) {
                        showAlertDialog = true
                        alertDialogType = 3
                        return@Button
                    } else {
                        coroutineScope.launch {
                            userViewModel.register()
                            if (userViewModel.userInfo?.userName != null) {
                                navHostController.navigate("HomePage") {
                                    popUpTo("LoginPage") { inclusive = true }
                                }
                            } else {
                                showAlertDialog = true
                                alertDialogType = 4
                            }
                        }
                    }
                }


            },
            enabled = !userViewModel.loading
        ) {
            Text(text = stringResource(id = titleId))
            if (userViewModel.loading) {
                CircularProgressIndicator(modifier = Modifier.size(20.dp))
            }
        }

        showDialog(alertDialogType = alertDialogType, showAlertDialog = showAlertDialog) {
            showAlertDialog = false
        }

        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = stringResource(id = bottomTextId),
            fontStyle = FontStyle.Italic,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth(Alignment.End)
                .clickable(enabled = true, onClick = bottomTextClickFunc),
            textDecoration = TextDecoration.Underline
        )
    }
}

@Composable
fun LoginInputField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    labelId: Int,
    keyboardOptions: KeyboardOptions,
    keyboardActions: KeyboardActions,
    icon: ImageVector,
    visualTransformation: VisualTransformation,
    trailingIcon: @Composable (() -> Unit)?
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        singleLine = true,
        label = {
            Text(text = stringResource(id = labelId))
        },
        keyboardActions = keyboardActions,
        keyboardOptions = keyboardOptions,
        modifier = modifier,
        leadingIcon = { Icon(icon, contentDescription = null) },
        visualTransformation = visualTransformation,
        trailingIcon = trailingIcon
    )
}

@Composable
fun showDialog(
    alertDialogType: Int,
    showAlertDialog: Boolean,
    onClose: () -> Unit
) {
    // alert
    when (alertDialogType) {
        1 -> alertDialog(
            isShow = showAlertDialog,
            title = "提示",
            infoText = "请填写账号密码",
            onClose = onClose
        )
        2 -> alertDialog(
            isShow = showAlertDialog,
            title = "提示",
            infoText = "账号密码错误", onClose = onClose
        )
        3 -> alertDialog(
            isShow = showAlertDialog,
            title = "提示",
            infoText = "两次密码输入不一致", onClose = onClose
        )
        4 -> alertDialog(
            isShow = showAlertDialog,
            title = "提示",
            infoText = "注册失败", onClose = onClose
        )
        5 -> alertDialog(
            isShow = showAlertDialog,
            title = "提示",
            infoText = "用户名最长10个字符", onClose = onClose
        )
        else -> {}
    }
}


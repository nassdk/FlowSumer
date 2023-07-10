package com.nassdk.flowsummer.ui

import android.os.Handler
import android.os.Looper
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nassdk.flowsummer.core.ui.FlowSummerTheme

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun MainScreen(
    viewState: MainViewState,
    updateInputState: (String) -> Unit,
    triggerSumUpdate: () -> Unit
) {

    val focusRequester = remember {
        FocusRequester()
    }

    val keyboardController = LocalSoftwareKeyboardController.current

    Column(
        modifier = Modifier.fillMaxSize(),
        content = {

            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(all = 16.dp)
                    .focusRequester(focusRequester),
                value = viewState.inputState,
                placeholder = {
                    Text(text = "Write in a digit")
                },
                onValueChange = updateInputState,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = { triggerSumUpdate.invoke() }
                )
            )

            Text(
                text = viewState.sum,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(weight = 1f)
                    .padding(all = 16.dp),
                fontSize = 20.sp
            )

            Button(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .height(height = 48.dp),
                shape = RoundedCornerShape(16.dp),
                onClick = triggerSumUpdate,
                content = {
                    Text(text = "Calculate", fontSize = 18.sp)
                }
            )
        }
    )

    LaunchedEffect(
        key1 = Unit,
        block = {
            Handler(Looper.getMainLooper()).post {
                focusRequester.requestFocus()
                keyboardController?.show()
            }
        }
    )

    LaunchedEffect(
        key1 = viewState.sum,
        block = {
            focusRequester.freeFocus()
            keyboardController?.hide()
        }
    )
}

@Composable
@Preview(showBackground = true)
private fun MainScreenPreview() {
    FlowSummerTheme {
        MainScreen(
            viewState = MainViewState(sum = "1 3", inputState = "3"),
            updateInputState = {},
            triggerSumUpdate = {}
        )
    }
}
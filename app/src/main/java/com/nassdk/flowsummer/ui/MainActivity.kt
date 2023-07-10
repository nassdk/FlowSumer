package com.nassdk.flowsummer.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.nassdk.flowsummer.core.di.ServiceLocator
import com.nassdk.flowsummer.core.ui.FlowSummerTheme
import com.nassdk.flowsummer.core.viewmodel.ViewModelFactory

class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<MainViewModel> {
        ViewModelFactory(useCase = ServiceLocator.useCase)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FlowSummerTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                    content = {
                        MainScreen(
                            viewState = viewModel.viewState.collectAsState().value,
                            updateInputState = viewModel::updateInputState,
                            triggerSumUpdate = viewModel::calculateSum
                        )
                    }
                )
            }
        }
    }
}


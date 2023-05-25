package com.kssidll.choochoo.ui.shared

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.tooling.preview.Preview
import com.kssidll.choochoo.ui.theme.ChooChooTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OutlinedTextFieldWithExposedDropdown(
        placeholder: String,
        value: String,
        onValueChange: (String) -> Unit,
        possibleValues: List<String>,
        modifier: Modifier = Modifier,
        singleLine: Boolean = true
) {
    var expanded: Boolean by remember {
        mutableStateOf(false)
    }

    val focusManager = LocalFocusManager.current

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded },
        modifier = modifier
    ) {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = modifier.menuAnchor(),
            keyboardActions = KeyboardActions(
                onDone = {
                    expanded = false
                    focusManager.clearFocus(true)
                }
            ),
            placeholder = { Text(text = placeholder, modifier = Modifier.alpha(0.5F)) },
            singleLine = singleLine
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = {},
            modifier = modifier
        ) {
            possibleValues.filter { it.contains(value, true) }.forEach { value ->
                DropdownMenuItem(
                    text = {
                           Text(text = value)
                    }, 
                    onClick = {
                        onValueChange(value)
                        expanded = false
                        focusManager.clearFocus(true)
                    },
                    modifier = modifier
                )
            }
        }
    }
}

@Preview(group = "OutlinedTextFieldWithExposedDropdown", name = "OutlinedTextFieldWithExposedDropdown Light", apiLevel = 29,showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(group = "OutlinedTextFieldWithExposedDropdown", name = "OutlinedTextFieldWithExposedDropdown Dark", apiLevel = 29,showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun OutlinedTextFieldWithExposedDropdownPreview() {
    ChooChooTheme {
        Surface(
            color = MaterialTheme.colorScheme.background
        ) {
            OutlinedTextFieldWithExposedDropdown(
                placeholder = "Text",
                value = "",
                onValueChange = {},
                possibleValues = listOf(),
                modifier = Modifier.background(MaterialTheme.colorScheme.primary),
            )
        }
    }
}
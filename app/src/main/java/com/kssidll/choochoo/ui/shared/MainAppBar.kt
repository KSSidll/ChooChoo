package com.kssidll.choochoo.ui.shared

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kssidll.choochoo.ui.theme.ChooChooTheme

@Composable
fun MainAppBar(
    onNavigationIconClick: () -> Unit
) {
    Box(
        contentAlignment = Alignment.CenterStart,
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.primary)
    ) {

        // button that shows side panel
        IconButton(
            onClick = onNavigationIconClick
        ) {
            Icon(
                Icons.Rounded.Menu,
                contentDescription = "Toggle navigation drawer",
                modifier = Modifier
                    .size(30.dp),
                tint = MaterialTheme.colorScheme.onPrimary)
        }

        // app logo
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "ChooChoo",
                color = MaterialTheme.colorScheme.onPrimary)
        }
    }
}

@Preview(group = "MainAppBar", name = "Main App Bar Light", apiLevel = 29, showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(group = "MainAppBar", name = "Main App Bar Dark", apiLevel = 29, showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun MainAppBarPreview() {
    ChooChooTheme {
        Surface(
            color = MaterialTheme.colorScheme.background
        ) {
            MainAppBar(
                onNavigationIconClick = {}
            )
        }
    }
}
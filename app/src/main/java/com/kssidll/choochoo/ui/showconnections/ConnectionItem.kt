package com.kssidll.choochoo.ui.showconnections

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kssidll.choochoo.ui.theme.ChooChooTheme
import java.sql.Time
import java.util.Locale

@Composable
fun ConnectionItem(
    connectionData: ConnectionData,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Row ( verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = Time(connectionData.timeDeparture).toString().substring(0, 5),
                fontSize = 20.sp
            )
            Icon(
                imageVector = Icons.Default.KeyboardArrowRight,
                contentDescription = null,
                Modifier.size(25.dp)
            )
            Text(
                text = Time(connectionData.timeArrival).toString().substring(0, 5),
                fontSize = 20.sp
            )

            val price = connectionData.price.toFloat() / 100
            Text(
                text = String.format(Locale("en", "US"), "$%.2f", price),
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.End
            )
        }
        Text(
            text = "${Time(connectionData.timeArrival - connectionData.timeDeparture).toString().substring(0,2).removePrefix("0")}h" +
                    Time(connectionData.timeArrival - connectionData.timeDeparture).toString().substring(3,5),
            fontSize = 12.sp
        )
    }
}

@Preview(group = "ConnectionItem", name = "Connection Item Light", apiLevel = 29, showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(group = "ConnectionItem", name = "Connection Item Dark", apiLevel = 29, showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun ConnectionItemPreview() {
    ChooChooTheme {
        Surface(
            color = MaterialTheme.colorScheme.background
        ) {
            ConnectionItem(
                connectionData = generateConnections(1,"","")[0],
            )
        }
    }
}
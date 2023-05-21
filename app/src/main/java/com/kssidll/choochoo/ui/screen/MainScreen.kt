package com.kssidll.choochoo.ui.screen

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.kssidll.choochoo.data.fake.FakeStationRepository
import com.kssidll.choochoo.data.fake.FakeTicketRepository
import com.kssidll.choochoo.data.fake.FakeUserRepository
import com.kssidll.choochoo.ui.theme.ChooChooTheme
import com.kssidll.choochoo.ui.viewmodel.MainActivityViewModel

@Composable
fun MainScreen(navController: NavController, viewModel: MainActivityViewModel) {
    Text(text = "Hello Android!")
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    val viewModel = MainActivityViewModel(FakeUserRepository(), FakeTicketRepository(), FakeStationRepository())

    ChooChooTheme {
        MainScreen(rememberNavController(), viewModel)
    }
}
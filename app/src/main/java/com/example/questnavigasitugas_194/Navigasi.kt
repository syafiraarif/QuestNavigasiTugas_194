package com.example.questnavigasitugas_194

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.questnavigasitugas_194.view.FormInput
import com.example.questnavigasitugas_194.view.Home
import com.example.questnavigasitugas_194.view.ListPeserta

enum class Navigasi {
    Home,
    Daftar,
    Formulir
}

@OptIn (ExperimentalMaterial3Api::class)
@Composable

fun NavigasiApp(
    navController: NavHostController = rememberNavController()
) {
    Scaffold { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Navigasi.Home.name,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(route = Navigasi.Home.name) {
                Home(
                    OnMasukBtnClick = {
                        navController.navigate(Navigasi.Daftar.name)
                    }
                )
            }

            composable(route = Navigasi.Daftar.name) {
                ListPeserta(
                    OnFormulirClick = {
                        navController.navigate(Navigasi.Formulir.name)
                    },
                    OnBerandaClick = {
                        cancelAndBackToHome(navController)
                    }
                )
            }

            composable(route = Navigasi.Formulir.name) {
                FormInput(
                    OnBackClick = {
                        navController.popBackStack(route = Navigasi.Daftar.name, inclusive = false)
                    },
                    OnSubmitClick = {
                        // Setelah submit, balik ke halaman daftar peserta
                        navController.popBackStack(route = Navigasi.Daftar.name, inclusive = false)
                    }
                )
            }
        }
    }
}

private fun cancelAndBackToHome(
    navController: NavHostController
) {
    navController.popBackStack(route = Navigasi.Home.name, inclusive = false)
}
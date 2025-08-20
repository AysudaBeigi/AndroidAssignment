package ir.miare.androidcodechallenge.presentation.navigation

import android.app.Activity
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ir.miare.androidcodechallenge.presentation.ui.PlayerListScreen
import ir.miare.androidcodechallenge.presentation.ui.FollowedPlayerListScreen
import ir.miare.androidcodechallenge.presentation.viewmodel.AuthorizeViewModel
import ir.miare.androidcodechallenge.presentation.viewmodel.RepositoryViewModel
import org.koin.androidx.compose.getViewModel

private const val CODE = "code"

@Composable
internal fun FootballNavGraph() {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
    ) {
        val navController = rememberNavController()
        NavHost(
            navController = navController,
            startDestination = FootballNavigation.AuthorizeScreen.createRoute()
        ) {
            composable(route = FootballNavigation.AuthorizeScreen.createRoute()) {
                val context = LocalContext.current
                val currentIntent = (context as Activity).intent
                val authorizeViewModel = getViewModel<AuthorizeViewModel>()
                LaunchedEffect(currentIntent.data) {
                    currentIntent.data?.let {
                        getTokenAndHandleDeepLink(
                            uri = it,
                            authorizeViewModel = authorizeViewModel,
                            navController = navController,
                        )
                    }
                }
                PlayerListScreen(onAuthorizeClick = remember(context) {
                    {
                        authorizeViewModel.authorize(context = context)
                    }
                })
            }
            composable(route = FootballNavigation.RepositoryListScreen.createRoute()) {
                val viewModel = getViewModel<RepositoryViewModel>()
                val state = viewModel.state.collectAsStateWithLifecycle().value
                val keyword = remember {
                    mutableStateOf("")
                }
                FollowedPlayerListScreen(
                    keyword = keyword.value,
                    modifier = Modifier,
                    loadableRepositories = state.loadableRepositories,
                    onSearchValueChange = remember(keyword) {
                        {
                            keyword.value = it
                            viewModel.searchRepository(keyword = keyword.value)
                        }
                    },
                )
            }
        }
    }
}


private fun getTokenAndHandleDeepLink(
    uri: Uri,
    authorizeViewModel: AuthorizeViewModel,
    navController: NavHostController
) {
    uri.getQueryParameter(CODE)?.let {
        authorizeViewModel.getAccessTokenAndSave(code = it)
        navController.navigate(FootballNavigation.RepositoryListScreen.createRoute())
    }
}

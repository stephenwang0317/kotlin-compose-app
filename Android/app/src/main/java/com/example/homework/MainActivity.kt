package com.example.homework

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.LocalFireDepartment
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.homework.component.ChangeInfo
import com.example.homework.compositionLocal.LocalNavController
import com.example.homework.compositionLocal.LocalUserViewModel

import com.example.homework.ui.theme.HomeWorkTheme
import com.example.homework.viewmodel.UserViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController


class MainActivity : ComponentActivity() {

    @ExperimentalMaterialApi
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
//        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            Navigator()
        }
    }

    override fun onDestroy() {
        super.onDestroy()

    }
}

sealed class Screen(val route: String, @StringRes val textId: Int, val icon: ImageVector) {
    object Home : Screen("HomePage", R.string.main_page, Icons.Default.Home)
    object Hot : Screen("HotPage", R.string.hot_page, Icons.Outlined.LocalFireDepartment)
    object Myself : Screen("MyselfPage", R.string.myself_page, Icons.Default.AccountCircle)
}


@ExperimentalMaterialApi
@Composable
fun Navigator() {
    CompositionLocalProvider(
        LocalUserViewModel provides UserViewModel(LocalContext.current),
        LocalNavController provides rememberNavController()
    ) {
        val navController = LocalNavController.current
        val userViewModel = LocalUserViewModel.current
        NavHost(navController = navController, startDestination = "LoginPage") {
            composable("LoginPage") {

                if (userViewModel.logged) {
                    Log.i("===============", "已登录")
                    HomePage()
                } else {
                    Log.i("===============", "未登录")
                    LoginPage(
                        navHostController = navController,
                    )
                }
            }
            composable("RegisterPage") {
                RegisterPage(navHostController = navController)
            }
            composable("HomePage") { HomePage() }
            composable("HotPage") { HotPage() }
            composable("NewArticlePage") { NewArticlePage() }
            composable(
                route = "DetailArticle/{art_id}",
                arguments = listOf(navArgument("art_id") { type = NavType.IntType })
            ) {
                DetailArticle(art_id = it.arguments?.getInt("art_id"))
            }
            composable("MyArticle") { ListOfArticles() }
            composable("MyLike") { ListOfArticles(title = "我的点赞") }
            composable("MyComment") { ListOfComments() }
            composable("ChangeInfo") { ChangeInfo() }
        }
    }
}


@Composable
fun LoginPage(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
) {
    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(
        color = Color.Transparent,
        darkIcons = MaterialTheme.colors.isLight
    )

    HomeWorkTheme {
        Surface {
            LoginView(
                navHostController = navHostController,
                titleId = R.string.login,
                bottomTextId = R.string.goto_register,
                bottomTextClickFunc = { navHostController.navigate("RegisterPage") },
                isRegister = false,
            )
        }
    }
}

@Composable
fun RegisterPage(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
) {
    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(
        color = Color.Transparent,
        darkIcons = MaterialTheme.colors.isLight
    )

    HomeWorkTheme() {
        Surface() {
            LoginView(
                navHostController = navHostController,
                titleId = R.string.register,
                bottomTextId = R.string.goto_login,
                bottomTextClickFunc = { navHostController.navigate("LoginPage") },
                isRegister = true,
            )
        }
    }
}

@ExperimentalMaterialApi
@Composable
fun HomePage(
    modifier: Modifier = Modifier,
) {
    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(
        color = Color.Transparent,
        darkIcons = MaterialTheme.colors.isLight
    )


    HomeWorkTheme() {
        Surface {
            HomeView(
                navHostController = LocalNavController.current,
            )
        }
    }
}

@ExperimentalMaterialApi
@Composable
fun HotPage(
    modifier: Modifier = Modifier,
) {
    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(
        color = Color.Transparent,
        darkIcons = MaterialTheme.colors.isLight
    )

    HomeWorkTheme() {
        Surface {
            HotView()
        }
    }
}


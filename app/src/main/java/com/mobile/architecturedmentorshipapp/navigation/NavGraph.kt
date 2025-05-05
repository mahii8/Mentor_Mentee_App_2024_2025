package com.mobile.architecturedmentorshipapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.mobile.architecturedmentorshipapp.ui.*

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "welcome"
    ) {
        composable("login") { LoginScreen(navController) }
        composable("signup") { SignupScreen(navController) }
        composable("mentorHome") { MentorHomeScreen(navController) }
        composable("menteeHome") { MenteeHomeScreen(navController) }
        composable("profile") { ProfileScreen(navController) }
        composable("editProfile") { EditProfileScreen(navController) }
        composable("members") { MembersScreen(navController) }
        composable("filterMembers") { FilterMembersScreen(navController) }
        composable("memberProfile") { MemberProfileScreen(navController) }
        composable("sendRequest") { SendRequestScreen(navController) }
        composable("relations") { RelationsScreen(navController) }
        composable("assignTask") { AssignTaskScreen(navController) }
        composable("tasksPage") { TasksPageScreen(navController) }
        composable("request") { RequestScreen(navController) }
        composable("settings") { SettingsScreen(navController) }
        composable("logout") { LogoutScreen(navController) }
        composable("welcome") { WelcomeScreen(navController) }
        composable("changePassword") { ChangePasswordScreen(navController) }
        composable("deleteAccount") { DeleteAccountScreen(navController) }
    }
}

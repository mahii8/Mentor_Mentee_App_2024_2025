package com.example.mento_mentee_app.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.mento_mentee_app.ui.auth.LoginScreen
import com.example.mento_mentee_app.ui.auth.SignupScreen
import com.example.mento_mentee_app.ui.dashboard.MenteeHomeScreen
import com.example.mento_mentee_app.ui.dashboard.MentorHomeScreen
import com.example.mento_mentee_app.ui.member.FilterMembersScreen
import com.example.mento_mentee_app.ui.member.MembersScreen
import com.example.mento_mentee_app.ui.member.MemberProfileScreen
import com.example.mento_mentee_app.ui.profile.ChangePasswordScreen
import com.example.mento_mentee_app.ui.profile.DeleteAccountScreen
import com.example.mento_mentee_app.ui.profile.EditProfileScreen
import com.example.mento_mentee_app.ui.profile.ProfileScreen
import com.example.mento_mentee_app.ui.profile.LogoutScreen
import com.example.mento_mentee_app.ui.profile.SettingsScreen
import com.example.mento_mentee_app.ui.request.RequestScreen
import com.example.mento_mentee_app.ui.request.SendRequestScreen
import com.example.mento_mentee_app.ui.task.AssignTaskScreen
import com.example.mento_mentee_app.ui.task.TasksPageScreen
import com.example.mento_mentee_app.ui.task.RelationsScreen


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

package com.example.mento_mentee_app.ui

import android.net.Uri
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.mento_mentee_app.ui.auth.LoginScreen
import com.example.mento_mentee_app.ui.auth.SignupScreen
import com.example.mento_mentee_app.ui.dashboard.MenteeHomeScreen
import com.example.mento_mentee_app.ui.dashboard.MentorHomeScreen
import com.example.mento_mentee_app.ui.member.FilterMembersScreen
import com.example.mento_mentee_app.ui.member.MembersScreen
import com.example.mento_mentee_app.ui.member.MemberProfileScreen
import com.example.mento_mentee_app.ui.profile.*
import com.example.mento_mentee_app.ui.request.EditRequestScreen
import com.example.mento_mentee_app.ui.request.MentorshipRequestViewModel
import com.example.mento_mentee_app.ui.request.ProcessRequestScreen
import com.example.mento_mentee_app.ui.request.RequestScreen
import com.example.mento_mentee_app.ui.request.SendRequestScreen
import com.example.mento_mentee_app.ui.task.AssignTaskScreen
import com.example.mento_mentee_app.ui.task.RelationsScreen
import com.example.mento_mentee_app.ui.task.TasksPageScreen

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@OptIn(ExperimentalMaterial3Api::class)
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

        composable("members") {
            MembersScreen(navController = navController) // ⬅️ Now uses hiltViewModel() internally
        }

        composable("filterMembers") { FilterMembersScreen(navController) }
        composable("memberProfile") { MemberProfileScreen(navController) }

        composable(
            "sendRequestScreen/{mentorId}/{name}/{skill}",
            arguments = listOf(
                navArgument("mentorId") { type = NavType.StringType },
                navArgument("name") { type = NavType.StringType },
                navArgument("skill") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val mentorId = backStackEntry.arguments?.getString("mentorId")
            val name = backStackEntry.arguments?.getString("name")
            val skill = backStackEntry.arguments?.getString("skill")
            if (mentorId != null && name != null && skill != null) {
                val decodedName = Uri.decode(name)
                val decodedSkill = Uri.decode(skill)
                SendRequestScreen(
                    navController = navController,
                    _id = mentorId,
                    name = decodedName,
                    specialization = decodedSkill
                )
            }
        }

        composable("relations") { RelationsScreen(navController) }
        composable(
            route = "assignTask/{menteeId}",
            arguments = listOf(
                navArgument("menteeId") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val menteeId = backStackEntry.arguments?.getString("menteeId") ?: ""
            AssignTaskScreen(navController, menteeId = menteeId)
        }
        composable("tasksPage") { TasksPageScreen(navController) }
        composable("request") { RequestScreen(navController) }
        composable("process-request-screen") { ProcessRequestScreen(navController) }
        composable(
            route = "edit_task/{id}/{title}/{description}/{dueDate}/{priority}/{mentorId}/{menteeId}/{isCompleted}",
            arguments = listOf(
                navArgument("id") { type = NavType.StringType },
                navArgument("title") { type = NavType.StringType },
                navArgument("description") { type = NavType.StringType },
                navArgument("dueDate") { type = NavType.StringType },
                navArgument("priority") { type = NavType.StringType },
                navArgument("mentorId") { type = NavType.StringType },
                navArgument("menteeId") { type = NavType.StringType },
                navArgument("isCompleted") { type = NavType.BoolType }
            )
        ) {
            EditTaskScreen(
                navController = navController,
                id = it.arguments?.getString("id") ?: "",
                title = it.arguments?.getString("title") ?: "",
                description = it.arguments?.getString("description") ?: "",
                dueDate = it.arguments?.getString("dueDate") ?: "",
                priority = it.arguments?.getString("priority") ?: "",
                mentorId = it.arguments?.getString("mentorId") ?: "",
                menteeId = it.arguments?.getString("menteeId") ?: "",
                isCompleted = it.arguments?.getBoolean("isCompleted") ?: false
            )
        }
        composable(
            route = "edit_request/{id}/{startDate}/{endDate}/{mentorshipTopic}/{additionalNotes}/{mentorId}",
            arguments = listOf(
                navArgument("id") { type = NavType.StringType },
                navArgument("startDate") { type = NavType.StringType },
                navArgument("endDate") { type = NavType.StringType },
                navArgument("mentorshipTopic") { type = NavType.StringType },
                navArgument("additionalNotes") { type = NavType.StringType },
                navArgument("mentorId") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            EditRequestScreen(
                navController = navController,
                id = backStackEntry.arguments?.getString("id") ?: "",
                startDateStr = backStackEntry.arguments?.getString("startDate") ?: "",
                endDateStr = backStackEntry.arguments?.getString("endDate") ?: "",
                mentorshipTopicArg = backStackEntry.arguments?.getString("mentorshipTopic") ?: "",
                additionalNotesArg = backStackEntry.arguments?.getString("additionalNotes") ?: "",
                mentorId = backStackEntry.arguments?.getString("mentorId") ?: ""
            )
        }

        composable("settings") { SettingsScreen(navController) }
        composable("logout") { LogoutScreen(navController) }
        composable("welcome") { WelcomeScreen(navController) }
        composable("changePassword") { ChangePasswordScreen(navController) }
        composable("deleteAccount") { DeleteAccountScreen(navController) }
    }
}

@Composable
fun EditTaskScreen(
    navController: NavHostController,
    id: String,
    title: String,
    description: String,
    dueDate: String,
    priority: String,
    mentorId: String,
    menteeId: String,
    isCompleted: Boolean
) {

}

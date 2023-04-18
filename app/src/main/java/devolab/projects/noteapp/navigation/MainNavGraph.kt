package devolab.projects.noteapp.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import devolab.projects.noteapp.ui.screens.addnotesscreen.AddNoteScreen
import devolab.projects.noteapp.ui.screens.notedetails.NoteDetailsScreen
import devolab.projects.noteapp.ui.screens.notes.NotesScreen

fun NavGraphBuilder.mainNavGraph(
    navController: NavHostController,

) {

    navigation( startDestination = Screens.NotesScreen.route,
        route = MAIN_ROUTE){

        composable(Screens.NotesScreen.route){
            NotesScreen(navController)
        }
        composable(Screens.AddNoteScreen.route){
            AddNoteScreen(navController)
        }
        composable(Screens.NoteDetailsScreen.route){
            NoteDetailsScreen()
        }
    }

}
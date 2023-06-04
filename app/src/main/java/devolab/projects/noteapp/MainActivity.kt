package devolab.projects.noteapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navigation
import dagger.hilt.android.AndroidEntryPoint
import devolab.projects.noteapp.ui.navigation.Screens
import devolab.projects.noteapp.ui.screens.addEditNoteScreen.AddEditNoteScreen
import devolab.projects.noteapp.ui.screens.addEditNoteScreen.AddEditNoteViewModel
import devolab.projects.noteapp.ui.screens.notes.NotesScreen
import devolab.projects.noteapp.ui.screens.notes.NotesViewModel
import devolab.projects.noteapp.ui.theme.NoteAppTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NoteAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()

                    NavHost(
                        navController = navController,
                        startDestination = "main_route"
                    ) {
                        navigation(
                            startDestination = Screens.NotesScreen.route,
                            route = "main_route"
                        ) {
                            composable(Screens.NotesScreen.route) {

                                val notesViewModel = hiltViewModel<NotesViewModel>(it)
                                NotesScreen(
                                    navHostController = navController,
                                    viewModel = notesViewModel
                                )
                            }
                            composable(
                                Screens.AddEditNoteScreen.route + "?noteId={noteId}",
                                arguments = listOf(
                                    navArgument(
                                        name = "noteId"
                                    ) {
                                        type = NavType.IntType
                                        defaultValue = -1
                                    }
                                ))
                            {
                                val addEditNoteViewModel = hiltViewModel<AddEditNoteViewModel>(it)
                                AddEditNoteScreen(
                                    navHostController = navController,
                                    viewModel = addEditNoteViewModel
                                )
                            }
                        }

                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    NoteAppTheme {
        Greeting("Android")
    }
}
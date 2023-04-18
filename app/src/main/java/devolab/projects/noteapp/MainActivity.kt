package devolab.projects.noteapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import dagger.hilt.android.AndroidEntryPoint
import devolab.projects.noteapp.navigation.MAIN_ROUTE
import devolab.projects.noteapp.navigation.Screens
import devolab.projects.noteapp.ui.screens.addnotesscreen.AddNoteScreen
import devolab.projects.noteapp.ui.screens.notedetails.NoteDetailsScreen
import devolab.projects.noteapp.ui.screens.notes.NotesScreen
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
                        startDestination = Screens.NotesScreen.route
                    ) {

                        composable(Screens.NotesScreen.route) {
                            NotesScreen(navController)
                        }
                        composable(Screens.AddNoteScreen.route) {
                            AddNoteScreen(navController)
                        }
                        composable(Screens.NoteDetailsScreen.route) {
                            NoteDetailsScreen()
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
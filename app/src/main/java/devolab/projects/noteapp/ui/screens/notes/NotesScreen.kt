package devolab.projects.noteapp.ui.screens.notes

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import devolab.projects.noteapp.domain.model.Note
import devolab.projects.noteapp.navigation.Screens
import devolab.projects.noteapp.ui.screens.notes.components.NotesScreenTopBar
import devolab.projects.noteapp.ui.screens.notes.components.NotesScrollView
import devolab.projects.noteapp.ui.screens.notes.components.TagsScrollView
import devolab.projects.noteapp.ui.utils.LOREM_IPSUM
import devolab.projects.noteapp.viewmodels.NotesViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun NotesScreen(navHostController: NavHostController, viewModel: NotesViewModel = hiltViewModel()) {

    val scaffoldState = rememberScaffoldState()

    val state = viewModel.notesState

    Scaffold(scaffoldState = scaffoldState) {

        Column(modifier = Modifier.fillMaxSize()) {
            NotesScreenTopBar(
                modifier = Modifier.padding(
                    top = 20.dp,
                    start = 12.dp,
                    end = 12.dp,
                    bottom = 30.dp
                ),
                onAddButtonClick = {
                    navHostController.navigate(Screens.AddNoteScreen.route)
                },
                title = "your notes"
            )
            TagsScrollView(
                tags = state.tags, onTagSelected = { tag, isSelected ->

                }, modifier = Modifier.padding(bottom = 10.dp)
            )

            NotesScrollView(
                notes = viewModel.groupNotesByDate(),
                modifier = Modifier.padding(horizontal = 10.dp)
            )
        }

    }

}
package devolab.projects.noteapp.ui.screens.notes

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import devolab.projects.noteapp.ui.navigation.Screens
import devolab.projects.noteapp.ui.screens.notes.components.NotesScreenTopBar
import devolab.projects.noteapp.ui.screens.notes.components.NotesScrollView
import devolab.projects.noteapp.ui.screens.notes.components.TagsScrollView

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun NotesScreen(navHostController: NavHostController, viewModel: NotesViewModel = hiltViewModel()) {

    val scaffoldState = rememberScaffoldState()

    val state = viewModel.notesState

    CustomDialog(openDialog = state.isDialogShowing,
        dismiss = { viewModel.dismissDeleteDialog() },
        action = { viewModel.deleteNote() })

    Scaffold(scaffoldState = scaffoldState) {

        Column(modifier = Modifier.fillMaxSize()) {
            NotesScreenTopBar(
                modifier = Modifier.padding(
                    top = 20.dp, start = 12.dp, end = 12.dp, bottom = 30.dp
                ), onAddButtonClick = {
                    navHostController.navigate(Screens.AddEditNoteScreen.route)
                }, title = "your notes"
            )
            TagsScrollView(
                tags = state.tags,
                onTagSelected = { tag, isSelected ->
                    if(isSelected){
                        viewModel.addFilter(tag)
                    }
                    if(!isSelected){
                        viewModel.removeFilter(tag)
                    }
                },
                modifier = Modifier.padding(bottom = 10.dp)
            )

            NotesScrollView(
                notes = viewModel.groupNotesByDate(),
                modifier = Modifier.padding(horizontal = 10.dp),
                onClick = {
                    Log.e("notesScreen",it.title)
                    Log.e("notesScreen",it.id.toString())
                    navHostController.navigate(Screens.AddEditNoteScreen.route + "?noteId=${it.id}") },
                onLongClick = {
                    Log.e("notesScreen",it.id.toString())
                    viewModel.showDeleteDialog(it)
                }
            )
        }

    }

}


@Composable
fun CustomDialog(openDialog: Boolean, action: () -> Unit, dismiss: () -> Unit) {
    // Create a state variable to track the visibility of the alert dialog

    // When the showDialog state variable is true, show the alert dialog
    if (openDialog) {
        AlertDialog(
            onDismissRequest = dismiss,
            title = { },
            text = { Text("are you sure you want to delete this note?", color = Color.DarkGray) },
            buttons = {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    horizontalArrangement = Arrangement.spacedBy(20.dp, alignment = Alignment.End),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "dismiss", color = Color.Blue, modifier = Modifier.clickable {
                        dismiss()
                    })
                    Text(text = "delete", color = Color.Red, modifier = Modifier.clickable {
                        action()
                        dismiss()
                    })

                }

            },
            shape = RoundedCornerShape(30.dp)
        )
    }

}
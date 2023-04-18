package devolab.projects.noteapp.ui.screens.addnotesscreen

import android.annotation.SuppressLint
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import devolab.projects.noteapp.ui.screens.addnotesscreen.components.AddNoteActionBar
import devolab.projects.noteapp.ui.screens.addnotesscreen.components.CustomTextField
import devolab.projects.noteapp.viewmodels.NotesViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AddNoteScreen(
    navHostController: NavHostController,
    viewModel: NotesViewModel = hiltViewModel()
) {

    val state = viewModel.noteState

    Scaffold(modifier = Modifier.fillMaxSize()) {
        ConstraintLayout() {
            val (textBox, actionBar) = createRefs()
            Column(
                modifier = Modifier
                    .padding(12.dp)
                    .clip(RoundedCornerShape(30.dp))
                    .border(width = 1.dp, color = Color.DarkGray, shape = RoundedCornerShape(30.dp))
                    .constrainAs(textBox) {
                        top.linkTo(parent.top, 20.dp)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        bottom.linkTo(actionBar.top, 25.dp)
                        height = Dimension.value(600.dp)
                    }
            ) {
                CustomTextField(
                    text = state.title,
                    onValueChange = { viewModel.changeNoteTitle(it) },
                    singleLine = true,
                    textStyle = MaterialTheme.typography.h2,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    isHintVisible = !state.isTitleFocused && state.title.isBlank(),
                    onFocusChanged = { viewModel.changeTitleFocus(it.isFocused) },
                    hint = state.titleHint
                )
                Spacer(modifier = Modifier.height(16.dp))

                CustomTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .padding(20.dp),
                    text = state.content,
                    onValueChange = { viewModel.changeNoteContent(it) },
                    textStyle = MaterialTheme.typography.body2,
                    singleLine = false,
                    isHintVisible = !state.isContentFocused && state.content.isBlank(),
                    onFocusChanged = { viewModel.changeContentFocus(it.isFocused) }
                )

            }

            AddNoteActionBar(modifier = Modifier
                .fillMaxWidth()
                .constrainAs(actionBar) {
                    start.linkTo(parent.start, 10.dp)
                    end.linkTo(parent.end, 10.dp)
                    bottom.linkTo(parent.bottom, 20.dp)
                },
                onAddNote = {
                    viewModel.insertNote()
                    navHostController.navigateUp()
                },
                onCancel = { navHostController.navigateUp() })
        }

    }


}
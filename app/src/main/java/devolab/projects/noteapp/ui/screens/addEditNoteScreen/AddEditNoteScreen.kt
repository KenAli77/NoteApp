package devolab.projects.noteapp.ui.screens.addEditNoteScreen

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import devolab.projects.noteapp.ui.navigation.Screens
import devolab.projects.noteapp.ui.screens.addEditNoteScreen.components.AddNoteActionBar
import devolab.projects.noteapp.ui.screens.addEditNoteScreen.components.ColorSelectorRow
import devolab.projects.noteapp.ui.screens.addEditNoteScreen.components.CustomTextField
import devolab.projects.noteapp.ui.screens.addEditNoteScreen.components.TagEditor
import devolab.projects.noteapp.ui.theme.LightPurple
import devolab.projects.noteapp.ui.theme.PastelBlue
import devolab.projects.noteapp.ui.theme.PastelGreen
import devolab.projects.noteapp.ui.theme.Peach
import devolab.projects.noteapp.ui.utils.HORIZONTAL_MAIN_PADDING

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AddEditNoteScreen(
    navHostController: NavHostController,
    viewModel: AddEditNoteViewModel = hiltViewModel()
) {

    val state = viewModel.noteState


    Scaffold(modifier = Modifier.fillMaxSize()) {
        ConstraintLayout {
            val (textBox, actionBar, tagEditor, colorSelector) = createRefs()
            ColorSelectorRow(modifier = Modifier.constrainAs(colorSelector) {
                top.linkTo(parent.top,10.dp)

            }.fillMaxWidth(), onColorSelected ={viewModel.changeNoteColor(it)}, colors = listOf(
                LightPurple.toArgb(),
                Peach.toArgb(),
                PastelGreen.toArgb(),
                PastelBlue.toArgb()
            ), selectedColor =Color(state.color))

            Column(
                modifier = Modifier
                    .padding(12.dp)
                    .clip(RoundedCornerShape(30.dp))
                    .border(width = 1.dp, color = Color.DarkGray, shape = RoundedCornerShape(30.dp))
                    .constrainAs(textBox) {
                        top.linkTo(colorSelector.bottom, 5.dp)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        bottom.linkTo(actionBar.top, 25.dp)
                        height = Dimension.value(600.dp)
                    }
                    .background(Color(state.color))

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
                    hint = state.titleHint,

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

            TagEditor(
                modifier = Modifier
                    .fillMaxWidth()
                    .constrainAs(tagEditor) {
                        bottom.linkTo(actionBar.top)
                        top.linkTo(textBox.bottom)
                    }
                    .padding(horizontal = HORIZONTAL_MAIN_PADDING.dp),
                value = viewModel.tag,
                onValueChange = { viewModel.tag = it },
                onAddTag = {
                    if (viewModel.tag.isNotBlank()) {
                        viewModel.addTag(it)
                    }
                },
                tags = viewModel.tags,
                onRemove = { viewModel.removeTag(it) }
            )

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
                onCancel = {
                    navHostController.navigateUp()
                })
        }

    }


}
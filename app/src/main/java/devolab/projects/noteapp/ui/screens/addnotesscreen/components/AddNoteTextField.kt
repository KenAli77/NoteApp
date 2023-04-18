package devolab.projects.noteapp.ui.screens.addnotesscreen.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle


@Composable
fun CustomTextField(
    modifier: Modifier,
    text: String,
    onValueChange: (String) -> Unit,
    hint: String = "Type in something..",
    textStyle:TextStyle,
    singleLine:Boolean,
    onFocusChanged:(FocusState)->Unit,
    isHintVisible: Boolean = true,
    ) {


    Box(modifier = modifier) {

        BasicTextField(
            value = text,
            onValueChange = onValueChange,
            textStyle = textStyle,
            singleLine = singleLine,
            modifier = Modifier
                .fillMaxWidth()
                .onFocusChanged {
                    onFocusChanged(it)
                }
        )
        if(isHintVisible) {
            Text(text = hint, style = textStyle, color = Color.DarkGray)
        }


    }
}

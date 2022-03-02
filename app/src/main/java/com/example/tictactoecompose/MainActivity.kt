package com.example.tictactoecompose

import android.graphics.ColorFilter
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tictactoecompose.ui.theme.TicTacToeComposeTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TicTacToeComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    TableState()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TableState(){
    var tbl = rememberSaveable{ mutableStateOf("BBBBBBBXO") }

    Table(
        tb = tbl ,
        change = {idx-> tbl.value = "B".repeat(idx) +"X".repeat(8-idx) })
}



fun charImg(c :Char):Int{
    return when(c){
        'X'->R.drawable.ic_baseline_add_circle_outline_24
        'O'->R.drawable.ic_baseline_radio_button_checked_24
        else ->R.drawable.ic_baseline_radio_button_unchecked_24
    }
}

@Composable
fun Table(tb: MutableState<String> , change : (idx :Int ) -> Unit) {


    Column(
        Modifier.background(Color.DarkGray).fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally


    ) {
        for (i in 0 until 3){

            Row() {
                for (j in 0 until 3){
                    Image(
                        painter = painterResource(id = charImg(tb.value[i*3+j])) ,
                        contentDescription = "sq", modifier = Modifier.size(110.dp).clickable { change(i*3+j) }
                    )
                }
            }
        }

    }

}


package com.example.tictactoecompose

import android.graphics.ColorFilter
import android.graphics.fonts.FontStyle
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.unit.sp
import com.example.tictactoecompose.ui.theme.TicTacToeComposeTheme

class MainActivity : ComponentActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TicTacToeComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Table()
                }
            }
        }
    }
}




fun charImg(c :Char):Int{
    return when(c){
        'X'->R.drawable.ic_baseline_add_circle_outline_24
        'O'->R.drawable.ic_baseline_radio_button_checked_24
        else ->R.drawable.ic_baseline_radio_button_unchecked_24
    }
}

fun charNext(c :Char):Char{
    return when(c){
        'X'->'O'
        'O'->'B'
        'B'->'X'
        else ->'B'
    }
}

fun strNew(prev: String , i : Int , j : Int):String{
    val idx = i*3 + j
    return prev.substring(0, idx) + charNext(prev[idx]) + prev.substring(idx + 1)
}

fun checkWinner(tb: String):Char{
    for (c in charArrayOf('X' , 'O')){
        
        if (tb[0] == c && tb[4] == c && tb[8] == c)
            return c
        if (tb[2] == c && tb[4] == c && tb[6] == c)
            return c         
        for (i in 0 until  3){
            if (tb[0 + i*3] == c && tb[1+ i*3] == c && tb[2+ i*3] == c)
                return c
            if (tb[0 + i] == c && tb[3+ i] == c && tb[6+ i] == c)
                return c
        }
        
    }
    return 'B'

}

@Preview(showBackground = true)
@Composable
fun Table() {

    var tb = rememberSaveable{ mutableStateOf("BBBBBBBXO") }
    var winner = rememberSaveable{ mutableStateOf('B') }
    Column(
        Modifier
            .background(Color.DarkGray)
            .fillMaxWidth().fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,verticalArrangement =Arrangement.Center



    ) {
        for (i in 0 until 3){

            Row() {
                for (j in 0 until 3){
                    Image(
                        painter = painterResource(id = charImg(tb.value[i*3+j])) ,
                        contentDescription = "sq", modifier = Modifier
                            .size(110.dp)
                            .clickable {
                                tb.value = strNew(tb.value, i, j)
                                winner.value = checkWinner(tb.value)
                            }
                    )
                }
            }
        }
        
        Text(text = "The winner is " + winner.value, color = Color.LightGray , fontSize = 40.sp)

    }

}


package com.example.bizcrd

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialogDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Green
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.bizcrd.ui.theme.BizCrdTheme
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BizCrdTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    CreateBizCard()
                }
            }
        }
    }
}

@Composable
fun CreateBizCard() {
    val buttonClickedState = remember {
        mutableStateOf(false)
    }
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {//with this we have created a surface that will acquire max height and max width
        Card(
            modifier = Modifier
                .width(200.dp)
                .height(390.dp)
                .background(color = Green)
                .padding(12.dp),
            shape = RoundedCornerShape(corner = CornerSize(15.dp)),
            colors = CardDefaults.cardColors(
                containerColor = Color.White,
            ),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)//it will add that boxy and shadow effect to the box.
        ) {
            Column(modifier = Modifier
                .height(1000.dp)
                .width(370.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top)//it will align everything in this container to be center alligned
            {
                CreateImageProfile()
                Divider(modifier = Modifier,
                    thickness = 4.dp,
                    color = Color.Black
                )

                CreateInfo()

                Button(
                    modifier = Modifier
                        .height(40.dp)
                        .width(130.dp),
                    onClick = {
                        buttonClickedState.value = !buttonClickedState.value//toggling bw true ans false

                    }) {
                    Text(text = "Portfolio",
                         )
                }
                if(buttonClickedState.value) {
                    Content()
                }
                else{
                    Box(){

                    }
              }

            }
        }
    }
}
@Composable
fun Content(){
    Box(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight()
        .padding(5.dp)){
        Surface(modifier = Modifier
            .padding(3.dp)
            .fillMaxHeight()
            .fillMaxWidth(),
            shape = RoundedCornerShape(corner = CornerSize(10.dp)),
            border = BorderStroke(width = 2.dp, color = Color.White)
        ) {
            Portfolio(data = listOf("Project 1","Project 2","Project 3"))

        }
    }
}

@Composable
fun Portfolio(data: List<String>) {
    LazyColumn {//scrollable list
        items(data) {item ->
            Card(modifier = Modifier
                .padding(13.dp)
                .width(500.dp)
                .fillMaxHeight(),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                shape = RectangleShape){
                Row(modifier = Modifier
                    .padding(8.dp)
                    .width(500.dp)
                    .background(MaterialTheme.colorScheme.surface)
                    .padding(4.dp)) {
                    CreateImageProfile(modifier = Modifier.size(100.dp))
                    Column(modifier = Modifier
                        .padding(7.dp)
                        .align(alignment = Alignment.CenterVertically)){
                        Text(text = item, fontWeight = FontWeight.Bold)
                        Text(text = "A great project",
                            style = MaterialTheme.typography.bodyMedium)
                    }

                }
            }
        }
    }
}

@Composable
private fun CreateInfo() {
    Column(modifier = Modifier.padding(5.dp)) {
        Text(
            text = "RITIK JOSHI",
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.primary
        )

        Text(
            text = "Android Compose Programmar",
            modifier = Modifier.padding(3.dp)
        )

        Text(
            text = "@ritik_6559",
            modifier = Modifier.padding(3.dp)
        )
    }
}

@Composable
private fun CreateImageProfile(modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier
            .size(180.dp)
            .padding(5.dp),
        shape = CircleShape,
        border = BorderStroke(width = 0.5.dp, color = LightGray),
        shadowElevation = 4.dp,
        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
    ) {
        Image(
            painter = painterResource(id = R.drawable.img),
            contentDescription = "Profile Image",
            modifier = modifier.size(180.dp)
        )

    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    BizCrdTheme {
        CreateBizCard()
    }
}
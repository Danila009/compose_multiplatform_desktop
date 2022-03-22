
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import api.Api
import api.ApiRepository
import api.model.Music
import api.utils.ConstantsUrl.BASE_URL
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        val repository = ApiRepository(
            retrofit()
        )
        val scope = rememberCoroutineScope()
        val music = remember { mutableStateOf(listOf<Music>()) }
        scope.launch {
            music.value = repository.getMusic().body()!!
        }
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = Color(0xFF23282D)
        ){
            LazyColumn {
                items(music.value){item ->
                    Column{
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            Icon(
                                imageVector = Icons.Default.Person,
                                contentDescription = null,
                                tint = Color.Cyan
                            )

                            Text(
                                text = item.title,
                                color = Color.White
                            )
                        }
                        Divider()
                    }
                }
            }
        }
    }
}

private fun retrofit():Api = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(GsonConverterFactory.create())
    .build()
    .create(Api::class.java)

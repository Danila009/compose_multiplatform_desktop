package api

import api.model.Music
import api.utils.ConstantsUrl.MUSIC_URL
import retrofit2.Response
import retrofit2.http.GET

interface Api {

    @GET(MUSIC_URL)
    suspend fun getMusic():Response<List<Music>>
}
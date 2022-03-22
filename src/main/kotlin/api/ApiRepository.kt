package api

import api.model.Music
import retrofit2.Response

class ApiRepository(
    private val api: Api,
) {
    suspend fun getMusic():Response<List<Music>> {
        return api.getMusic()
    }
}
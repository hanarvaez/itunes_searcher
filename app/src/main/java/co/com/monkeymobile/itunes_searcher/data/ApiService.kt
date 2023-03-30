package co.com.monkeymobile.itunes_searcher.data

import co.com.monkeymobile.itunes_searcher.data.ApiUrl.ENTITY_DEFAULT_VALUE
import co.com.monkeymobile.itunes_searcher.data.ApiUrl.LIMIT_DEFAULT_VALUE
import co.com.monkeymobile.itunes_searcher.data.source.remote.response.AlbumQueryBackendResponse
import retrofit2.http.GET
import retrofit2.http.Query

object ApiUrl {
    const val REST_BASE_URL = "https://itunes.apple.com/"

    const val SEARCH_PARTICLE = "search"

    const val TERM_QUERY = "term"
    const val ENTITY_QUERY = "entity"
    const val RESULTS_LIMIT_QUERY = "limit"

    const val ENTITY_DEFAULT_VALUE = "album"
    const val LIMIT_DEFAULT_VALUE = 200
}

interface ApiService {

    @GET(ApiUrl.SEARCH_PARTICLE)
    suspend fun fetchAlbumsListByTerm(
        @Query(ApiUrl.TERM_QUERY) term: String,
        @Query(ApiUrl.ENTITY_QUERY) entity: String = ENTITY_DEFAULT_VALUE,
        @Query(ApiUrl.RESULTS_LIMIT_QUERY) limit: Int = LIMIT_DEFAULT_VALUE
    ): AlbumQueryBackendResponse
}

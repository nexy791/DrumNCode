package com.test.data.source.shared.remote

import com.test.data.model.feed.response.FeedResponseModel
import com.test.data.model.info.response.InfoResponseModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Api service for all requests
 *
 * API_KEY is added to the request by [AuthInterceptor]
 */
interface ApiService {

    private companion object {

        // query params
        private const val QUERY_FORMAT = "format"
        private const val QUERY_METHOD = "method"
        private const val QUERY_NO_JSON_CALLBACK = "nojsoncallback" // for jsonFlickrApi({ ... })

        private const val QUERY_PER_PAGE = "per_page"


        // query values
        private const val VALUE_FORMAT = "json"
        private const val VALUE_NO_JSON_CALLBACK = 1
        private const val VALUE_PER_PAGE = 9 // 3x3 grid
    }

    @GET("rest/")
    suspend fun getImagesFromTheLastDay(
        @Query(QUERY_METHOD) method: String = "flickr.interestingness.getList",
        @Query(QUERY_FORMAT) format: String = VALUE_FORMAT,
        @Query(QUERY_NO_JSON_CALLBACK) noJsonCallback: Int = VALUE_NO_JSON_CALLBACK,
        @Query(QUERY_PER_PAGE) perPage: Int = VALUE_PER_PAGE,
    ): Response<FeedResponseModel>

    @GET("rest/")
    suspend fun getImageInfo(
        @Query("photo_id") photoId: String,
        @Query(QUERY_METHOD) method: String = "flickr.photos.getInfo",
        @Query(QUERY_FORMAT) format: String = VALUE_FORMAT,
        @Query(QUERY_NO_JSON_CALLBACK) noJsonCallback: Int = VALUE_NO_JSON_CALLBACK,
    ): Response<InfoResponseModel>

}
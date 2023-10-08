package com.test.data.model.feed.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

// --> flickr.interestingness.getList
// https://www.flickr.com/services/api/flickr.interestingness.getList.html
@JsonClass(generateAdapter = true)
data class FeedResponseModel(
    @Json(name = "photos") val photos: PhotosModel? = null,
) {

    @JsonClass(generateAdapter = true)
    data class PhotosModel(
        @Json(name = "photo") val photo: List<PhotoModel>? = null,
    ) {

        @JsonClass(generateAdapter = true)
        data class PhotoModel(
            @Json(name = "id") val id: String? = null,
            @Json(name = "title") val title: String? = null,
            @Json(name = "secret") val secret: String? = null,
            @Json(name = "server") val server: String? = null,
        )

    }

}
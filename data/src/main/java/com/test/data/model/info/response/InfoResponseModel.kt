package com.test.data.model.info.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

// --> flickr.photos.getInfo
// https://www.flickr.com/services/api/flickr.photos.getInfo.html
@JsonClass(generateAdapter = true)
data class InfoResponseModel(
    @Json(name = "photo") val photo: PhotoModel? = null,
) {

    @JsonClass(generateAdapter = true)
    data class PhotoModel(
        @Json(name = "id") val id: String? = null,
        @Json(name = "server") val server: String? = null,
        @Json(name = "secret") val secret: String? = null,
        @Json(name = "dateuploaded") val dateUploaded: String? = null,
        @Json(name = "owner") val owner: OwnerModel? = null,
        @Json(name = "title") val title: TitleModel? = null,
        @Json(name = "description") val description: DescriptionModel? = null,
        @Json(name = "views") val views: String? = null,
        @Json(name = "urls") val urls: UrlsModel? = null,
    ) {

        @JsonClass(generateAdapter = true)
        data class OwnerModel(
            @Json(name = "username") val username: String? = null,
            @Json(name = "realname") val realName: String? = null,
        )

        @JsonClass(generateAdapter = true)
        data class TitleModel(
            @Json(name = "_content") val content: String? = null,
        )

        @JsonClass(generateAdapter = true)
        data class DescriptionModel(
            @Json(name = "_content") val content: String? = null,
        )

        @JsonClass(generateAdapter = true)
        data class UrlsModel(
            @Json(name = "url") val url: List<UrlModel>? = null,
        ) {

            @JsonClass(generateAdapter = true)
            data class UrlModel(
                @Json(name = "type") val type: String? = null,
                @Json(name = "_content") val content: String? = null,
            )

        }

    }

}
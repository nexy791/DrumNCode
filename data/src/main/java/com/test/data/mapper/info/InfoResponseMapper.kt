package com.test.data.mapper.info

import com.test.data.mapper.BaseMapper
import com.test.data.model.info.ImageInfoEntity
import com.test.data.model.info.response.InfoResponseModel

class InfoResponseMapper :
    BaseMapper.Mapper<InfoResponseModel.PhotoModel, ImageInfoEntity>() {

    companion object {
        private const val PHOTO_PAGE_URL = "photopage"
    }

    // we need id so if null throw error
    override fun map(from: InfoResponseModel.PhotoModel): ImageInfoEntity =
        ImageInfoEntity(
            title = from.title?.content.orEmpty(),
            id = from.id!!,
            dateUploaded = from.dateUploaded.orEmpty(),
            ownerUsername = from.owner?.username.orEmpty(),
            ownerRealName = from.owner?.realName.orEmpty(),
            description = from.description?.content.orEmpty(),
            views = from.views ?: "0",
            urlOriginal = from.urls?.url?.firstOrNull { it.type == PHOTO_PAGE_URL }?.content.orEmpty(),
            secret = from.secret.orEmpty(),
            server = from.server.orEmpty()
        )

    override fun reverseMap(to: ImageInfoEntity): InfoResponseModel.PhotoModel =
        InfoResponseModel.PhotoModel(
            title = InfoResponseModel.PhotoModel.TitleModel(to.title),
            id = to.id,
            dateUploaded = to.dateUploaded,
            owner = InfoResponseModel.PhotoModel.OwnerModel(to.ownerUsername, to.ownerRealName),
            description = InfoResponseModel.PhotoModel.DescriptionModel(to.description),
            views = to.views,
            urls = InfoResponseModel.PhotoModel.UrlsModel(
                listOf(
                    InfoResponseModel.PhotoModel.UrlsModel.UrlModel(
                        PHOTO_PAGE_URL,
                        to.urlOriginal
                    )
                )
            )
        )
}
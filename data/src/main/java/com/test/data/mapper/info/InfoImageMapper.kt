package com.test.data.mapper.info

import com.test.data.mapper.BaseMapper
import com.test.data.model.info.ImageInfoEntity
import com.test.domain.model.InfoImageModel

class InfoImageMapper(
    private val baseUrl: String,
) : BaseMapper.PathMapper<ImageInfoEntity, InfoImageModel>() {

    companion object {
        private const val SIZE_SUFFIX = "b" // b - large 1024 on the longest side
    }

    override fun map(from: ImageInfoEntity): InfoImageModel = InfoImageModel.Base(
        id = from.id,
        title = from.title,
        description = from.description,
        views = from.views,
        ownerUsername = from.ownerUsername,
        ownerRealName = from.ownerRealName,
        dateUploaded = from.dateUploaded,
        path = getImagePath(
            base = baseUrl,
            server = from.server,
            id = from.id,
            secret = from.secret,
            sizeSuffix = SIZE_SUFFIX
        ),
        urlOriginal = from.urlOriginal,
        server = from.server,
        secret = from.secret,
    )

    override fun reverseMap(to: InfoImageModel): ImageInfoEntity =
        ImageInfoEntity(
            id = to.id,
            title = to.title,
            secret = to.secret,
            server = to.server,
            description = to.description,
            views = to.views,
            ownerUsername = to.ownerUsername,
            ownerRealName = to.ownerRealName,
            dateUploaded = to.dateUploaded,
            urlOriginal = to.urlOriginal
        )

}
package domain.utils

import kotlin.experimental.ExperimentalObjCName
import kotlin.native.ObjCName

enum class AppDeepLink {
    HOME, SIGNIN, UNKNOWN;


    @OptIn(ExperimentalObjCName::class)
    @ObjCName(swiftName = "utils")
    companion object {
        private val APP_DEEP_LINKS = mapOf(
            HOME to "kmm://home", SIGNIN to "kmm://signin", UNKNOWN to "kmm://unknown"
        )

        fun typeFromPath(fullDeepLink: String) =
            APP_DEEP_LINKS.entries.find { it.value == fullDeepLink }?.key ?: UNKNOWN

        fun pathFromType(linkType: AppDeepLink) = APP_DEEP_LINKS[linkType] ?: ""
    }
}
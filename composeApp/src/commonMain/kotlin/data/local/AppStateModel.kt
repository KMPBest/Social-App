package data.local

import io.realm.kotlin.types.RealmObject

class AppStateModel : RealmObject {
    var isDarkMode: Boolean = false
    var token = ""
    var refreshToken = ""
}
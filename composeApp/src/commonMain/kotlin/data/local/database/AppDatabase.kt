package data.local.database

import io.realm.kotlin.types.RealmObject


/**
 * @author: Edward
 * This object is just an example, this database should store a list of like a post of social, and use that data to show for user if offline
 */
class AppDatabase : RealmObject {
    var isDarkMode: Boolean = false
    var token = ""
    var refreshToken = ""
}
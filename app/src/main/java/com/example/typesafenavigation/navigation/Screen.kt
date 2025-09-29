package com.example.typesafenavigation.navigation

import android.os.Build
import android.os.Parcelable
import androidx.navigation.NavType
import androidx.savedstate.SavedState
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

sealed interface Screen {
    @Serializable
    data object FirstScreen : Screen

    @Serializable
    data class SecondScreen(
        val dummy: Dummy?
    ) : Screen

}

@Serializable
@Parcelize
data class Dummy(
    val name: String, val age: Int
) : Parcelable


class CustomNavType<T : Parcelable>(
    private val clazz: Class<T>,
    private val serializer: KSerializer<T>
) : NavType<T?>(isNullableAllowed = true) {

    companion object {
        const val NULL = "null"
    }

    override fun put(bundle: SavedState, key: String, value: T?) {
        bundle.putParcelable(key, value)
    }

    override fun get(bundle: SavedState, key: String): T? {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            bundle.getParcelable(key, clazz)
        } else {
            bundle.getParcelable(key)
        }
    }

    override fun parseValue(value: String): T? {
        return if (value == NULL) null else Json.decodeFromString(serializer, value)
    }

    override fun serializeAsValue(value: T?): String {
        return value?.let {
            Json.encodeToString(serializer, it)
        } ?: NULL
    }

}
package br.com.nandoligeiro.samplearch.data.repository.datasource.network

import androidx.annotation.Nullable
import com.squareup.moshi.FromJson
import com.squareup.moshi.JsonQualifier
import com.squareup.moshi.JsonReader
import com.squareup.moshi.ToJson



class NullToEmptyStringAdapter {

    @ToJson
    fun toJson(value: String?): String? {
        return value
    }

    @FromJson
    fun fromJson(reader: JsonReader): String {
        if (reader.peek() != JsonReader.Token.NULL) {
            return reader.nextString()
        }
        reader.nextNull<Unit>()
        return ""
    }
}
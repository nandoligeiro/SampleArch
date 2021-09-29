package br.com.nandoligeiro.samplearch.data.network

import com.squareup.moshi.FromJson
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
package com.tech.assignment.model

import io.realm.RealmObject

//save data in realm db
 open class ZipCodeModel(
    var pinCodeStart: String? = null,
    var pinCodeEnd: String? = null,
    var pinCodeLocation: String? = null,
): RealmObject() {
    override fun toString(): String {
        return "ZipCodeModel(pinCodeStart=$pinCodeStart)"+"ZipCodeModel(pinCodeEnd=$pinCodeEnd)"+"ZipCodeModel(pinCodeLocation=$pinCodeLocation)"
    }
}
package com.tech.assignment.model

import io.realm.RealmObject

//save data in realm db
 open class ZipCodeModel(
    var pinCode: String? = null,
): RealmObject() {
    override fun toString(): String {
        return "ZipCodeModel(pinCode=$pinCode)"
    }
}
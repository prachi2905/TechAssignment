package com.tech.assignment.localDb

import com.tech.assignment.model.ZipCodeModel
import io.realm.Realm
import io.realm.RealmResults
import java.util.ArrayList

open class PinCodeDao {


    companion object {
        val getLocalDbData = fun(): RealmResults<ZipCodeModel>? {
            val db = Realm.getDefaultInstance()
            val pinCodeList = db.where(ZipCodeModel::class.java).findAll()
            return pinCodeList
        }
        fun saveDataInLocalDb(pinCodeList: ArrayList<ZipCodeModel>) {
            val realm = Realm.getDefaultInstance()
            realm.beginTransaction()
            realm.copyToRealm(pinCodeList)
            realm.commitTransaction()
        }
    }

}
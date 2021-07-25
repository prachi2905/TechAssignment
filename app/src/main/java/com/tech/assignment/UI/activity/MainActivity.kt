package com.tech.assignment.UI.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.tech.assignment.R
import com.tech.assignment.UI.adapter.HomeScreenAdapter
import com.tech.assignment.databinding.ActivityMainBinding
import com.tech.assignment.localDb.PinCodeDao
import com.tech.assignment.localDb.PinCodeDao.Companion.getLocalDbData
import com.tech.assignment.model.ZipCodeModel
import io.realm.RealmResults
import org.apache.commons.csv.CSVFormat
import org.apache.commons.csv.CSVParser
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.util.*


class MainActivity : AppCompatActivity() {
    private lateinit var response: ZipCodeModel
    private lateinit var userDataAdapter: HomeScreenAdapter
    private lateinit var binding: ActivityMainBinding
    val pinCodeList = ArrayList<ZipCodeModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        //Activity indicator enabled
        binding.progressBar.visibility = View.VISIBLE

        //check if data is avaiable in local DB then set the UI
        if (!(getLocalDbData()!!.size == 0)) {
            getLocalDbData()?.let { setDataOnUI(it) }
        } else {
            //read the  csv file if data is not avaiable in local DB(first time app launch)
            readCSVFile()
        }
    }

    fun readCSVFile() {
        val inputStram: InputStream = resources.openRawResource(R.raw.zip_code)
        val inputreader = InputStreamReader(inputStram)
        val bufferedReader = BufferedReader(inputreader);
        val csvParser = CSVParser(bufferedReader, CSVFormat.DEFAULT);
        for (csvRecord in csvParser) {

            //need to show  pincode which is avaiable on number 3
            val pinCodeLocation = csvRecord.get(3);
            val pinCodeStart = csvRecord.get(14);
            val pinCodeEnd = csvRecord.get(15);
            response = ZipCodeModel(pinCodeStart,pinCodeEnd,pinCodeLocation)
            pinCodeList.add(response)

            //save data in realm DB
            PinCodeDao.saveDataInLocalDb(pinCodeList)
        }
        //show data on list from DB
        if (!(getLocalDbData()!!.size == 0)) {
            getLocalDbData()?.let { setDataOnUI(it) }
        }
    }

    //setting data on recycler View Adapter
    fun setDataOnUI(zipCodeModel: RealmResults<ZipCodeModel>) {
        binding.progressBar.visibility = View.GONE
        userDataAdapter = zipCodeModel.let { HomeScreenAdapter(this, it) }
        val mLayoutManager = LinearLayoutManager(this)
        binding.recyclerView.addItemDecoration(
            DividerItemDecoration(
                this, LinearLayoutManager.VERTICAL
            )
        )
        binding.recyclerView.layoutManager = mLayoutManager
        binding.recyclerView.adapter = userDataAdapter
    }

}

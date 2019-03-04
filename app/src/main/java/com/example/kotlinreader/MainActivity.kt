package com.example.kotlinreader

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import java.net.HttpURLConnection
import java.net.URL
import android.os.StrictMode
import android.util.Log
import org.apache.commons.io.FileUtils
import java.io.*
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.core.ZipFile;




class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (android.os.Build.VERSION.SDK_INT > 9) {
            val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
            StrictMode.setThreadPolicy(policy)
        }
    }

    fun submitGithubUrl (view: View){
        val fName ="https://github.com/Movshin/KotlinReader/archive/master.zip" // I don't get it from input for now for simplicity of testing
        val fileUrl=URL(fName)
        val targetFile = File(getExternalFilesDir(null).path+"/targetFile.zip")
        //TODO replace getExternalFilesDir by getExternalStorageDirectory with request of permission
        FileUtils.copyURLToFile(fileUrl,targetFile)
        unzip(targetFile.path,getExternalFilesDir(null).path+'/')
    }


    fun unzip (source: String,destination:String){
        val zipFile = ZipFile(source)
        zipFile.extractAll(destination)
    }



}

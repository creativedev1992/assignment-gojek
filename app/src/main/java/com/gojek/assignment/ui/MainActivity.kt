package com.gojek.assignment.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.gojek.assignment.R
import com.gojek.assignment.di.APIModule
import com.gojek.assignment.di.DaggerAPIComponent
import com.gojek.assignment.model.RPMResponse
import com.gojek.assignment.util.Resource
import com.gojek.assignment.viewmodel.RPMViewModel
import com.gojek.assignment.viewmodel.RPMViewModelFactory
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(),View.OnClickListener {
    lateinit var viewModel: RPMViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnupdate.setOnClickListener(this)
        val component = DaggerAPIComponent
            .builder()
            .aPIModule(APIModule())
            .build()
        component.inject(this)
        val notificationViewModelFactory = RPMViewModelFactory()
        viewModel =
            ViewModelProviders.of(this, notificationViewModelFactory)
                .get(RPMViewModel::class.java)
        viewModel.rpmLiveData?.observeForever {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    val res = it.data as RPMResponse
                    Log.d("check", "response random" + res[0].random)
                    tvrpm.text = "${res.get(0).random}${getString(R.string.rstring_rpm)}"
                    btnupdate.isEnabled = true
                    btnupdate.isClickable=true
                    btnupdate.alpha=1.0f
                    imgwheel.rotation= res.get(0).random.toFloat()
                    Snackbar.make(parentview,"SPEED UPDATED",Snackbar.LENGTH_LONG).show()
                }
                Resource.Status.ERROR -> {
                    btnupdate.isEnabled = true
                    btnupdate.isClickable=true
                    Snackbar.make(parentview,"UNABLE TO UPDATE SPEED",Snackbar.LENGTH_LONG).show()
                }
                Resource.Status.LOADING -> {
                }
            }

        }
    }

    override fun onClick(v: View?) {
        if(v?.id==R.id.btnupdate)
        {
            btnupdate.isEnabled = false
            btnupdate.isClickable=false
            btnupdate.alpha=0.4f
            GlobalScope.launch {
                viewModel.fetchRPM()
            }
            Snackbar.make(parentview,"Fetching RPM",Snackbar.LENGTH_LONG).show()
        }
    }
}
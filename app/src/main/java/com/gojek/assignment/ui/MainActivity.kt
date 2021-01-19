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

class MainActivity : AppCompatActivity() {
    lateinit var viewModel: RPMViewModel
    var rootView:View?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rootView =
            getWindow().getDecorView().findViewById(android.R.id.content)

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
            Log.d("check", "response status" + it.status)
           /* btnupdate.isEnabled = true
            btnupdate.isClickable=true*/
           // btnupdate.alpha=0.0f
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    val res = it.data as RPMResponse
                    Log.d("check", "response random" + res[0].random)
                    tvrpm.setText(res.get(0).random)
                    btnupdate.isEnabled = true
                    btnupdate.isClickable=true
                    imgwheel.rotation= res.get(0).random.toFloat()

                    Snackbar.make(rootView as View,"SPEED UPDATED",Snackbar.LENGTH_LONG)
                }
                Resource.Status.ERROR -> {
                    btnupdate.isEnabled = true
                    btnupdate.isClickable=true
                    Snackbar.make(rootView as View,"UNABLE TO UPDATE SPEED",Snackbar.LENGTH_LONG)
                }
                Resource.Status.LOADING -> {
                }
            }

        }
        btnupdate.setOnClickListener {
            btnupdate.isEnabled = false
            btnupdate.isClickable=false
           // btnupdate.alpha=0.5f
            GlobalScope.launch {
                viewModel.fetchRPM()
            }
            Toast.makeText(MainActivity@ this, "Fetching RPM", Toast.LENGTH_SHORT).show()
        }
    }
}
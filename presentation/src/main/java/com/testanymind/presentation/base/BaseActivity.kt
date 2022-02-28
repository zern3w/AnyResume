package com.testanymind.presentation.base

import android.os.Bundle
import androidx.annotation.CallSuper
import androidx.annotation.LayoutRes
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

abstract class BaseActivity : AppCompatActivity() {

    @LayoutRes
    abstract fun layoutId(): Int
    abstract fun getToolBar(): Toolbar?

    abstract fun start()

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutId())

        getToolBar()?.let {
            setSupportActionBar(it)
            val actionBar = supportActionBar
            if (actionBar != null) {
                setUpActionbar(actionBar)
            }
        }

        start()
    }

//    override fun attachBaseContext(newBase: Context?) {
//        newBase?.let {
//            val localeContext = LocaleHelper.onAttach(newBase)
//            super.attachBaseContext(localeContext)
//        }?:run{
//            super.attachBaseContext(newBase)
//        }
//    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    protected open fun setUpActionbar(actionBar: ActionBar?) {
//        actionBar?.setHomeAsUpIndicator(R.drawable.ic_back_black)
    }
}
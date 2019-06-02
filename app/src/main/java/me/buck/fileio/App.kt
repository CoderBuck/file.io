package me.buck.fileio

import android.app.Application
import timber.log.Timber

/**
 * Created by buck on 2019-06-02
 */
class App :Application() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}
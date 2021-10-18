package com.fronties.socialeventchat.profile.room

import android.os.Handler
import android.os.Looper
import java.util.concurrent.Executor
import java.util.concurrent.Executors

class ProfileExecutor(

)
{

    private var diskIO: Executor? = null
    private var mainThread: Executor? = null
    private var networkIO: Executor? = null
    companion object{
        private val LOCK = Any()

        private var sInstance: ProfileExecutor? = null
        fun getInstance(): ProfileExecutor? {
            if (sInstance == null) {
                synchronized(LOCK) {
                    sInstance =
                        ProfileExecutor(
                            Executors.newSingleThreadExecutor(),
                            Executors.newFixedThreadPool(3),
                            MainThreadExecutor()
                        )
                }
            }
            return sInstance
        }
    }




    constructor(diskIO: Executor, networkIO: Executor, mainThread: Executor) : this() {
        this.diskIO = diskIO
        this.networkIO = networkIO
        this.mainThread = mainThread
    }




    fun diskIO(): Executor? {
        return diskIO
    }

    fun mainThread(): Executor? {
        return mainThread
    }

    fun networkIO(): Executor? {
        return networkIO
    }

    private class MainThreadExecutor : Executor {
        private val mainThreadHandler = Handler(Looper.getMainLooper())
        override fun execute(command: Runnable) {
            mainThreadHandler.post(command)
        }
    }
}
package com.detectfrida.util

import com.detectfrida.data.TotalResult

interface IChecksResultListener {
    fun onProcessStarted()
    fun onUpdateResult(result: TotalResult)
    fun onProcessFinished(result: TotalResult)
}

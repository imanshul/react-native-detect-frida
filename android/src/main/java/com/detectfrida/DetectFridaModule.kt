/*
The MIT License (MIT)

Copyright (c) 2024  Anshul Thakur <anshulthakur939@gmail.com>

Permission is hereby granted, free of charge, to any person obtaining a copy of this software
and associated documentation files (the “Software”), to deal in the Software without
restriction, including without limitation the rights to use, copy, modify, merge, publish,
distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the
Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or
substantial portions of the Software.

THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING
BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */


package com.detectfrida

import com.detectfrida.constant.GeneralConst.CH_STATE_CHECKED_ERROR
import com.detectfrida.constant.GeneralConst.CH_STATE_CHECKED_ROOT_DETECTED
import com.detectfrida.constant.GeneralConst.CH_STATE_CHECKED_ROOT_NOT_DETECTED
import com.detectfrida.constant.GeneralConst.CH_STATE_STILL_GOING
import com.detectfrida.constant.GeneralConst.CH_STATE_UNCHECKED
import com.detectfrida.data.CheckInfo
import com.detectfrida.data.TotalResult
import com.detectfrida.util.CheckTask
import com.detectfrida.util.ChecksHelper
import com.detectfrida.util.IChecksResultListener
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.facebook.react.bridge.ReactMethod
import com.facebook.react.bridge.Promise
import java.util.ArrayList
import com.facebook.react.bridge.Arguments
import com.facebook.react.bridge.WritableArray
import com.facebook.react.bridge.WritableMap

class DetectFridaModule(private val reactContext: ReactApplicationContext) :
  ReactContextBaseJavaModule(reactContext) {

  private var mTask: CheckTask? = null

  override fun getName(): String {
    return NAME
  }

  // Example method
  // See https://reactnative.dev/docs/native-modules-android
  @ReactMethod
  fun detectRoot(promise: Promise) {
    mTask?.cancel(false)
    mTask = CheckTask(object : IChecksResultListener {
      override fun onProcessStarted() {
        // Implement your logic when the process starts
      }

      override fun onUpdateResult(result: TotalResult) {
        // Implement your logic to update the result
      }

      override fun onProcessFinished(result: TotalResult) {
        // Implement your logic when the process finishes
        if (mTask != null) {
          mTask = null
        }

        val mFullData: ArrayList<CheckInfo> = result.list as ArrayList<CheckInfo>


        when (result.checkState) {
          CH_STATE_CHECKED_ROOT_DETECTED -> {
            //The device is rooted
            promise.resolve(getDataToSend(true, result.list as ArrayList<CheckInfo>))
          }

          CH_STATE_CHECKED_ROOT_NOT_DETECTED -> {
            promise.resolve(getDataToSend(false, result.list as ArrayList<CheckInfo>))
          }

          CH_STATE_STILL_GOING -> {
            //Still fetching
          }

          CH_STATE_UNCHECKED -> {
            //Unchecked
          }

          CH_STATE_CHECKED_ERROR -> {
            promise.reject("Getting Error")
          }

          else -> {
            promise.reject("Unknown state of the result")
          }
        }
      }
    }, false)
    mTask!!.execute()
  }

  fun getDataToSend(isRooted: Boolean, checkInfoList: ArrayList<CheckInfo>): WritableMap {
    val resultData: WritableMap = Arguments.createMap()
    val checkInfoArray: WritableArray = Arguments.createArray()
    for (checkInfo in checkInfoList) {
      val checkInfoMap: WritableMap = Arguments.createMap()
      checkInfoMap.putBoolean("state", checkInfo.state ?: false)
      checkInfoMap.putInt("id", checkInfo.typeCheck)
      checkInfoMap.putString("name", reactContext.getString(ChecksHelper.getCheckStringId(checkInfo.typeCheck)))
      checkInfoArray.pushMap(checkInfoMap)
    }
    resultData.putBoolean("isRooted",isRooted)
    resultData.putArray("checkStatus", checkInfoArray)
    return resultData
  }

  companion object {
    const val NAME = "DetectFrida"
  }
}

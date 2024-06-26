package com.detectfrida.util

import android.os.AsyncTask

import com.detectfrida.constant.GeneralConst
import com.detectfrida.data.CheckInfo
import com.detectfrida.data.TotalResult

import java.util.ArrayList

import com.detectfrida.constant.GeneralConst.CH_STATE_CHECKED_ROOT_DETECTED
import com.detectfrida.constant.GeneralConst.CH_STATE_CHECKED_ROOT_NOT_DETECTED
import com.detectfrida.constant.GeneralConst.CH_STATE_UNCHECKED
import com.detectfrida.constant.GeneralConst.CH_TYPE_BUSYBOX_BINARY
import com.detectfrida.constant.GeneralConst.CH_TYPE_DANGEROUS_PROPS
import com.detectfrida.constant.GeneralConst.CH_TYPE_DEV_KEYS
import com.detectfrida.constant.GeneralConst.CH_TYPE_HOOKS
import com.detectfrida.constant.GeneralConst.CH_TYPE_NON_RELEASE_KEYS
import com.detectfrida.constant.GeneralConst.CH_TYPE_PERMISSIVE_SELINUX
import com.detectfrida.constant.GeneralConst.CH_TYPE_RESETPROP
import com.detectfrida.constant.GeneralConst.CH_TYPE_SUPER_USER_APK
import com.detectfrida.constant.GeneralConst.CH_TYPE_SU_BINARY
import com.detectfrida.constant.GeneralConst.CH_TYPE_SU_EXISTS
import com.detectfrida.constant.GeneralConst.CH_TYPE_TEST_KEYS
import com.detectfrida.constant.GeneralConst.CH_TYPE_WRONG_PATH_PERMITION
import com.detectfrida.constant.GeneralConst.CH_TYPE_XPOSED

class CheckTask(
    private var mListener: IChecksResultListener?,
    private val mIsEmptyResult: Boolean
) : AsyncTask<Void, TotalResult, TotalResult>() {

    override fun doInBackground(objects: Array<Void>): TotalResult? {
        var totalResult: TotalResult?
        val list = ArrayList<CheckInfo>()
        val checks = arrayOf(CH_TYPE_TEST_KEYS, CH_TYPE_DEV_KEYS, CH_TYPE_NON_RELEASE_KEYS, CH_TYPE_DANGEROUS_PROPS, CH_TYPE_PERMISSIVE_SELINUX, CH_TYPE_SU_EXISTS, CH_TYPE_SUPER_USER_APK,CH_TYPE_SU_BINARY, CH_TYPE_BUSYBOX_BINARY, CH_TYPE_XPOSED,CH_TYPE_RESETPROP, CH_TYPE_WRONG_PATH_PERMITION, CH_TYPE_HOOKS)
        for (item: Int in checks) {
            list.add(CheckInfo(null, item))
        }
        if (isCancelled) {
            mListener = null
            return null
        }
        totalResult = TotalResult(list, CH_STATE_UNCHECKED)
        publishProgress(totalResult)
        if (!mIsEmptyResult) {
            if (!RootDetectorHelper.instance.isLibraryLoaded) {
                totalResult = TotalResult(list, GeneralConst.CH_STATE_CHECKED_ERROR)
                return totalResult
            }
            var isFoundRoot = false
            for (item in list) {
                if (isCancelled) {
                    mListener = null
                    return null
                }
                try { /* Delay for UI only, feel free to remove it*/
                    Thread.sleep(30)
                } catch (e: InterruptedException) { /*do nothing*/ }
                when (item.typeCheck) {
                    CH_TYPE_TEST_KEYS -> item.state = RootDetectorHelper.instance.isDetectedTestKeys
                    CH_TYPE_DEV_KEYS -> item.state = RootDetectorHelper.instance.isDetectedDevKeys
                    CH_TYPE_NON_RELEASE_KEYS -> item.state = RootDetectorHelper.instance.isNotFoundReleaseKeys
                    CH_TYPE_DANGEROUS_PROPS -> item.state = RootDetectorHelper.instance.isFoundDangerousProps
                    CH_TYPE_PERMISSIVE_SELINUX -> item.state = RootDetectorHelper.instance.isPermissiveSelinux
                    CH_TYPE_SU_EXISTS -> item.state = RootDetectorHelper.instance.isSuExists
                    CH_TYPE_SUPER_USER_APK -> item.state = RootDetectorHelper.instance.isAccessedSuperuserApk
                    CH_TYPE_SU_BINARY -> item.state = RootDetectorHelper.instance.isFoundSuBinary
                    CH_TYPE_BUSYBOX_BINARY -> item.state = RootDetectorHelper.instance.isFoundBusyboxBinary
                    CH_TYPE_XPOSED -> item.state = RootDetectorHelper.instance.isFoundXposed
                    CH_TYPE_RESETPROP -> item.state = RootDetectorHelper.instance.isFoundResetprop
                    CH_TYPE_WRONG_PATH_PERMITION -> item.state = RootDetectorHelper.instance.isFoundWrongPathPermission
                    CH_TYPE_HOOKS -> item.state = RootDetectorHelper.instance.isFoundHooks
                }
                if (item.state != null && item.state === java.lang.Boolean.TRUE && !isFoundRoot) {
                    isFoundRoot = true
                }
                totalResult = TotalResult(list, if (isFoundRoot) CH_STATE_CHECKED_ROOT_DETECTED else CH_STATE_CHECKED_ROOT_NOT_DETECTED)
                publishProgress(totalResult)
            } //for (CheckInfo item : list)
            totalResult = TotalResult(list, if (isFoundRoot) CH_STATE_CHECKED_ROOT_DETECTED else CH_STATE_CHECKED_ROOT_NOT_DETECTED)
            return totalResult
        }
        return totalResult
    }

    override fun onProgressUpdate(vararg progress: TotalResult) {
        super.onProgressUpdate(*progress)
        if (isCancelled) {
            mListener = null
            return
        }
        if (progress.isNotEmpty()) {
            mListener?.onUpdateResult(progress[0])
        }
    }

    override fun onPostExecute(totalResult: TotalResult) {
        super.onPostExecute(totalResult)
        mListener?.onProcessFinished(totalResult)
    }

    override fun onPreExecute() {
        mListener?.onProcessStarted()
    }
}

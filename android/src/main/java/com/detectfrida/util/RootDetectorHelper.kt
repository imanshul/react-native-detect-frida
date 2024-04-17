package com.detectfrida.util

import com.detectfrida.BuildConfig

class RootDetectorHelper private constructor() {

    val isLibraryLoaded: Boolean
        get() {
            if (isLoaded) { return true }
            try {
                System.loadLibrary(LIB_NAME)
                isLoaded = true
            } catch (e: UnsatisfiedLinkError) {
                if (BuildConfig.DEBUG) { e.printStackTrace() }
            }
            return isLoaded
        }

    val isDetectedDevKeys: Boolean
        external get

    val isDetectedTestKeys: Boolean
        external get

    val isNotFoundReleaseKeys: Boolean
        external get

    val isFoundDangerousProps: Boolean
        external get

    val isPermissiveSelinux: Boolean
        external get

    val isSuExists: Boolean
        external get

    val isAccessedSuperuserApk: Boolean
        external get

    val isFoundSuBinary: Boolean
        external get

    val isFoundBusyboxBinary: Boolean
        external get

    val isFoundXposed: Boolean
        external get

    val isFoundResetprop: Boolean
        external get

    val isFoundWrongPathPermission: Boolean
        external get

    val isFoundHooks: Boolean
        external get

    private object InstanceHolder {
         val INSTANCE = RootDetectorHelper()
    }

    companion object {
        private val LIB_NAME = "native-lib"
        private var isLoaded: Boolean = false

        val instance: RootDetectorHelper
            get() = InstanceHolder.INSTANCE
    }
}

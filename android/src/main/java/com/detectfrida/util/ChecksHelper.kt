package com.detectfrida.util

import androidx.annotation.StringRes

import com.detectfrida.R

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
import com.detectfrida.constant.GeneralConst.CH_TYPE_UNKNOWN
import com.detectfrida.constant.GeneralConst.CH_TYPE_WRONG_PATH_PERMITION
import com.detectfrida.constant.GeneralConst.CH_TYPE_XPOSED

object ChecksHelper {
    @StringRes
    fun getCheckStringId(typeCheck: Int): Int = when (typeCheck) {
        CH_TYPE_TEST_KEYS -> R.string.checks_desc_1
        CH_TYPE_DEV_KEYS -> R.string.checks_desc_2
        CH_TYPE_NON_RELEASE_KEYS -> R.string.checks_desc_3
        CH_TYPE_DANGEROUS_PROPS -> R.string.checks_desc_4
        CH_TYPE_PERMISSIVE_SELINUX -> R.string.checks_desc_5
        CH_TYPE_SU_EXISTS -> R.string.checks_desc_6
        CH_TYPE_SUPER_USER_APK -> R.string.checks_desc_7
        CH_TYPE_SU_BINARY -> R.string.checks_desc_8
        CH_TYPE_BUSYBOX_BINARY -> R.string.checks_desc_9
        CH_TYPE_XPOSED -> R.string.checks_desc_10
        CH_TYPE_RESETPROP -> R.string.checks_desc_11
        CH_TYPE_WRONG_PATH_PERMITION -> R.string.checks_desc_12
        CH_TYPE_HOOKS -> R.string.checks_desc_13
        CH_TYPE_UNKNOWN -> R.string.empty
        else -> R.string.empty
    }
}

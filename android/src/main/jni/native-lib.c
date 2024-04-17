/*
Copyright (c) 2024  Anshul Thakur <anshulthakur939@gmail.com>
 */

#include <jni.h>
#include "../../../distribution/meat_grinder.h"


JNIEXPORT jboolean JNICALL
Java_com_detectfrida_util_RootDetectorHelper_isDetectedTestKeys(
        JNIEnv *env,
        jobject  this ) {

    return (jboolean) isDetectedTestKeys();
}

JNIEXPORT jboolean JNICALL
Java_com_detectfrida_util_RootDetectorHelper_isDetectedDevKeys(
        JNIEnv *env,
        jobject  this ) {

    return (jboolean) isDetectedDevKeys();
}

JNIEXPORT jboolean JNICALL
Java_com_detectfrida_util_RootDetectorHelper_isNotFoundReleaseKeys(
        JNIEnv *env,
        jobject  this ) {

    return (jboolean) isNotFoundReleaseKeys();
}

JNIEXPORT jboolean JNICALL
Java_com_detectfrida_util_RootDetectorHelper_isFoundDangerousProps(
        JNIEnv *env,
        jobject  this ) {

    return (jboolean) isFoundDangerousProps();
}

JNIEXPORT jboolean JNICALL
Java_com_detectfrida_util_RootDetectorHelper_isPermissiveSelinux(
        JNIEnv *env,
        jobject  this ) {

    return (jboolean) isPermissiveSelinux();
}

JNIEXPORT jboolean JNICALL
Java_com_detectfrida_util_RootDetectorHelper_isSuExists(
        JNIEnv *env,
        jobject  this ) {

    return (jboolean) isSuExists();
}

JNIEXPORT jboolean JNICALL
Java_com_detectfrida_util_RootDetectorHelper_isAccessedSuperuserApk(
        JNIEnv *env,
        jobject  this ) {

    return (jboolean) isAccessedSuperuserApk();
}

JNIEXPORT jboolean JNICALL
Java_com_detectfrida_util_RootDetectorHelper_isFoundSuBinary(
        JNIEnv *env,
        jobject  this ) {

    return (jboolean) isFoundSuBinary();
}

JNIEXPORT jboolean JNICALL
Java_com_detectfrida_util_RootDetectorHelper_isFoundBusyboxBinary(
        JNIEnv *env,
        jobject  this ) {

    return (jboolean) isFoundBusyboxBinary();
}


JNIEXPORT jboolean JNICALL
Java_com_detectfrida_util_RootDetectorHelper_isFoundXposed(
        JNIEnv *env,
        jobject  this ) {

    return (jboolean) isFoundXposed();
}

JNIEXPORT jboolean JNICALL
Java_com_detectfrida_util_RootDetectorHelper_isFoundResetprop(
        JNIEnv *env,
        jobject  this ) {

    return (jboolean) isFoundResetprop();
}

JNIEXPORT jboolean JNICALL
Java_com_detectfrida_util_RootDetectorHelper_isFoundWrongPathPermission(
        JNIEnv *env,
        jobject  this ) {

    return (jboolean) isFoundWrongPathPermission();
}

JNIEXPORT jboolean JNICALL
Java_com_detectfrida_util_RootDetectorHelper_isFoundHooks(
        JNIEnv *env,
        jobject  this ) {

    return (jboolean) isFoundHooks();
}

#ifndef MEAT_GRINDER_MEAT_GRINDER_H
#define MEAT_GRINDER_MEAT_GRINDER_H

#include <sys/system_properties.h>
#include <sys/types.h>
#include <android/log.h>
#include <jni.h>

#include <string.h>
#include <stdlib.h>
#include <stdbool.h>
#include <mntent.h>
#include <unistd.h>
#include "const_general.h"

#include "const_properties.h"



#define GR_LOG_TAG "RootDetectorHelper"

#ifndef NDEBUG

#define GR_LOGI(...)  __android_log_print(ANDROID_LOG_INFO,GR_LOG_TAG,__VA_ARGS__)
#define GR_LOGE(...)  __android_log_print(ANDROID_LOG_ERROR,GR_LOG_TAG,__VA_ARGS__)
#define GR_LOGW(...)  __android_log_print(ANDROID_LOG_WARN,GR_LOG_TAG,__VA_ARGS__)
#define GR_LOGD(...)  __android_log_print(ANDROID_LOG_DEBUG,GR_LOG_TAG,__VA_ARGS__)
#define GR_LOGV(...)  __android_log_print(ANDROID_LOG_VERBOSE,GR_LOG_TAG,__VA_ARGS__)

#else //NDEBUG

#define GR_LOGI(...)
#define GR_LOGE(...)
#define GR_LOGW(...)
#define GR_LOGD(...)
#define GR_LOGV(...)

#endif //NDEBUG

bool isDetectedTestKeys();

bool isDetectedDevKeys();

bool isNotFoundReleaseKeys();

bool isFoundDangerousProps();

bool isPermissiveSelinux();

bool isSuExists();

bool isAccessedSuperuserApk();

bool isFoundSuBinary();

bool isFoundBusyboxBinary();

bool isFoundXposed();

bool isFoundResetprop();

bool isFoundWrongPathPermission();

//http://d3adend.org/blog/?p=589 Stab 4: Use /proc/[pid]/maps to detect suspicious shared objects or JARs loaded into memory.
bool isFoundHooks();

#endif //MEAT_GRINDER_MEAT_GRINDER_H

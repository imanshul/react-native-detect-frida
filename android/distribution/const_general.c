#include "const_general.h"

//TODO Magisk
const char * const MG_SU_PATH[] = {
        "/data/local/",
        "/data/local/bin/",
        "/data/local/xbin/",
        "/sbin/",
        "/system/bin/",
        "/system/bin/.ext/",
        "/system/bin/failsafe/",
        "/system/sd/xbin/",
        "/su/xbin/",
        "/su/bin/",
        "/magisk/.core/bin/",
        "/system/usr/we-need-root/",
        "/system/xbin/",
        0
};

const char * const MG_EXPOSED_FILES[] = {
        "/system/lib/libxposed_art.so",
        "/system/lib64/libxposed_art.so",
        "/system/xposed.prop",
        "/cache/recovery/xposed.zip",
        "/system/framework/XposedBridge.jar",
        "/system/bin/app_process64_xposed",
        "/system/bin/app_process32_xposed",
        "/magisk/xposed/system/lib/libsigchain.so",
        "/magisk/xposed/system/lib/libart.so",
        "/magisk/xposed/system/lib/libart-disassembler.so",
        "/magisk/xposed/system/lib/libart-compiler.so",
        "/system/bin/app_process32_orig",
        "/system/bin/app_process64_orig",
        "/system/lib/libmemtrack_real.so",
        "/system/lib64/libmemtrack_real.so",
        "/system/lib/libriru_edxp.so",
        "/system/lib64/libriru_edxp.so",
        "/system/lib/libwhale.edxp.so",
        "/system/lib64/libwhale.edxp.so",
        "/system/framework/edxp.jar",
        0
};

const char * const MG_READ_ONLY_PATH[] = {
        "/system",
        "/system/bin",
        "/system/sbin",
        "/system/xbin",
        "/vendor/bin",
        "/sbin",
        "/etc",
        0
};


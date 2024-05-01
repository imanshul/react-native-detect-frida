#import "JailBrokenHelper.h"
#import <UIKit/UIKit.h>
#import <mach-o/dyld.h>
#import <objc/runtime.h>

@implementation JailBrokenHelper

+ (BOOL)isSimulator {
    return TARGET_OS_SIMULATOR != 0;
}

+ (BOOL)hasCydiaInstalled {
    return [[UIApplication sharedApplication] canOpenURL:[NSURL URLWithString:@"cydia://"]];
}

+ (BOOL)hasUndecimusInstalled {
    return [[UIApplication sharedApplication] canOpenURL:[NSURL URLWithString:@"undecimus://"]];
}

+ (BOOL)hasSileoInstalled {
    return [[UIApplication sharedApplication] canOpenURL:[NSURL URLWithString:@"sileo://"]];
}

+ (BOOL)hasZbraInstalled {
    return [[UIApplication sharedApplication] canOpenURL:[NSURL URLWithString:@"zbra://"]];
}

+ (BOOL)isContainsSuspiciousApps {
    NSArray *suspiciousAppsPathToCheck = @[
        @"/Applications/Cydia.app",
        @"/Applications/blackra1n.app",
        @"/Applications/FakeCarrier.app",
        @"/Applications/Icy.app",
        @"/Applications/IntelliScreen.app",
        @"/Applications/MxTube.app",
        @"/Applications/RockApp.app",
        @"/Applications/SBSettings.app",
        @"/Applications/WinterBoard.app",
        @"/Applications/Shadow.app",
        @"/Applications/IntelliScreen.app",
        @"/Applications/Sileo.app",
        @"/Applications/FlyJB.app",
        @"/Applications/Zebra.app"
    ];

    for (NSString *path in suspiciousAppsPathToCheck) {
        if ([[NSFileManager defaultManager] fileExistsAtPath:path]) {
            return YES;
        }
    }
    return NO;
}

+ (BOOL)isSuspiciousSystemPathsExists {
    NSArray *suspiciousSystemPathsToCheck = @[
        @"/Library/MobileSubstrate/MobileSubstrate.dylib",
        @"/Library/MobileSubstrate/DynamicLibraries/LiveClock.plist",
        @"/Library/MobileSubstrate/DynamicLibraries/Veency.plist",
        @"/private/var/lib/apt",
        @"/private/var/lib/apt/",
        @"/private/var/lib/cydia",
        @"/private/var/mobile/Library/SBSettings/Themes",
        @"/private/var/stash",
        @"/private/var/tmp/cydia.log",
        @"/System/Library/LaunchDaemons/com.ikey.bbot.plist",
        @"/System/Library/LaunchDaemons/com.saurik.Cydia.Startup.plist",
        @"/usr/bin/sshd",
        @"/usr/libexec/sftp-server",
        @"/usr/sbin/sshd",
        @"/etc/apt",
        @"/bin/bash",
        @"/etc/apt/sources.list.d/electra.list",
        @"/usr/lib/libcycript.dylib",
        @"/usr/bin/cycript",
        @"/usr/sbin/frida-server",
        @"/etc/sshd_config",
        @"/Library/Shadow/Rulesets",
        @"/usr/bin/ssh",
        @"/var/mobile/Library/Preferences/ABPattern",
        @"/usr/lib/ABDYLD.dylib",
        @"/usr/lib/ABSubLoader.dylib",
        @"/etc/apt/sources.list.d/sileo.sources",
        @"/.bootstrapped_electra",
        @"/usr/lib/libjailbreak.dylib",
        @"/jb/lzma",
        @"/.cydia_no_stash",
        @"/.installed_unc0ver",
        @"/jb/offsets.plist",
        @"/usr/share/jailbreak/injectme.plist",
        @"/etc/apt/undecimus/undecimus.list",
        @"/var/lib/dpkg/info/mobilesubstrate.md5sums",
        @"/jb/jailbreakd.plist",
        @"/jb/amfid_payload.dylib",
        @"/jb/libjailbreak.dylib",
        @"/usr/libexec/cydia/firmware.sh",
        @"/var/lib/cydia",
        @"/private/var/Users/",
        @"/var/log/apt",
        @"/private/var/cache/apt/",
        @"/private/var/log/syslog",
        @"/Library/MobileSubstrate/CydiaSubstrate.dylib",
        @"/var/binpack",
        @"/Library/PreferenceBundles/LibertyPref.bundle",
        @"/Library/PreferenceBundles/ShadowPreferences.bundle",
        @"/Library/PreferenceBundles/ABypassPrefs.bundle",
        @"/Library/PreferenceBundles/FlyJBPrefs.bundle",
        @"/Library/PreferenceBundles/Cephei.bundle",
        @"/Library/PreferenceBundles/SubstitutePrefs.bundle",
        @"/Library/PreferenceBundles/libhbangprefs.bundle",
        @"/usr/lib/libhooker.dylib",
        @"/usr/lib/libsubstitute.dylib",
        @"/usr/lib/substrate",
        @"/usr/lib/TweakInject",
        @"/var/binpack/Applications/loader.app",
        @"/Library/BawAppie/ABypass",
        @"/Library/MobileSubstrate/DynamicLibraries/SSLKillSwitch2.plist",
        @"/Library/MobileSubstrate/DynamicLibraries/PreferenceLoader.plist",
        @"/Library/MobileSubstrate/DynamicLibraries/PreferenceLoader.dylib",
        @"/Library/MobileSubstrate/DynamicLibraries",
        @"/var/mobile/Library/Preferences/me.jjolano.shadow.plist"
    ];

    for (NSString *path in suspiciousSystemPathsToCheck) {
        if ([[NSFileManager defaultManager] fileExistsAtPath:path]) {
            return YES;
        }
    }
    return NO;
}

+ (BOOL)canEditSystemFiles {
    NSString *jailBreakText = @"Developer Insider";
    NSError *error = nil;
    BOOL success = [jailBreakText writeToFile:jailBreakText atomically:YES encoding:NSUTF8StringEncoding error:&error];
    return success;
}

+ (BOOL)checkDYLD {
    NSArray *suspiciousLibraries = @[
        @"SubstrateLoader.dylib",
        @"SSLKillSwitch2.dylib",
        @"SSLKillSwitch.dylib",
        @"MobileSubstrate.dylib",
        @"TweakInject.dylib",
        @"CydiaSubstrate",
        @"cynject",
        @"CustomWidgetIcons",
        @"PreferenceLoader",
        @"RocketBootstrap",
        @"WeeLoader",
        @"/.file", // HideJB (2.1.1) changes full paths of the suspicious libraries to "/.file"
        @"libhooker",
        @"SubstrateInserter",
        @"SubstrateBootstrap",
        @"ABypass",
        @"FlyJB",
        @"Substitute",
        @"Cephei",
        @"Electra",
        @"AppSyncUnified-FrontBoard.dylib",
        @"Shadow",
        @"FridaGadget",
        @"frida",
        @"libcycript"
    ];

    uint32_t count = _dyld_image_count();
    for (uint32_t i = 0; i < count; i++) {
        const char *imageName = _dyld_get_image_name(i);
        NSString *imageNameStr = [NSString stringWithUTF8String:imageName];

        for (NSString *library in suspiciousLibraries) {
            if ([imageNameStr localizedCaseInsensitiveContainsString:library]) {
                return YES;
            }
        }
    }
    return NO;
}

+ (BOOL)checkSuspiciousObjCClasses {
    Class shadowRulesetClass = objc_getClass("ShadowRuleset");
    SEL selector = NSSelectorFromString(@"internalDictionary");
    if (class_getInstanceMethod(shadowRulesetClass, selector) != nil) {
        return YES;
    }
    return NO;
}

@end

#import "DetectFrida.h"
#import "JailBrokenHelper.h"

@implementation DetectFrida
RCT_EXPORT_MODULE()

// Example method
// See // https://reactnative.dev/docs/native-modules-ios
RCT_EXPORT_METHOD(multiply:(double)a
                  b:(double)b
                  resolve:(RCTPromiseResolveBlock)resolve
                  reject:(RCTPromiseRejectBlock)reject)
{
    NSNumber *result = @(a * b);

    resolve(result);
}

+ (BOOL)requiresMainQueueSetup {
    return YES;
}

- (void)closeApp:(double)delay {
    dispatch_after(dispatch_time(DISPATCH_TIME_NOW, (int64_t)(delay * NSEC_PER_SEC)), dispatch_get_main_queue(), ^{
        exit(0);
    });
}

- (BOOL)isJailBroken {
    if ([JailBrokenHelper isSimulator]) { return NO; }
    if ([JailBrokenHelper hasCydiaInstalled]) { return YES; }
    if ([JailBrokenHelper hasUndecimusInstalled]) { return YES; }
    if ([JailBrokenHelper hasSileoInstalled]) { return YES; }
    if ([JailBrokenHelper hasZbraInstalled]) { return YES; }
    if ([JailBrokenHelper isContainsSuspiciousApps]) { return YES; }
    if ([JailBrokenHelper checkDYLD]) { return YES; }
    if ([JailBrokenHelper checkSuspiciousObjCClasses]) { return YES; }
    if ([JailBrokenHelper isSuspiciousSystemPathsExists]) { return YES; }
    return [JailBrokenHelper canEditSystemFiles];
}

// RCT_EXPORT_METHOD(isJailBroken:(RCTResponseSenderBlock)callback) {
//     BOOL isJailBroken = [self isJailBroken];
//     callback(@[@(isJailBroken)]);
// }

RCT_REMAP_METHOD(isJailBroken,
                 isJailBrokenWithResolver:(RCTPromiseResolveBlock)resolve
                 rejecter:(RCTPromiseRejectBlock)reject) {
    @try {
        BOOL isJailBroken = [self isJailBroken];
        resolve(@{@"isRooted": @(isJailBroken)});
    }
    @catch (NSException *exception) {
        NSError *error = [NSError errorWithDomain:@"jailbreakchecker" code:1 userInfo:@{NSLocalizedDescriptionKey: exception.reason ?: @"Failed to determine jailbreak status."}];
        reject(@"jailbreak_status_error", @"Failed to determine jailbreak status.", error);
    }
}

RCT_EXPORT_METHOD(closeAppAfterDelay:(double)delay) {
    [self closeApp:delay];
}



@end

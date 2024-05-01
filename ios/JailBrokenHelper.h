#import <Foundation/Foundation.h>

@interface JailBrokenHelper : NSObject

+ (BOOL)isSimulator;
+ (BOOL)hasCydiaInstalled;
+ (BOOL)hasUndecimusInstalled;
+ (BOOL)hasSileoInstalled;
+ (BOOL)hasZbraInstalled;
+ (BOOL)isContainsSuspiciousApps;
+ (BOOL)isSuspiciousSystemPathsExists;
+ (BOOL)canEditSystemFiles;
+ (BOOL)checkDYLD;
+ (BOOL)checkSuspiciousObjCClasses;

@end

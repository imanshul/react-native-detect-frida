
#ifdef RCT_NEW_ARCH_ENABLED
#import "RNDetectFridaSpec.h"

@interface DetectFrida : NSObject <NativeDetectFridaSpec>
#else
#import <React/RCTBridgeModule.h>

@interface DetectFrida : NSObject <RCTBridgeModule>
#endif

@end

import { NativeModules, Platform } from 'react-native';
import type { RootCheckResult } from './ValueTypes';

const LINKING_ERROR =
  `The package 'react-native-detect-frida' doesn't seem to be linked. Make sure: \n\n` +
  Platform.select({ ios: "- You have run 'pod install'\n", default: '' }) +
  '- You rebuilt the app after installing the package\n' +
  '- You are not using Expo Go\n';

const DetectFrida = NativeModules.DetectFrida
  ? NativeModules.DetectFrida
  : new Proxy(
      {},
      {
        get() {
          throw new Error(LINKING_ERROR);
        },
      }
    );

const isAndroid = () => {
  return Platform.OS === 'android';
};

export function isDeviceRooted(): Promise<RootCheckResult> {
  if (isAndroid()) {
    return DetectFrida.detectRoot();
  } else {
    return DetectFrida.isJailBroken();
  }
}

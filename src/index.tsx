import { NativeModules, Platform } from 'react-native';
import type { RootCheckResult } from './ValueTypes';
import NativeDetectFrida from './NativeDetectFrida';

const DetectFrida = NativeDetectFrida
  ? NativeDetectFrida
  : NativeModules.DetectFrida;

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

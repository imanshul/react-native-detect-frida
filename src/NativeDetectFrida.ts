import type { TurboModule } from 'react-native';
import { TurboModuleRegistry } from 'react-native';

export interface Spec extends TurboModule {
  multiply(a: number, b: number): Promise<number>;
  isJailBroken(): Promise<{ isRooted: boolean }>;
  closeAppAfterDelay(delay: number): void;
}

export default TurboModuleRegistry.getEnforcing<Spec>('DetectFrida');

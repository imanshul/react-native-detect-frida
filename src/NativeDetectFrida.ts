import type { TurboModule } from 'react-native';
import { TurboModuleRegistry } from 'react-native';

export interface Spec extends TurboModule {
  /**
   * Android only — returns detailed root check info.
   */
  detectRoot?(): Promise<{
    isRooted: boolean;
    checkStatus: { id: number; name: string; state: boolean }[];
  }>;

  /**
   * iOS only — returns basic jailbreak status.
   */
  isJailBroken?(): Promise<{ isRooted: boolean }>;

  /**
   * iOS only — force quit the app after delay.
   */
  closeAppAfterDelay?(delay: number): void;
}

export default TurboModuleRegistry.getEnforcing<Spec>('DetectFrida');

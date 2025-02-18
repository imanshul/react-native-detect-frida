export interface CheckStatus {
  id: number;
  name: string;
  state: boolean;
}

// eslint-disable-next-line @typescript-eslint/no-unused-vars
export interface RootCheckResult {
  isRooted: boolean;
  checkStatus: CheckStatus[];
}

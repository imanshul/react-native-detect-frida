export interface CheckStatus {
  id: number;
  name: string;
  state: boolean;
}

export interface RootCheckResult {
  isRooted: boolean;
  checkStatus: CheckStatus[];
}

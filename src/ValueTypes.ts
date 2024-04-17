interface CheckStatus {
  id: number;
  name: string;
  state: boolean;
}

interface RootCheckResult {
  isRooted: boolean;
  checkStatus: CheckStatus[];
}

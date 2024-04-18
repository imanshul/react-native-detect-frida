# react-native-detect-frida

[![Version](https://img.shields.io/npm/v/react-native-detect-frida)](https://www.npmjs.com/package/react-native-detect-frida)

A React-Native library to detect if frida server is running or not

Remember! Detecting and hiding root are a cat and mouse game.

<a href="https://www.buymeacoffee.com/anshulthakur" target="_blank"><img src="https://cdn.buymeacoffee.com/buttons/default-orange.png" alt="Buy Me A Coffee" height="41" width="174"></a>

## Installation

```sh
npm install react-native-detect-frida
```

## Usage

```js
import { isDeviceRooted } from 'react-native-detect-frida';

// ...

const result = await isDeviceRooted();
```

| Method           | Returns                    | Description                                                    |
| ---------------- | -------------------------- | -------------------------------------------------------------- |
| `isDeviceRooted` | `Promise<RootCheckResult>` | Returns promise containing key whether device is rooted or not |


```ts
interface CheckStatus {
  id: number;
  name: string;
  state: boolean;
}

interface RootCheckResult {
  isRooted: boolean;
  checkStatus: CheckStatus[];
}
```

### Platform Supported:

- [x] Android
- [ ] iOS

### Screenshot

![Circle of Trust](./apk/app.png)



# Root checks

These are the native checks:

- TEST KEYS
- DEV KEYS
- NON RELEASE KEYS
- DANGEROUS PROPS
- PERMISSIVE SELINUX
- SU EXISTS
- SUPERUSER APK
- SU BINARY
- BUSYBOX BINARY
- XPOSED
- RESETPROP(EXPERIMENTAL)
- WRONG PATH PERMITION
- HOOKS

### False positives

Note that sometimes the `bool isFoundBusyboxBinary()` method can return a false positive.
This is cause the manufacturer of the device rom has left the busybox binary.
This alone doesn't mean that the device is rooted.

The following devices are known have the busybox binary on the stock rom:

- Part of the OnePlus Devices
- Part of the Xiaomi Devices

### Thanks

- [Dmitrii Kozhevin](mailto://kozhevin.dima@gmail.com) for native [C module](https://github.com/DimaKoz/meat-grinder)

## Contributing

See the [contributing guide](CONTRIBUTING.md) to learn how to contribute to the repository and the development workflow.

## License

MIT License

Copyright (c) 2024 Anshul Thakur <anshulthakur939@gmail.com>

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.

---

Made with :heart: by [Anshul Thakur](https://in.linkedin.com/in/anshul-thakur)

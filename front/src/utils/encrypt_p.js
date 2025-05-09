//AES加密
// AES加密
import CryptoJS from 'crypto-js/crypto-js'
export function encryptPassword(passWord) {
  let key1 = CryptoJS.enc.Utf8.parse(process.env.KEYSTR);
  let iv = CryptoJS.enc.Utf8.parse(process.env.IVSTR);
  let srcs = CryptoJS.enc.Utf8.parse(passWord);
  var encrypted = CryptoJS.AES.encrypt(srcs, key1, {
    iv: iv,
    mode: CryptoJS.mode.CBC,
    padding: CryptoJS.pad.ZeroPadding
  });
  return CryptoJS.enc.Base64.stringify(encrypted.ciphertext);
}

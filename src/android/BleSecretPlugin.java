package cordova.plugin.ble.secret;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * This class echoes a string called from JavaScript.
 */
public class BleSecretPlugin extends CordovaPlugin {
    private BlePackage blePackage;

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        blePackage = new BlePackage();

        if (action.equals("encode")) {
            String message = args.getString(0);
            this.encode("encode" + message, callbackContext);
            return true;
        } else if (action.equals("decode")) {
            String message = args.getString(0);
            this.decode("decode" + message, callbackContext);
            return true;
        }
        return false;
    }

    private void encode(String message, CallbackContext callbackContext) {
        if (message != null && message.length() > 0) {
            String result = cryptToHexString("encode", message);
            callbackContext.success(result);
        } else {
            callbackContext.error("Expected one non-empty string argument.");
        }
    }

    private void decode(String message, CallbackContext callbackContext) {
        if (message != null && message.length() > 0) {
            String result = cryptToHexString("decode", message);
            callbackContext.success(result);
        } else {
            callbackContext.error("Expected one non-empty string argument.");
        }
    }

    private String cryptToHexString(String cryptType, String inputStr){
        String result = "";
        if(inputStr.trim().length() <1){
            return;
        }
        String[] hexArray = inputStr.trim().split(" ");

        byte[] bytes = new byte[hexArray.length];
        int count = 0;
        for (int i=0;i<hexArray.length;i++){
            if(hexArray[i].trim().isEmpty()){
                continue;
            }
            byte[] singleByte = hexString2Bytes(hexArray[i]);
            if(singleByte.length!=1){
                return;
            }

            bytes[count] = singleByte[0];
            count++;
        }
        if ("encode".equals(cryptType))
        {
            byte[] encode = blePackage.encode(bytes,count);
            result = bytes2HexString(encode);
        }else if ("decode".equals(cryptType))
        {
            byte[] decode = blePackage.decode(bytes,count);
            result = bytes2HexString(decode);
        } else
        {
            result = "Unknown crypt type.";
        }
        return result;
    }

    private String bytes2HexString(byte[] b) {
        StringBuffer result = new StringBuffer();
        String hex;
        for (int i = 0; i < b.length; i++) {
            hex = Integer.toHexString(b[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            result.append(hex.toUpperCase() + " ");
        }
        return result.toString().trim();
    }
    private byte[] hexString2Bytes(String str) {
        if(str == null || str.trim().equals("")) {
            return new byte[0];
        }

        byte[] bytes = new byte[str.length() / 2];
        for(int i = 0; i < str.length() / 2; i++) {
            String subStr = str.substring(i * 2, i * 2 + 2);
            bytes[i] = (byte) Integer.parseInt(subStr, 16);
        }

        return bytes;
    }
}

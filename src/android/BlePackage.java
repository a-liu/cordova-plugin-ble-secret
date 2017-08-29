package cordova.plugin.ble.secret;

/**
 * Created by admin on 2017/6/10.
 */

public class BlePackage {
    static {
        System.loadLibrary("DataPackage");
    }

    public native byte[] encode(byte[] srcStr,int len);
    public native byte[] decode(byte[] encryptStr,int len);
}

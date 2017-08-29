var exec = require('cordova/exec');

module.exports = {
    encode: function (arg0, success, error) {
        exec(success, error, "BleSecret", "encode", [arg0]);
    },
    decode: function (arg0, success, error) {
        exec(success, error, "BleSecret", "decode", [arg0]);
    }
}


var exec = require('cordova/exec');

module.exports = {
    encode: function (arg0, succedoes, error) {
        exec(success, error, "BleSecretPlugin", "encode", [arg0]);
    },
    decode: function (arg0, success, error) {
        exec(success, error, "BleSecretPlugin", "decode", [arg0]);
    }
}


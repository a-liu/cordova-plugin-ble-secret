var exec = require('cordova/exec');
exports.encode = function (arg0, success, error) {
    exec(success, error, "BleSecret", "encode", [arg0]);
}

exports.decode = function (arg0, success, error) {
    exec(success, error, "BleSecret", "decode", [arg0]);
}
// module.exports = {
//     encode: function (arg0, success, error) {
//         exec(success, error, "BleSecret", "encode", [arg0]);
//     },
//     decode: function (arg0, success, error) {
//         exec(success, error, "BleSecret", "decode", [arg0]);
//     }
// }


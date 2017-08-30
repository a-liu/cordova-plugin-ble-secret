1. Add plugin by git link.
ionic cordova plugin add https://github.com/a-liu/cordova-plugin-ble-secret.git

2. Use it in your tsfile.
declare var ble_secret: any;

submit () {
    ble_secret.encode('09 A9 80 47',  function(success) {
      alert(success);
    }, function (error) {
      alert(error);
    });
}


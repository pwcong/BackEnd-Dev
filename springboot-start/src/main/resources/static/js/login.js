define(function (require) {

    var $ = require('jquery');
    require('bootstrap');

    var loginTip = $('#loginTip');
    var inputUsername = $('#inputUsername');
    inputUsername.change(function () {
        loginTip.hide();
    });
    var inputPassword = $('#inputPassword');
    inputPassword.change(function () {
        loginTip.hide();
    });

    $('#loginForm').submit(function (e) {

        var username = inputUsername.val();
        var password = inputPassword.val();

        $.post(CONFIG.contextPath + 'auth/login', {
            username: username,
            password: password
        }).then(function (res) {

            try {
                if (res && res.code === 0) {
                    loginTip.removeClass('alert-danger');
                    loginTip.addClass('alert-success');
                    loginTip.text(res.msg);
                    setTimeout(function () {
                        window.open(CONFIG.contextPath, '_self');
                    }, 2000);
                } else {
                    loginTip.text(res.msg);
                }
                loginTip.show();
            } catch (e) {
                loginTip.text('Unknown error');
                loginTip.show();
            }

        });

        return false;

    });

});
(function ($) {
    "use strict";

    // Login Form
    //----------------------------------------------
    // Validation
    $("#login_form").validate({
        rules: {
            login_email: {
                required: true,
                email: true
            },
            login_password: "required"
        },
        errorClass: "form-invalid"
    });
})(jQuery);
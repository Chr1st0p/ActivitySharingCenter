(function ($) {
    "use strict";

    // Login Form
    //----------------------------------------------
    // Validation
    $("#login-form").validate({
        rules: {
            login_email: "required",
            login_password: "required"
        },
        errorClass: "form-invalid"
    });

    // Register Form
    //----------------------------------------------
    // Validation
    jQuery.validator.addMethod("phoneSG", function(phone_number, element) {
        phone_number = phone_number.replace(/\s+/g, "");
        return this.optional(element) || phone_number.length === 8 && 
        phone_number.match(/^([89]+)([0-9]*)$/);
    }, "Please specify a valid phone number");

    $("#register-form").validate({
        rules: {
            reg_fullname: "required",
            reg_email: {
                required: true,
                email: true
            },
            reg_password: {
                required: true,
                minlength: 5
            },
            reg_password_confirm: {
                required: true,
                equalTo: "#register-form [name=reg_password]"
            },
            reg_phone: {
                required: true,
                phoneSG: true
            },
            reg_agree: "required"
        },
        errorClass: "form-invalid",
        errorPlacement: function (label, element) {
            if (element.attr("type") === "checkbox" || element.attr("type") === "radio") {
                element.parent().append(label); // this would append the label after all your checkboxes/labels (so the error-label will be the last element in <div class="controls"> )
            } else {
                label.insertAfter(element); // standard behaviour
            }
        }
    });


})(jQuery);
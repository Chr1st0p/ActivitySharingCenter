(function ($) {
    "use strict";

    // Register Form
    //----------------------------------------------
    // Validation
    jQuery.validator.addMethod("NRIC", function(nric, element) {
        nric = nric.replace(/\s+/g, "");
        return this.optional(element) || nric.length === 9 && 
        nric.match(/^[SGFsgf]{1}[0-9]{7}[a-zA-Z]{1}$/);
    }, "Please enter a valid NRIC");

    $("#register_form").validate({
        rules: {
            reg_name: {
                required: true,
                maxlength: 63
            },
            reg_userId: {
                required: true,
                maxlength: 10
            },
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
                equalTo: "#register_form [name=reg_password]"
            },
            reg_nric: {
                NRIC: true
            },
            reg_roleId: "required"
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

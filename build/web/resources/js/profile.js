$().ready(function() {
    "use strict";

    // Profile Form
    //----------------------------------------------
    // Validation
    jQuery.validator.addMethod("birth", function(value, element) {
        if (length(value) === 0) return true;
        var minDate = Date.parse("01/01/1990");  
        var today = new Date();
        var DOB = Date.parse(value);  
        if((DOB <= today && DOB >= minDate)) {  
            return true;  
        }  
        return false;
    }, "Please specify a valid date of birth");

    $("#profile-form").validate({
        rules: {
            DOB: {
                birth: true
            }
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
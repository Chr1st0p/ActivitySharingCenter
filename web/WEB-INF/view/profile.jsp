<c:if test="${result != 'none'}">
    <div class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" id="myModal">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="myModalLabel">${result}</h4>
                </div>
                <div class="modal-body">
                    <c:if test="${msg == 'nickname'}">
                        <p>This Nickname already existed.</p>
                    </c:if>
                    <c:if test="${msg == 'none'}">
                        <p>The profile has been modified.</p>
                    </c:if>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Dismiss</button>
                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal-dialog -->
    </div><!-- /.modal -->
</c:if>
<div class="container" style="margin-top: 80px;">
    <form role="form" id="profile-form" name="profile-form" action="Profile" method="POST" style="width: 600px; margin-bottom: 80px;" class="form-horizontal">
        <!-- Text input-->
        <div class="form-group">
            <label class="col-md-6 control-label" for="Nickname">Nickname</label>  
            <div class="col-md-6">
                <input id="Nickname" name="Nickname" type="text" placeholder="Nickname" class="form-control input-md" value="${nickname}">
                <span class="help-block">Will display "User"+User_Id if not set.</span>
            </div>
        </div>

        <!-- Multiple Radios (inline) -->
        <div class="form-group">
            <label class="col-md-6 control-label" for="Gender">Gender</label>
            <div class="col-md-6"> 
                <label class="radio-inline" for="Gender-0">
                    <input type="radio" name="Gender" id="Gender-0" value="0" checked="checked">
                    Not Shown
                </label> 
                <label class="radio-inline" for="Gender-1">
                    <input type="radio" name="Gender" id="Gender-1" value="1">
                    Male
                </label> 
                <label class="radio-inline" for="Gender-2">
                    <input type="radio" name="Gender" id="Gender-2" value="2">
                    Female
                </label>
            </div>
        </div>

        <!-- Select Basic -->
        <div class="form-group">
            <label class="col-md-6 control-label" for="DOB">DOB</label>
            <div class="col-md-6">
                <input id="DOB" type="date" name="DOB" value="${dob}">
            </div>
        </div>

        <!-- Text input-->
        <div class="form-group">
            <label class="col-md-6 control-label" for="Nationality">Nationality</label>  
            <div class="col-md-6">
                <input id="Nationality" name="Nationality" type="text" placeholder="Nationality" class="form-control input-md" value="${nationality}">
            </div>
        </div>

        <!-- Textarea -->
        <div class="form-group">
            <label class="col-md-6 control-label" for="Address">Address</label>
            <div class="col-md-6">                     
                <textarea class="form-control" id="Address" name="Address">${address}</textarea>
            </div>
        </div>

        <button type="submit" class="btn btn-lg btn-primary" style="margin-top: 10px; margin-right: 20px; float: right;">Submit</button>
    </form>
</div>
<script>
    var autocomlete;
    function initAutocomplete() {
        // Create the autocomplete object, restricting the search to geographical
        // location types.
        autocomplete = new google.maps.places.Autocomplete(
                /** @type {!HTMLInputElement} */(document.getElementById('Address')));
        autocomplete.setComponentRestrictions({country: 'SG'});
        // When the user selects an address from the dropdown, populate the address
        // fields in the form.
        //        autocomplete.addListener('place_changed', fillInAddress);
    }
</script>
<script>
    $(document).ready(function () {
    <c:if test="${result != 'none'}">
        $('#myModal').modal('show');
    </c:if>

        jQuery.validator.addMethod("birth", function (value, element) {
            if (value.length === 0)
                return true;
            var minDate = Date.parse("01/01/1990");
            var today = new Date();
            var DOB = Date.parse(value);
            if ((DOB <= today && DOB >= minDate)) {
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

        $("#Gender-" + ${gender}).prop('checked', true);
    });
</script>
<script src="http://maps.googleapis.com/maps/api/js?key=AIzaSyDknTuQNUudZrTPv3B_WM9rJf4Rpm9FQSs&libraries=places&callback=initAutocomplete" async defer></script>
</body>
</html>

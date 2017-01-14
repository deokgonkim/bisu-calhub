$(document).ready(function() {
    /*
     * use CSS3 placeholder but make sure users with unsupported browsers at
     * least see the labels
     */
    var browserSupportsInputPlaceholder = function() {
        var i = document.createElement('input');
        return 'placeholder' in i;
    };
    // check if the browser supports HTML5 placeholder attributes for text
    // fields
    if (browserSupportsInputPlaceholder()) {
        // loop over each label with jQuery
        $.each($("label"), function(i, element) {
            // extract the label valuefrom the label
            var for_input = $(element).attr('for');
            var text = $(element).text();
            // insert the placeholder into the corresponding input
            $("#" + for_input).attr('placeholder', text);
            // hide the label it's not needed anymore
            $(element).hide();
        });
    }

    // make all OpenID provider buttons into jQuery buttons
    $(".openid-provider-button").button();
    // make all form submit buttons into jQuery buttons
    $("input:submit").button();
    // make all regular form buttons into jQuery buttons
    $("input:button").button();

    // focus the username field when the page is loaded
    $("#j_username").focus();
});
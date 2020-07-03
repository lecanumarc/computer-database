
$(function() {
	$("form[name='addComputerForm']").validate({
		rules: {
			computerName: "required",

		},
		messages: {
			computerName: "Please enter a computer name",

		},
		submitHandler: function(form) {
			form.submit();
		}
	});
});

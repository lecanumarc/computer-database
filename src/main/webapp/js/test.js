$(function() {
	$('#addComputerForm').validate({
		rules: {
			'computerName': {
				required: true,
			},
		},
		messages: {
			'computerName': "Please enter a computer name",
		}
	});
});
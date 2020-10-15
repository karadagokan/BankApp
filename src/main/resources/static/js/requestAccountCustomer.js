$('document').ready(function() {

	$('table #detailsButton').on('click', function(event) {
		event.preventDefault();

		var href = $(this).attr('href');

		$.get(href, function(customer, status) {
			$('#idDetails').val(customer.customerId);
			$('#firstNameDetails').val(customer.customerFirstName);
			$('#lastNameDetails').val(customer.customerLastName);
			$('#salaryDetails').val(customer.salary);
		});
		$('#detailsModal').modal();
	});

});
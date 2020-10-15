
$('document').ready(function(){
	
	$('table #editButton').on('click',function(event){
		event.preventDefault();
			
		var href = $(this).attr('href');
		
		$.get(href, function(customer, status){
			$('#idEdit').val(customer.customerId);
			$('#firstNameEdit').val(customer.customerFirstName);
			$('#lastNameEdit').val(customer.customerLastName);
			$('#salaryEdit').val(customer.salary);
		});
		$('#editModal').modal();
	});
	

	$('table #deleteButton').on('click', function(event){
		event.preventDefault();
		
		var href= $(this).attr('href');
		
		$('#confirmDeleteButton').attr('href', href);
		
		$('#deleteModal').modal();
	});
		
});
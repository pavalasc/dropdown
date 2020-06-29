function employeeSelect() {
	//display the spinner
	$('#ajaxLoader').show();
	
	//first, let's get rid of the default "SELECT" option if it exists
	var defaultOption = $("#employeeIdSelect option[value='default']");
	if (defaultOption) defaultOption.remove();
	
	//get the selected id
	var employeeId = $('#employeeIdSelect').val();
	
	
	//alert ("empId: " +  employeeId);
	//get the url for the ajax call
	var url = "./skills/" + employeeId;
	//alert ("url: " +  url);
	//do the ajax call
	$.get(url, populateEmployeeInfo);	
	//alert ("after ajax get call! ");
}



//This is the function we run when we get back a response
//from the ajax call
function populateEmployeeInfo(data) {  //need for a class rest Response with two fields: status(OK or NOT_FOUND) and response (payload)
	var status = data.responseStatus; ////RestResponse has a field status
	
	//check the response to make sure it's ok
	if (status == "Ok") {
		var response = data.response; //RestResponse has a field response
		//alert ("response: " +  response);
		

		var state = '';
		state += '<option disabled selected="selected">--Select Skill...--</option>';
	    for (var key in response) {

//	    	alert(response[key].label);// other also in the same way.
	    	
	    	state += '<option value="' + response[key].id+ '">' + response[key].label + '</option>';
	    }
	    
	    $('#employeeSkillSelect').html(state);
		
		//var result = JSON.parse(response);

		//alert ("result: " +  result);
		//get the JSON data
		
//		var label0 = object[0].label;
//		alert ("label0: " +  label0);
//		var employeeId = response.employeeId;
//		var firstName = response.firstName;
//		var lastName = response.lastName;
//		var hoursPerWeek = response.hoursPerWeek;
//		var imageFile = './images/' + response.imageFile;
		
		//set the profile badge values
//		$('#employeePic').attr("src", imageFile);
//		$('#employeeName').html(firstName + ' ' + lastName);
//		$('#employeeDepartment').html(department);
		
		//set the input field values
//		$('#employeeFirstName').val(firstName);
//		$('#employeeLastName').val(lastName);
//		$('#employeeDept').val(department);
//		$('#employeeId').val(employeeId);
//		$('#employeeHours').val(hoursPerWeek);
		
		//show the hidden elements
//		$('#profileRow').css('visibility','visible');
	}
	
	//hide the spinner again
	$('#ajaxLoader').hide();
}

$.preloadImages = function() {
	for (var i = 0; i < arguments.length; i++) {
	    $("<img />").attr("src", arguments[i]);
	}
}
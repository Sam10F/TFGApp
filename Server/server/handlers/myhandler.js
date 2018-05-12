var data_data 			= require("../data/data.js"),
	mailValidator		= require("email-validator"),
	passwordValidator 	= require("password-validator");

exports.getcp1 = function(){

	data_data.getcp1.apply(this, arguments);
}

exports.add_data = function(newData, callback){
	data_data.add_data(newData, callback);
}

exports.logIn = function(dataLogIn, callback){
	data_data.logIn(dataLogIn, callback);
}

exports.edit = function(dataEdit, callback){
	var schema = createPassSchema();
	if(!schema.validate(dataEdit[2])){
		callback("The password does not fit the requirements");
	}else if(!mailValidator.validate(dataEdit[0])){
		callback("The email format is not valid");
	}else{
		data_data.edit(dataEdit, callback);
	}
}

exports.signUp = function(dataSignUp, callback){

	var schema = createPassSchema();
	if(!schema.validate(dataSignUp[2])){
		callback("The password does not fit the requirements");
	}else if(!mailValidator.validate(dataSignUp[0])){
		callback("The email format is not valid");
	}else{
		data_data.signUp(dataSignUp, callback);
	}
	
}

function createPassSchema(){
	// Create a schema 
	var schema = new passwordValidator();
	 
	// Add properties to it 
	schema
	.is().min(4)                                    // Minimum length 4 
	.is().max(200)                                  // Maximum length 100 
	.has().lowercase()                              // Must have lowercase letters 
	.has().digits()                                 // Must have digits 
	.has().not().spaces()                           // Should not have spaces 
	.is().not().oneOf(['Passw0rd', 'Password123']); // Blacklist these values

	return schema; 
}


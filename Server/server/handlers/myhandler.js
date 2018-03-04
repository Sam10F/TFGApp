var data_data = require("../data/data.js");

exports.getcp1 = function(){

	data_data.getcp1.apply(this, arguments);
}

exports.getPrueba = function(){
	data_data.getString.apply(this, arguments);
}

exports.add_data = function(newData, callback){
	data_data.add_data(newData, callback);
}
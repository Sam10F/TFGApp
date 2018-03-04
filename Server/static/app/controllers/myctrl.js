(function(){
	function MyCtrl($scope, dataProvider){

		function get_datacp1(){
			$scope.fn = dataProvider.getcp1(function (err, data) {

                $scope.fn = data;
                $scope.fnSexo = get_sex_values(data);
                $scope.fnRangos = get_range_values(data);          
            });

            
		};

		function get_sex_values(data){
			var sexValues = [];
			var i = 0;

			while(i != data.length){
				if(!sexValues.includes(data[i].SEXO) && data[i].SEXO != null){
					sexValues.push(data[i].SEXO);
				}
				i++;
			}

			return sexValues;
		};

		function get_range_values(data){
			var rangeValues = []
			var i = 0;

			while(i != data.length){
				if(!rangeValues.includes(data[i].RANGOS) && data[i].RANGOS != null){
					rangeValues.push(data[i].RANGOS);
				}
				i++;
			}

			return rangeValues;
		};


		$scope.addData = function(new_data){
			dataProvider.addData(new_data, function(err, data){
				console.log(err);
                if (err) {
                    $scope.add_recipe_error = "(" + err.error + ") " + err.message;
                } else {
                    $scope.add_recipe_error = null;
                    get_datacp1();
                } 
			});
		};


		get_datacp1();
	}

	app.controller("myctrl", ['$scope', 'dataProvider', MyCtrl])
})();

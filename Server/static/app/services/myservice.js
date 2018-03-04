(function(){
	function dataProvider($http){

		this._server_host = "";

		this.getcp1 = function(callback){
			$http.get(this._server_host + "/v1/data.json")
				.success(function (data, status, headers, conf) {
                    callback(null, data);

                })
                .error(function (data, status, headers, conf) {
                    callback(data);
                });
		}

		this.addData = function(new_data, callback){
			$http.put(this._server_host + "/v1/data.json", new_data)
				.success(function (data, status, headers, conf) {
                    callback(null, data);
                })
                .error(function (data, status, headers, conf) {
                    callback(data);
                });
		}

	}

	app.service("dataProvider", dataProvider);
})();
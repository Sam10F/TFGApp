var app = angular.module("myApp", ["ngRoute"]);

app.config(function($routeProvider){
	$routeProvider
		.when("/recipes",  {controller: "myctrl", templateUrl: "/app/partials/partial.html" })
        .when("/",  { redirectTo: "/recipes" })
        .otherwise({ redirectTo: "/404_page" });
});
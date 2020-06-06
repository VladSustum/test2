var projekatApp = angular.module("projekatApp", ["ngRoute"]);



projekatApp.controller("projekatCtrl", function($scope, $http, $location){
	
	$scope.sprintovi = [];
	$scope.stanja = [];
	$scope.zadaci = [];
	
	var zadaciUrl = "/api/zadaci";
	var stanjaUrl = "/api/stanja";
	var sprintoviUrl = "api/sprintovi";
	
	$scope.newZadatak = {};
	$scope.newZadatak.ime ="";
	$scope.newZadatak.zaduzeni ="";
	$scope.newZadatak.bodovi ="";
	$scope.newZadatak.stanjeId ="";
	$scope.newZadatak.sprintId ="";
	
	$scope.search ={};
	$scope.search.ime ="";
	$scope.search.sprint ="";
	
	$scope.totalPages = 1;
	$scope.pageNum = 0;
	
	var getZadaci = function(){
		var config = { params : {} };
		if($scope.search.ime != ""){
			config.params.ime = $scope.search.ime;
		}
		if($scope.search.sprint != ""){
			config.params.sprint = $scope.search.sprint;
		}
		config.params.pageNum = $scope.pageNum;
		
		$http.get(zadaciUrl, config).then(
		function success(res){
			$scope.zadaci = res.data
			$scope.totalPages = res.headers("totalPages");
		},
		function error(){
			alert("greska pri dobavjanju zadataka")
		}
		);	
	}
	getZadaci();
	
	var getStanja = function(){
		$http.get(stanjaUrl).then(
		function success(res){
			$scope.stanja = res.data
		},
		function error(){
			alert("nesuspesno dobavjanje stanja")
		}
		);
	}
	
	getStanja();
	
	var getSprintovi = function(){
		$http.get(sprintoviUrl).then(
		function success(res){
			$scope.sprintovi = res.data
		},
		function error(){
			alert("neuspesno dobavjanje sprinotova")
		}
		);
	}
	getSprintovi();
	
	
	$scope.doAdd = function(){
		$http.post(zadaciUrl, $scope.newZadatak).then(
		function succses(){
			getZadaci();
			$scope.newZadatak = {};
			$scope.newZadatak.ime ="";
			$scope.newZadatak.zaduzeni ="";
			$scope.newZadatak.bodovi ="";
			$scope.newZadatak.stanjeId ="";
			$scope.newZadatak.sprintId ="";
			
		},
		function error(){
			alert("Neuspesno dodavanje")
		}
		);
	}
	$scope.delet = function(id){
		$http.delete(zadaciUrl + "/" + id).then(
		function success(){
			getZadaci();
		},
		function error(){
			alert("Neuspesno brisanje")
		}
		);
	}
	
	$scope.search = function(){
		$scope.pageNum = 0;
		getZadaci();
		
	}
	
	$scope.changePage= function(direction){
		$scope.changePage = $scope.pageNum + direction;
		getZadaci();
	}
	
	$scope.predji = function(id){
	var promise =$http.post(zadaciUrl + "/" + id);
	promise.then(
		function success(){
			alert("uspesno izmenjeneno");
			getZadaci();
		},
		function error(){
			alert("neuspesno imena stanja");
			getZadaci();
		}
		);
	}
	$scope.goEdit = function(id){
		$location.path('/projekti/edit/' + id);
	}
	
	
	
	
});

projekatApp.controller("editCtrl", function($scope, $http, $location, $routeParams){
	var zadaciUrl = "/api/zadaci/" + $routeParams.id;
	var stanjaUrl = "/api/stanja";
	var sprintoviUrl = "api/sprintovi";
	
	$scope.stanja = [];
	$scope.sprintovi = [];
	
	$scope.newZadatak = {};
	$scope.newZadatak.ime ="";
	$scope.newZadatak.zaduzeni ="";
	$scope.newZadatak.bodovi ="";
	$scope.newZadatak.stanjeId ="";
	$scope.newZadatak.sprintId ="";
	
	var getZadaci= function(){
		$http.get(zadaciUrl).then(
		function succes(res){
		$scope.newZadatak = res.data	
		},
		function error(){
			alert("Neuspesno dobavjanje zadataka")
		}
		);
	}
	
getZadaci();
	
	var getStanja = function(){
		$http.get(stanjaUrl).then(
		function success(res){
			$scope.stanja = res.data
		},
		function error(){
			alert("nesuspesno dobavjanje stanja")
		}
		);
	}
	
	getStanja();
	
	var getSprintovi = function(){
		$http.get(sprintoviUrl).then(
		function success(res){
			$scope.sprintovi = res.data
		},
		function error(){
			alert("neuspesno dobavjanje sprinotova")
		}
		);
	}
	getSprintovi();
	
	$scope.doEdit = function(){
		$http.put(zadaciUrl, $scope.newZadatak).then(
		function success(){
			$location.path("/projekti")
		},
		function error(){
			alert("Neuspesna izmena");
		}
		);
	}
	
})











projekatApp.config(["$routeProvider", function($routeProvider){
	$routeProvider
	.when('/projekti',{
		templateUrl : '/app/html/projekat.html'
	})
	.when('/projekti/edit/:id', {
		templateUrl : '/app/html/projekatEdit.html'
	})
	.otherwise({
		redirectTo : '/'
	})
}]);
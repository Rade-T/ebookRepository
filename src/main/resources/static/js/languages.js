var app = angular.module('lang-app', []);

app.controller('LanguageCRUDCtrl', ['$scope', '$http', '$window', function ($scope, $http, $window) {
	$http.get("api/languages")
	.then(function (response) {$scope.languages = response.data;});
	
	$scope.language = {};
	
	$scope.selectLanguage = function (id) {
		$scope.language.id = id;
		$scope.getLanguage($scope.selected);
	}
	
	$scope.saveLanguage = function () {
		if ($scope.language.id == null) {
			$scope.addLanguage();
		} else {
			$scope.updateLanguage();
		}
	}
	
    $scope.updateLanguage = function (id, name) {
// CategoryCRUDService.updateCategory($scope.category.id, $scope.category.name)
    	$http({
    		method: "PUT",
    		url: "api/languages/",
    		data: {id : id, name : name}
    	}).then(function success(response){
              $scope.message = 'Category data updated!';
              $scope.errorMessage = '';
          },
          function error(response){
              $scope.errorMessage = 'Error updating category!';
              $scope.message = '';
          });
        $window.location.reload();
    }
    
    $scope.getLanguage = function (id) {
// CategoryCRUDService.getCategory($scope.category.id)
    	$http({
    		method: "GET",
    		url: "/api/languages/" + id
    	}).then(function success(response){
              $scope.category = response.data;
              $scope.category.id = id;
              $scope.message='';
              $scope.errorMessage = '';
          },
          function error (response ){
              $scope.message = '';
              if (response.status === 404){
                  $scope.errorMessage = 'Language not found!';
              }
              else {
                  $scope.errorMessage = "Error getting language!";
              }
          });
    }
    
    $scope.addLanguage = function (newLanguage) {
        if ($scope.language != null && $scope.language.name) {
// CategoryCRUDService.addCategory($scope.category.name)
        	$http({
        		method: "POST",
        		url: "/api/languages",
        		data: newLanguage
        	}).then (function success(response){
                  $scope.message = 'Language added!';
                  $scope.errorMessage = '';
              },
              function error(response){
                  $scope.errorMessage = 'Error adding language!';
                  $scope.message = '';
            });
        }
        else {
            $scope.errorMessage = 'Please enter a name!';
            $scope.message = '';
        }
        $window.location.reload();
    }
    
    $scope.deleteLanguage = function (id, $index) {
// CategoryCRUDService.deleteCategory(id)
    	$http({
    		method: "DELETE",
    		url: "/api/languages/" + id
    	}).then (function success(response){
              $scope.message = 'Language deleted!';
              $scope.category = null;
              $scope.errorMessage='';
              document.getElementById("languageTable").deleteRow($index);
          },
          function error(response){
              $scope.errorMessage = 'Error deleting language!';
              $scope.message='';
          })
          $window.location.reload();
    }
    
    $scope.getAllCategories = function () {
// CategoryCRUDService.getAllCategories()
    	$http({
        	  method: "GET",
        	  url: "/api/languages"
          }).then(function success(response){
              $scope.languages = response.data._embedded.languages;
              $scope.message='';
              $scope.errorMessage = '';
          },
          function error (response ){
              $scope.message='';
              $scope.errorMessage = 'Error getting categories!';
          });
    }

}]);
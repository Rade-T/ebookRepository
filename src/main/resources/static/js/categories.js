var app = angular.module('cat-app', []);

app.controller('CategoryCRUDCtrl', ['$scope', '$http', 'CategoryCRUDService', function ($scope, $http, CategoryCRUDService) {
	$http.get("api/categories")
	.then(function (response) {$scope.categories = response.data;});
	
	$scope.category = {};
	
	$scope.selectCategory = function (id) {
		$scope.category.id = id;
		$scope.getCategory();
		$scope.selected = $scope.category;
	}
	
    $scope.updateCategory = function () {
        CategoryCRUDService.updateCategory($scope.category.id, $scope.category.name)
          .then(function success(response){
              $scope.message = 'Category data updated!';
              $scope.errorMessage = '';
          },
          function error(response){
              $scope.errorMessage = 'Error updating category!';
              $scope.message = '';
          });
    }
    
    $scope.getCategory = function () {
        var id = $scope.category.id;
        CategoryCRUDService.getCategory($scope.category.id)
          .then(function success(response){
              $scope.category = response.data;
              $scope.category.id = id;
              $scope.message='';
              $scope.errorMessage = '';
          },
          function error (response ){
              $scope.message = '';
              if (response.status === 404){
                  $scope.errorMessage = 'Category not found!';
              }
              else {
                  $scope.errorMessage = "Error getting category!";
              }
          });
    }
    
    $scope.addCategory = function () {
        if ($scope.category != null && $scope.category.name) {
            CategoryCRUDService.addCategory($scope.category.name)
              .then (function success(response){
                  $scope.message = 'Category added!';
                  $scope.errorMessage = '';
              },
              function error(response){
                  $scope.errorMessage = 'Error adding category!';
                  $scope.message = '';
            });
        }
        else {
            $scope.errorMessage = 'Please enter a name!';
            $scope.message = '';
        }
    }
    
    $scope.deleteCategory = function () {
        CategoryCRUDService.deleteCategory($scope.category.id)
          .then (function success(response){
              $scope.message = 'Category deleted!';
              $scope.category = null;
              $scope.errorMessage='';
          },
          function error(response){
              $scope.errorMessage = 'Error deleting category!';
              $scope.message='';
          })
    }
    
    $scope.getAllCategories = function () {
        CategoryCRUDService.getAllCategories()
          .then(function success(response){
              $scope.categories = response.data._embedded.categories;
              $scope.message='';
              $scope.errorMessage = '';
          },
          function error (response ){
              $scope.message='';
              $scope.errorMessage = 'Error getting categories!';
          });
    }

}]);

app.service('CategoryCRUDService',['$http', function ($http) {
	
    this.getCategory = function getCategory(categoryId){
        return $http({
          method: 'GET',
          url: 'api/categories/'+categoryId
        });
	}
	
    this.addCategory = function addCategory(name){
        return $http({
          method: 'POST',
          url: 'api/categories/',
          data: {name:name}
        });
    }
	
    this.deleteCategory = function deleteCategory(id){
        return $http({
          method: 'DELETE',
          url: 'api/categories/'+id
        })
    }
	
    this.updateCategory = function updateCategory(id,name){
        return $http({
          method: 'PUT',
          url: 'api/categories/'+id,
          data: {name:name}
        })
    }
	
    this.getAllCategories = function getAllCategories(){
        return $http({
          method: 'GET',
          url: 'api/categories/'
        });
    }

}]);

//$(document).ready(function (){
//	$.ajax({
//        type: "GET",
//        url: "api/categories/",
//        dataType: "json",
//        success: function (data) {
//        	for (i = 0;i < data.length;i++) {
//        		$("#categoryTableBody").append("<tr><td>" + data[i].name + "</td></tr>");
//        		console.log(data[i]);
//        	}
//        },
//        error: function (err) {
//        	console.log("Greska prilikom ucitavanja");
//        	console.log(err.responseText);
//        }
//    });
//});
//
//$("#addCategoryBtn").on("click", function(event) {
//	event.preventDefault();
//	var data = {
//		"name" : $("#nameInput").val()
//	};
//	$.ajax({
//		type: "POST",
//		url: "api/categories/",
//		data: JSON.stringify(data),
//		contentType: "application/json",
//		success: function(response) {
//			location.reload();
//		},
//		error: function (err) {
//			console.log(err);
//            alert("Greska");
//        }
//	});
//});
/**
 * Created by Tom on 6/25/2016.
 */
/** angular.module("exampleApp", ["increment", "ngResource"]) */
angular.module("exampleApp", ["ngResource"])
    .constant("baseUrl", "http://localhost\\:8080/loyalties/")
    .config(function($httpProvider) {
        $httpProvider.defaults.withCredentials = true;
    })
    .controller("defaultCtrl", function ($scope, $http, $resource, baseUrl) {

        $scope.displayMode = "list";
        $scope.currentLoyalty = null;

        $scope.loyaltiesResource = $resource(baseUrl + ":id", { id: "@id" });

        $scope.listLoyalties = function () {
            $scope.foo = $scope.loyaltiesResource.get();
            //$scope.loyalties = $scope.loyaltiesResource.get();
            //$scope.loyalties.$promise.then(function (data) {
            $scope.foo.$promise.then(function (data) {
                alert(JSON.stringify($scope.foo._embedded.loyalties));
                $scope.loyalties = [];
                /*
                for (var loyalty in $scope.foo._embedded.loyalties) {
                    //alert();
                    $scope.loyalties.push(loyalty);
                }
                */
                for(var i = 0; i < $scope.foo._embedded.loyalties.length; i++) {
                    var obj = $scope.foo._embedded.loyalties[i];

                    $scope.loyalties.push(obj);
                }
            });
        }

        $scope.deleteLoyalty = function (loyalty) {
            loyalty.$delete().then(function () {
                $scope.loyalties.splice($scope.loyalties.indexOf(loyalty), 1);
            });
            $scope.displayMode = "list";
        }

        $scope.createLoyalty = function (loyalty) {
            new $scope.loyaltiesResource(loyalty).$save().then(function(newLoyalty) {
                $scope.loyalties.push(newLoyalty);
                $scope.displayMode = "list";
            });
        }

        $scope.updateLoyalty = function (loyalty) {
            loyalty.$save();
            $scope.displayMode = "list";
        }

        $scope.editOrCreateLoyalty = function (loyalty) {
            $scope.currentLoyalty = loyalty ? loyalty : {};
            $scope.displayMode = "edit";
        }

        $scope.saveEdit = function (loyalty) {
            if (angular.isDefined(loyalty.id)) {
                $scope.updateLoyalty(loyalty);
            } else {
                $scope.createLoyalty(loyalty);
            }
        }

        $scope.cancelEdit = function () {
            if ($scope.currentLoyalty && $scope.currentLoyalty.$get) {
                $scope.currentLoyalty.$get();
            }
            $scope.currentLoyalty = {};
            $scope.displayMode = "list";
        }

        $scope.listLoyalties();
    });
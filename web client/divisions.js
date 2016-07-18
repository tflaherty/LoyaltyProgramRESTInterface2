/**
 * Created by Tom on 7/18/2016.
 */
/** angular.module("exampleApp", ["increment", "ngResource"]) */
angular.module("divisions", ["ngResource"])
//.constant("baseUrl", "http://localhost:8080/divisions/")
    .constant("baseUrl", "http://www.loyaltyprogramrestinterface2.8evdhp67pp.us-west-2.elasticbeanstalk.com/divisions/")
    .config(function($httpProvider) {
        $httpProvider.defaults.withCredentials = true;
        $httpProvider.defaults.useXDomain = true;
        delete $httpProvider.defaults.headers.common['X-Requested-With'];
    })
    .controller("divisionsCtrl", function ($scope, $http, $resource, baseUrl) {

        $scope.displayMode = "list";
        $scope.currentDivision = null;

        $scope.divisionsResource = $resource(baseUrl + ":id?projection=full", { id: "@id" },
            {'update': {method: 'PUT'}}
        );

        $scope.listDivisions = function () {
            $scope.foo = $scope.divisionsResource.get();
            //$scope.divisions = $scope.divisionsResource.get();
            //$scope.divisions.$promise.then(function (data) {
            $scope.foo.$promise.then(function (data) {
                //alert(JSON.stringify($scope.foo._embedded.divisions));
                $scope.divisions = [];
                for(var i = 0; i < $scope.foo._embedded.divisions.length; i++) {
                    //var obj = $scope.foo._embedded.divisions[i];
                    var obj = new $scope.divisionsResource($scope.foo._embedded.divisions[i]);
                    $scope.divisions.push(obj);
                }
            });
        }

        $scope.deleteDivision = function (division) {
            division.$delete().then(function () {
                $scope.divisions.splice($scope.divisions.indexOf(division), 1);
            });
            $scope.displayMode = "list";
        }

        $scope.createDivision = function (division) {
            new $scope.divisionsResource(division).$save().then(function(newDivision) {
                $scope.divisions.push(newDivision);
                $scope.displayMode = "list";
            });
        }

        $scope.updateDivision = function (division) {
            division.$update();
            $scope.displayMode = "list";
        }

        $scope.editOrCreateDivision = function (division) {
            $scope.currentDivision = division ? division : {};
            $scope.displayMode = "edit";
        }

        $scope.saveEditDivision = function (division) {
            if (angular.isDefined(division.id)) {
                $scope.updateDivision(division);
            } else {
                $scope.createDivision(division);
            }
        }

        $scope.cancelEditDivision = function () {
            if ($scope.currentDivision && $scope.currentDivision.$get) {
                $scope.currentDivision.$get();
            }
            $scope.currentDivision = {};
            $scope.displayMode = "list";
        }

        $scope.listDivisions();
    });
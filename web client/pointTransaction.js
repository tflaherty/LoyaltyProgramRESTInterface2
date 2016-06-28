/**
 * Created by Tom on 6/27/2016.
 */
angular.module("exampleApp", ["ngResource"])
    //.constant("baseUrl", "http://localhost\\:8080/pointTransactions/")
    .constant("baseUrl", "http://www.loyaltyprogramrestinterface2.8evdhp67pp.us-west-2.elasticbeanstalk.com/pointTransactions/")
    .config(function($httpProvider) {
        $httpProvider.defaults.withCredentials = true;
    })
    .controller("defaultCtrl", function ($scope, $http, $resource, baseUrl) {

        //$scope.loyaltiesBaseUrl = "http://localhost:8080/loyalties/";
        $scope.loyaltiesBaseUrl = "http://www.loyaltyprogramrestinterface2.8evdhp67pp.us-west-2.elasticbeanstalk.com/loyalties/";
        $scope.displayMode = "list";
        $scope.currentPointTransaction = null;

        $scope.pointTransactionsResource = $resource(baseUrl + ":id?projection=full", { id: "@id" });

        $scope.listPointTransactions = function () {
            $scope.foo = $scope.pointTransactionsResource.get();
            //$scope.pointTransactions = $scope.pointTransactionsResource.get();
            //$scope.pointTransactions.$promise.then(function (data) {
            $scope.foo.$promise.then(function (data) {
                //alert(JSON.stringify($scope.foo._embedded.pointTransactions));
                $scope.pointTransactions = [];
                for(var i = 0; i < $scope.foo._embedded.pointTransactions.length; i++) {
                    var obj = $scope.foo._embedded.pointTransactions[i];
                    $scope.pointTransactions.push(obj);
                }
            });
        }

        $scope.deletePointTransaction = function (pointTransaction) {
            pointTransaction.$delete().then(function () {
                $scope.pointTransactions.splice($scope.pointTransactions.indexOf(pointTransaction), 1);
            });
            $scope.displayMode = "list";
        }

        $scope.createPointTransaction = function (pointTransaction) {
            new $scope.pointTransactionsResource(pointTransaction).$save().then(function(newPointTransaction) {
                $scope.pointTransactions.push(newPointTransaction);
                $scope.displayMode = "list";
            });
        }

        $scope.updatePointTransaction = function (pointTransaction) {
            pointTransaction.$save();
            $scope.displayMode = "list";
        }

        $scope.editOrCreatePointTransaction = function (pointTransaction) {
            $scope.currentPointTransaction = pointTransaction ? pointTransaction : {};
            $scope.displayMode = "edit";
        }

        $scope.saveEdit = function (pointTransaction) {
            if (angular.isDefined(pointTransaction.id)) {
                $scope.updatePointTransaction(pointTransaction);
            } else {
                $scope.createPointTransaction(pointTransaction);
            }
        }

        $scope.cancelEdit = function () {
            if ($scope.currentPointTransaction && $scope.currentPointTransaction.$get) {
                $scope.currentPointTransaction.$get();
            }
            $scope.currentPointTransaction = {};
            $scope.displayMode = "list";
        }

        $scope.listPointTransactions();
    });

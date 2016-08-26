/**
 * Created by Tom on 8/26/2016.
 */
/** angular.module("exampleApp", ["increment", "ngResource"]) */
angular.module("loyaltyProgram.companies", ["ngResource"])
//.constant("baseUrl", "http://localhost:8080/persons/")
    .constant("baseUrl", "http://www.loyaltyprogramrestinterface2.8evdhp67pp.us-west-2.elasticbeanstalk.com/messages?projection=full/")
    .config(function($httpProvider) {
        $httpProvider.defaults.withCredentials = true;
        $httpProvider.defaults.useXDomain = true;
        delete $httpProvider.defaults.headers.common['X-Requested-With'];
    })
    .controller("companiesCtrl", function ($scope, $http, $resource, baseUrl) {

        $scope.displayMode = "list";
        $scope.currentMessage = null;

        $scope.messagesResource = $resource(baseUrl + ":id", { id: "@id" },
            {'update': {method: 'PUT'}}
        );

        $scope.listMessages = function () {
            $scope.foo = $scope.messagesResource.get();
            //$scope.messages = $scope.messagesResource.get();
            //$scope.messages.$promise.then(function (data) {
            $scope.foo.$promise.then(function (data) {
                //alert(JSON.stringify($scope.foo._embedded.messages));
                $scope.messages = [];
                for(var i = 0; i < $scope.foo._embedded.messages.length; i++) {
                    //var obj = $scope.foo._embedded.messages[i];
                    var obj = new $scope.messagesResource($scope.foo._embedded.messages[i]);
                    $scope.messages.push(obj);
                }
            });
        }

        $scope.deleteMessage = function (message) {
            message.$delete().then(function () {
                $scope.messages.splice($scope.messages.indexOf(message), 1);
            });
            $scope.displayMode = "list";
        }

        $scope.createMessage = function (message) {
            new $scope.messagesResource(message).$save().then(function(newMessage) {
                $scope.messages.push(newMessage);
                $scope.displayMode = "list";
            });
        }

        $scope.updateMessage = function (message) {
            message.$update();
            $scope.displayMode = "list";
        }

        $scope.editOrCreateMessage = function (message) {
            $scope.currentMessage = message ? message : {};
            $scope.displayMode = "edit";
        }

        $scope.saveEditMessage = function (message) {
            if (angular.isDefined(message.id)) {
                $scope.updateMessage(message);
            } else {
                $scope.createMessage(message);
            }
        }

        $scope.cancelEditMessage = function () {
            if ($scope.currentMessage && $scope.currentMessage.$get) {
                $scope.currentMessage.$get();
            }
            $scope.currentMessage = {};
            $scope.displayMode = "list";
        }

        $scope.listMessages();
    });
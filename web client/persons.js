/**
 * Created by Tom on 8/26/2016.
 */
/** angular.module("exampleApp", ["increment", "ngResource"]) */
angular.module("loyaltyProgram.companies", ["ngResource"])
//.constant("baseUrl", "http://localhost:8080/persons/")
    .constant("baseUrl", "http://www.loyaltyprogramrestinterface2.8evdhp67pp.us-west-2.elasticbeanstalk.com/persons/")
    .config(function($httpProvider) {
        $httpProvider.defaults.withCredentials = true;
        $httpProvider.defaults.useXDomain = true;
        delete $httpProvider.defaults.headers.common['X-Requested-With'];
    })
    .controller("companiesCtrl", function ($scope, $http, $resource, baseUrl) {

        $scope.displayMode = "list";
        $scope.currentPerson = null;

        $scope.personsResource = $resource(baseUrl + ":id", { id: "@id" },
            {'update': {method: 'PUT'}}
        );

        $scope.listPersons = function () {
            $scope.foo = $scope.personsResource.get();
            //$scope.persons = $scope.personsResource.get();
            //$scope.persons.$promise.then(function (data) {
            $scope.foo.$promise.then(function (data) {
                //alert(JSON.stringify($scope.foo._embedded.persons));
                $scope.persons = [];
                for(var i = 0; i < $scope.foo._embedded.persons.length; i++) {
                    //var obj = $scope.foo._embedded.persons[i];
                    var obj = new $scope.personsResource($scope.foo._embedded.persons[i]);
                    $scope.persons.push(obj);
                }
            });
        }

        $scope.deletePerson = function (person) {
            person.$delete().then(function () {
                $scope.persons.splice($scope.persons.indexOf(person), 1);
            });
            $scope.displayMode = "list";
        }

        $scope.createPerson = function (person) {
            new $scope.personsResource(person).$save().then(function(newPerson) {
                $scope.persons.push(newPerson);
                $scope.displayMode = "list";
            });
        }

        $scope.updatePerson = function (person) {
            person.$update();
            $scope.displayMode = "list";
        }

        $scope.editOrCreatePerson = function (person) {
            $scope.currentPerson = person ? person : {};
            $scope.displayMode = "edit";
        }

        $scope.saveEditPerson = function (person) {
            if (angular.isDefined(person.id)) {
                $scope.updatePerson(person);
            } else {
                $scope.createPerson(person);
            }
        }

        $scope.cancelEditPerson = function () {
            if ($scope.currentPerson && $scope.currentPerson.$get) {
                $scope.currentPerson.$get();
            }
            $scope.currentPerson = {};
            $scope.displayMode = "list";
        }

        $scope.listPersons();
    });
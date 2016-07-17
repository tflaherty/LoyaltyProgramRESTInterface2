/**
 * Created by Tom on 6/28/2016.
 */
/** angular.module("exampleApp", ["increment", "ngResource"]) */
angular.module("exampleApp", ["ngResource"])
//.constant("baseUrl", "http://localhost:8080/companies/")
    .constant("baseUrl", "http://www.loyaltyprogramrestinterface2.8evdhp67pp.us-west-2.elasticbeanstalk.com/companies/")
    .config(function($httpProvider) {
        $httpProvider.defaults.withCredentials = true;
        $httpProvider.defaults.useXDomain = true;
        delete $httpProvider.defaults.headers.common['X-Requested-With'];
    })
    .controller("defaultCtrl", function ($scope, $http, $resource, baseUrl) {

        $scope.displayMode = "list";
        $scope.currentCompany = null;

        $scope.companiesResource = $resource(baseUrl + ":id", { id: "@id" });

        $scope.listCompanies = function () {
            $scope.foo = $scope.companiesResource.get();
            //$scope.companies = $scope.companiesResource.get();
            //$scope.companies.$promise.then(function (data) {
            $scope.foo.$promise.then(function (data) {
                //alert(JSON.stringify($scope.foo._embedded.companies));
                $scope.companies = [];
                for(var i = 0; i < $scope.foo._embedded.companies.length; i++) {
                    var obj = $scope.foo._embedded.companies[i];
                    $scope.companies.push(obj);
                }
            });
        }

        $scope.deleteCompany = function (company) {
            company.$delete().then(function () {
                $scope.companies.splice($scope.companies.indexOf(company), 1);
            });
            $scope.displayMode = "list";
        }

        $scope.createCompany = function (company) {
            new $scope.companiesResource(company).$save().then(function(newCompany) {
                $scope.companies.push(newCompany);
                $scope.displayMode = "list";
            });
        }

        $scope.updateCompany = function (company) {
            company.$save();
            $scope.displayMode = "list";
        }

        $scope.editOrCreateCompany = function (company) {
            $scope.currentCompany = company ? company : {};
            $scope.displayMode = "edit";
        }

        $scope.saveEdit = function (company) {
            if (angular.isDefined(company.id)) {
                $scope.updateCompany(company);
            } else {
                $scope.createCompany(company);
            }
        }

        $scope.cancelEdit = function () {
            if ($scope.currentCompany && $scope.currentCompany.$get) {
                $scope.currentCompany.$get();
            }
            $scope.currentCompany = {};
            $scope.displayMode = "list";
        }

        $scope.listCompanies();
    });
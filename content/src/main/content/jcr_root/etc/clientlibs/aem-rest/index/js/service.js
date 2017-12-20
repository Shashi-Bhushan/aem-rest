(function(){
    'use strict';

    indexModule.service('indexService' ,['$http', '$q', '$window', 'urlProvider', function($http, $q, $window, urlProvider){

        var self = this;

        this.createRandomUsers = function(count) {
            var deferred = $q.defer();

            var paramsArray = [];
            paramsArray[0] = count;

            var url = urlProvider.GetServiceURL('randomizeURL' , paramsArray);

            $http.get(url).then(function(response) {
                deferred.resolve(response.data);
            });

            return deferred.promise;
        }

        this.getAllUsers = function() {
            var deferred = $q.defer();

            var url = urlProvider.GetServiceURL('getAllUsersURL', []);

            $http.get(url).then(function(response) {
                deferred.resolve(response.data);
            });

            return deferred.promise;
        }
    }]);

})();

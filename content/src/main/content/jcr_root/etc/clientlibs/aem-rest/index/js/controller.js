(function() {
    "use strict";

    indexModule.controller('indexController', ['indexService', function(indexService){
        var self = this;

        self.randomUrl = 'hey';

        self.userList = [];

        var getAllUsersPromise = indexService.getAllUsers();
        getAllUsersPromise.then(function(response){
            self.userList = response;
        });

        self.createRandomUsers = function() {
            var count = angular.element('#user-count').val();

            var randomizeUsersPromise = indexService.createRandomUsers(count);
            randomizeUsersPromise.then(function(response) {
                var getAllUsersPromise = indexService.getAllUsers();

                getAllUsersPromise.then(function(response){
                    self.userList = response;
                });
            });
        }
    }]);
})();

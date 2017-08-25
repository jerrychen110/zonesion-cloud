(function() {
    'use strict';

    angular
        .module('zonesionCloudApplicationApp')
        .controller('UsersController', UsersController);

    UsersController.$inject = ['$scope', '$rootScope', '$stateParams', 'UsersService', 'UsersFavoritedService'];

    function UsersController($scope, $rootScope, $stateParams, UsersService, UsersFavoritedService) {
        var vm = this;
        vm.countUser = $stateParams.countUser
        loadLearnCourse();
        loadFavoritedCourse();

        function loadLearnCourse () {
        	UsersService.query({
        		id: $stateParams.id
                //size: vm.itemsPerPage
                //sort: sort()
            }, onSuccess, onError);

            function onSuccess(data, headers) {
                //vm.links = ParseLinks.parse(headers('link'));
                vm.totalItems = headers('X-Total-Count');
                vm.queryCount = vm.totalItems;
                vm.userInfo = data[0];
                vm.userInfos = data;
                console.log(data);
            }
            function onError(error) {
                //AlertService.error(error.data.message);
            }
        }
        
        function loadFavoritedCourse () {
        	UsersFavoritedService.query({
        		id: $stateParams.id
                //size: vm.itemsPerPage
                //sort: sort()
            }, onSuccess, onError);

            function onSuccess(data, headers) {
            	
            	vm.userFavorite = data[0];
                vm.userFavorites = data;
                console.log(vm.userFavorites);
            }
            function onError(error) {
                //AlertService.error(error.data.message);
            }
        }
    }
})();

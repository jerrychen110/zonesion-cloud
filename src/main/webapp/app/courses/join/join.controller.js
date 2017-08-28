(function() {
    'use strict';

    angular
        .module('zonesionCloudApplicationApp')
        .controller('JoinController', JoinController);

    JoinController.$inject = ['$scope', '$rootScope', '$stateParams', 'CoursesService', 'UsersService'];

    function JoinController($scope, $rootScope, $stateParams, CoursesService, UsersService) {
        var vm = this;
        loadAll();
        loadUserInfo();
        
        function loadAll () {
        	CoursesService.query({
        		id: $stateParams.id
            }, onSuccess, onError);

            function onSuccess(data, headers) {

                vm.courses = data[0];
                vm.allcourses = data;
                console.log(data);
 
            }
            function onError(error) {
                //AlertService.error(error.data.message);
            }
        }
        
        function loadUserInfo () {
        	UsersService.query({
        		id: $stateParams.id
            }, onSuccess, onError);

            function onSuccess(data, headers) {
            	
                vm.allusers = data;
                vm.countUsers = eval(data).length                
            }
            function onError(error) {
                //AlertService.error(error.data.message);
            }
        }
    }
})();

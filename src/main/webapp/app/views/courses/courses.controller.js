(function() {
    'use strict';

    angular
        .module('zonesionCloudApplicationApp')
        .controller('CoursesController', CoursesController);

    CoursesController.$inject = ['$scope', '$rootScope', 'Principal', 'LoginService','$state','$stateParams', 'CoursesService', 'UsersService'];

    function CoursesController($scope, $rootScope, Principal, LoginService, $state, $stateParams, CoursesService, UsersService) {
        var vm = this;
        vm.account = null;
        vm.isAuthenticated = null;
        vm.login = LoginService.open;
        $scope.$on('authenticationSuccess', function() {
            getAccount();
        });

        getAccount();

        function getAccount() {
            Principal.identity().then(function(account) {
                vm.account = account;
                vm.isAuthenticated = Principal.isAuthenticated;
                if(vm.account != null && vm.isAuthenticated != null){
                	$state.go('join',{id:$stateParams.id});
                }else{
                	$state.go('courses')
                }
            });
        }
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
                console.log(data);
                vm.countUsers = eval(data).length                
            }
            function onError(error) {
                //AlertService.error(error.data.message);
            }
        }
    }
})();

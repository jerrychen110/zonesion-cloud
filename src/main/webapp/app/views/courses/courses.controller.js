(function() {
    'use strict';

    angular
        .module('zonesionCloudApplicationApp')
        .controller('CoursesController', CoursesController);

    CoursesController.$inject = ['$scope', '$rootScope', 'Principal', 'LoginService','$state','$stateParams', 'CoursesService', 'UsersService'];

    function CoursesController($scope, $rootScope, Principal, LoginService, $state, $stateParams, CoursesService, UsersService) {
        var vm = this;
        vm.account = null;
        vm.isAuthenticated = Principal.isAuthenticated();
        vm.login = LoginService.open;

        //登录成功  刷新信息
        $scope.$on('authenticationSuccess', function() {
            // getAccount();
        });
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

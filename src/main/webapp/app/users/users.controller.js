(function() {
    'use strict';

    angular
        .module('zonesionCloudApplicationApp')
        .controller('UsersController', UsersController);

    UsersController.$inject = ['$scope', '$rootScope', '$stateParams', 'UsersService','$state'];

    function UsersController($scope, $rootScope, $stateParams, UsersService,$state) {

        var vm = this;
        $scope.avatar = '';
        $scope.name = 'admin';
        $scope.school = '武汉理工大学';
        $scope.about = '武汉理工大学武汉理工大学武汉理工大学武汉理工大学武汉理工大学';

        vm.activeTab = 1;
        vm.blank = false;

        vm.currentPage = 1;
        vm.pageSize = 2;
        vm.getCourseLearning = getCourseLearning;
        vm.getCourseFavorite = getCourseFavorite;
        getCourseLearning();

        function getCourseLearning () {
            UsersService.getCourseLearning({
                apge: vm.currentPage,
                size: vm.pageSize
            }, onSuccess, onError);

            function onSuccess(data, headers) {
                console.log(data);
                vm.blank = true;
                vm.userLearning = data;
            }
            function onError(error) {
                //AlertService.error(error.data.message);
            }
        }

        function getCourseFavorite () {
            UsersService.getCourseFavorite({
                apge: vm.currentPage,
                size: vm.pageSize
            }, onSuccess, onError);

            function onSuccess(data, headers) {
                console.log(data);
                vm.blank = true;
                vm.userLearning = data;
            }
            function onError(error) {
                //AlertService.error(error.data.message);
            }
        }
    }
})();

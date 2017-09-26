(function() {
    'use strict';

    angular
        .module('zonesionCloudApplicationApp')
        .controller('UsersController', UsersController);

    UsersController.$inject = ['$scope', '$rootScope', '$stateParams', 'UsersService','$state'];

    function UsersController($scope, $rootScope, $stateParams, UsersService,$state) {

        var vm = this;

        vm.activeTab = 1;
        vm.blank = false;
        vm.currentPage = 1;
        vm.pageSize = 2;
        vm.getCourseLearning = getCourseLearning;
        vm.getCourseFavorite = getCourseFavorite;
        vm.toCourse = toCourse;

        getCourseAccount();
        getCourseLearning();

        function getCourseAccount() {
            UsersService.getCourseAccount(onSuccess, onError);

            function onSuccess(data, headers) {
                //console.log(data);
                vm.userAccount = data;
            }
            function onError(error) {
                //AlertService.error(error.data.message);
            }
        }

        function getCourseLearning () {
            UsersService.getCourseLearning({
                apge: vm.currentPage,
                size: vm.pageSize,
                filter: null,
                query: vm.query
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
                //console.log(data);
                vm.blank = true;
                vm.userLearning = data;
            }
            function onError(error) {
                //AlertService.error(error.data.message);
            }
        }

        function toCourse(courseId){
            $state.go('courses',{id:courseId});
        }
    }
})();

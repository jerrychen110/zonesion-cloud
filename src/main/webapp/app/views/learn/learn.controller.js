(function() {
    'use strict';

    angular
        .module('zonesionCloudApplicationApp')
        .controller('LearnController', LearnController);

    LearnController.$inject = ['$scope', 'Principal', 'LoginService', '$state','Course','$rootScope','$stateParams','CourseService','$log'];

    function LearnController ($scope, Principal, LoginService, $state, Course,$rootScope,$stateParams,CourseService,$log) {
        var vm = this;

        vm.activeTab = 1;
        vm.sideList = true;

        vm.getCourseLessons=getCourseLessons();
        //vm.getCourseBase=getCourseBase();

        function getCourseLessons () {
            CourseService.getCourseLessons({
                id: $stateParams.id
            }, onSuccess, onError);

            function onSuccess(data, headers) {
                vm.courseInfo= data;
                console.log(data);
            }
            function onError(error) {
                //AlertService.error(error.data.message);
            }
        }

        function getCourseBase () {
            CourseService.getCourseBase({
                id: $stateParams.id
            }, onSuccess, onError);

            function onSuccess(data, headers) {
                vm.courseBase = data;
                console.log(data);
                $log.debug(data);
            }
            function onError(error) {
                $log.error();
                //AlertService.error(error.data.message);
            }
        }
    }
})();

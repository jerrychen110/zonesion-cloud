(function() {
    'use strict';

    angular
        .module('zonesionCloudApplicationApp')
        .controller('ExploreController', ExploreController);

    ExploreController.$inject = ['$scope', '$rootScope', '$stateParams', 'CourseService'];

    function ExploreController($scope, $rootScope, $stateParams, CourseService) {
        var vm = this;
        vm.pageSize = 5;
        vm.currentPage = 1;
        vm.totalCount = 0;
        vm.searchInfo = '';
        vm.filter = '';
        loadExplore();

        function loadExplore () {
        	CourseService.getCourseExplore({
        		pageNo: vm.currentPage,
        		pageSize: vm.pageSize,
        		filter: vm.filter
            }, onSuccess, onError);

            function onSuccess(data, headers) {
                vm.courses = data[0];
                vm.allcourses = data.result;
                for(var i = 1; i <= data.totalCount; i++){
                	vm.totalPages.push(i);
                }
            }
            function onError(error) {
                //AlertService.error(error.data.message);
            }
        }
    }
})();

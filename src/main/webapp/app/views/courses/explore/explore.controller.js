(function() {
    'use strict';

    angular
        .module('zonesionCloudApplicationApp')
        .controller('ExploreController', ExploreController);

    ExploreController.$inject = ['$scope', '$rootScope', '$stateParams', 'CourseService','$state'];

    function ExploreController($scope, $rootScope, $stateParams, CourseService,$state) {
        var vm = this;
        vm.pageSize = 10;
        vm.currentPage = 1;
        vm.totalCount = 0;
        vm.searchInfo = '';
        vm.filter = '';
        vm.courseType = '';
        vm.loadExplore=loadExplore;
        vm.stateGo=stateGo;
        loadExplore();

        function loadExplore (type) {
            vm.courseType = type;
        	CourseService.getCourseExplore({
        		page: vm.currentPage,
        		size: vm.pageSize,
        		filter: type,
            query: vm.query
            }, onSuccess, onError);

            function onSuccess(data, headers) {
                vm.courses = data[0];
                vm.allcourses = data.result;
                vm.totalCount=data.totalCount
            }
            function onError(error) {
                //AlertService.error(error.data.message);
            }
        }

        function stateGo(url,id){
          $state.go(url,{id:id});
        }
    }
})();

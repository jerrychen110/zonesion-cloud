(function() {
    'use strict';

    angular
        .module('zonesionCloudApplicationApp')
        .controller('ExploreController', ExploreController);

    ExploreController.$inject = ['$scope', '$rootScope', '$stateParams', 'ExploreService'];

    function ExploreController($scope, $rootScope, $stateParams, ExploreService) {
        var vm = this;
        vm.pageSize = 5;
        vm.currentPage = 1;
        vm.totalCount = 0;
        vm.searchInfo = '';

        console.log(vm.page);
        console.log(vm.size);
        console.log(vm.filter);

        loadExplore();

        function loadExplore () {
        	ExploreService.query({
        		pageNo: vm.currentPage,
        		pageSize: vm.pageSize,
        		filters: null
            }, onSuccess, onError);

            function onSuccess(data, headers) {
                //vm.links = ParseLinks.parse(headers('link'));
                //vm.totalPages = data.totalPages;
            	console.log(data.totalPages);
            	//vm.totalCount = data.totalCount;
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

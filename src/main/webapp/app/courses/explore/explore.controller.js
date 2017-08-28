(function() {
    'use strict';

    angular
        .module('zonesionCloudApplicationApp')
        .controller('ExploreController', ExploreController);

    ExploreController.$inject = ['$scope', '$rootScope', '$stateParams', 'ExploreService'];

    function ExploreController($scope, $rootScope, $stateParams, ExploreService) {
        var vm = this;
        if($stateParams.pageNo == null && $stateParams.size == null && $stateParams.filter == null){
        	$stateParams.pageNo = 1;
        	$stateParams.size = 10;
        	$stateParams.filter ="newest";
        }
        
        if($stateParams.pageNo != null && $stateParams.size == null && $stateParams.filter == null){
        	$stateParams.pageNo = $stateParams.pageNo;
        	$stateParams.size = 10;
        	$stateParams.filter ="newest";
        }
        if($stateParams.pageNo != null && $stateParams.size == null && $stateParams.filter != null){
        	$stateParams.pageNo = $stateParams.pageNo;
        	$stateParams.size = 10;

        	$scope.getFilters = function(val){
            	console.log(val)
            	vm.filter = val;
            }
        }
        
        vm.page = $stateParams.pageNo;
        vm.size = 10;
        vm.filter = $stateParams.filter;
        
        console.log(vm.page);
        console.log(vm.size);
        console.log(vm.filter);

        
        
        
        loadExplore();
        
        function loadExplore () {
        	ExploreService.query({
        		pageNo: vm.page,
        		pageSize: vm.size,
        		filters: vm.filter

            }, onSuccess, onError);

            function onSuccess(data, headers) {
                //vm.links = ParseLinks.parse(headers('link'));
                //vm.totalPages = data.totalPages;
            	console.log(data.totalPages);
            	//vm.totalCount = data.totalCount;
                vm.courses = data[0];
                vm.allcourses = data.result;
                vm.totalPages = [];
                vm.pageSize = 10;
                //vm.allFilters = {"newest":"最近更新", "hot":"最热课程", "recommended":"推荐课程"};
                
                vm.allFilters = [{"name": "最近更新","value": "newest"},{"name": "最热课程","value": "hot"},{"name": "推荐课程","value": "recommended"}]
                console.log(data);
 
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

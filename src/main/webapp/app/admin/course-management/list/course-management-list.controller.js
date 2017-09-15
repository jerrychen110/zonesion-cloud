(function() {
    'use strict';

    angular
        .module('zonesionCloudApplicationApp')
        .controller('CourseManagementListController', CourseManagementListController);

    CourseManagementListController.$inject = ['$scope','$rootScope','Principal', 'Course', 'ParseLinks', 'AlertService',
     '$state', '$stateParams', 'JhiLanguageService','COURSETYPETABS'];

    function CourseManagementListController($scope,$rootScope,Principal, Course, ParseLinks, AlertService, $state,
       $stateParams, JhiLanguageService,COURSETYPETABS) {
        var vm = this;

        vm.courseTabs=angular.copy(COURSETYPETABS);
        vm.currentAccount = null;
        vm.languages = null;
        vm.loadAll = loadAll;
        vm.page = 1;
        vm.totalItems = null;
        vm.links = null;
        vm.loadPage = loadPage;
        // vm.predicate = pagingParams.predicate;
        // vm.reverse = pagingParams.ascending;
        // vm.itemsPerPage = paginationConstants.itemsPerPage;
        vm.pageSize = 5;
        vm.currentPage =1;

        vm.courseType = $stateParams.courseType;
        vm.courseSource = $stateParams.courseSource;


        vm.transition = transition;
        vm.headTitle = null;
        vm.selectItem = selectItem;

        vm.loadAll();


        // 认证成功以后刷新页面信息
        $scope.$on('authenticationSuccess', function() {
          vm.currentAccount =  $rootScope.accountInfo;
          vm.isAuthenticated = Principal.isAuthenticated();
          vm.loadAll();
        });

        function loadAll () {
            Course.query({
                courseType: vm.courseType,
                courseSource: vm.courseSource

            }, onSuccess, onError);
            function onSuccess(data, headers) {
                vm.links = ParseLinks.parse(headers('link'));
                vm.totalItems = headers('X-Total-Count');
                vm.totalCount = vm.totalItems;
                vm.courses = data;
                vm.statesObj= {
               		 0:"未发布",
               		 1:"已发布",
               		 2:"已关闭"
               		};
                if(custParams.courseSource=='0'){
                	vm.headTitle = "自有课程";
                }else if(custParams.courseSource=='1'){
                	vm.headTitle = "第三方课程";
                }
            }
            function onError(error) {
                AlertService.error(error.data.message);
            }

        }


        function loadPage (page) {
            vm.page = page;
            vm.transition();
        }

        function transition () {
            $state.transitionTo($state.$current, {
                page: vm.page,
                sort: vm.predicate + ',' + (vm.reverse ? 'asc' : 'desc'),
                search: vm.currentSearch
            });
        }

        //
        function selectItem(selectIndex){
          angular.forEach(vm.courseTabs,function(tabInfo,index){
            if(index==selectIndex){
              tabInfo.active=true;
              vm.courseType = tabInfo.courseType;
            }else{
              tabInfo.active = false;
            }
          })
          vm.loadAll();
        }
    }
})();

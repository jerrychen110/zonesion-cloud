(function() {
    'use strict';

    angular
        .module('zonesionCloudApplicationApp')
        .controller('CourseManagementController', CourseManagementController);

    CourseManagementController.$inject = ['Principal', 'AlertService', '$state',
    'STUDENTSIDENAV','TEACHERSIDENAV','ADMINSIDENAV','JhiLanguageService','$rootScope','$scope'];

    function CourseManagementController(Principal, AlertService, $state, STUDENTSIDENAV,
      TEACHERSIDENAV,ADMINSIDENAV,JhiLanguageService,$rootScope,$scope) {
        var vm = this;
        vm.currentAccount = $rootScope.accountInfo;
        vm.languages = null;
        vm.getSideNavInfo = getSideNavInfo;
        vm.selectItem = selectItem;
        vm.getSideNavInfo();
        JhiLanguageService.getAll().then(function (languages) {
            vm.languages = languages;
        });

        $scope.$on('authenticationSuccess', function() {
          vm.currentAccount =  $rootScope.accountInfo;
          vm.isAuthenticated = Principal.isAuthenticated();
          vm.getSideNavInfo();
        });
        Principal.identity().then(function(account) {
            vm.currentAccount = account;
        });

        function getSideNavInfo(){
          if(vm.currentAccount==null){
            return;
          }
          angular.forEach(vm.currentAccount.authorities,function(authoritie){
            switch (authoritie) {
              case 'ROLE_ADMIN':
                vm.sidenavInfo = angular.copy(ADMINSIDENAV);
                break;
              case 'ROLE_TEACHER':
                vm.sidenavInfo = angular.copy(TEACHERSIDENAV);
                break;
              case 'ROLE_STUDENT':
                vm.sidenavInfo = angular.copy(STUDENTSIDENAV);
                break;
              default:
                break;
            }
          })
        }

        function selectItem(selectIndex){
          angular.forEach(vm.sidenavInfo,function(sidenavInfo,index){
            if(index==selectIndex){
              sidenavInfo.active=true;
            }else{
              sidenavInfo.active=false;
            }
          })
        }
    }
})();

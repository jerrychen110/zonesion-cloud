(function() {
    'use strict';

    angular
        .module('zonesionCloudApplicationApp')
        .controller('LearnTestController', LearnTestController);

    LearnTestController.$inject = ['$scope', 'Principal', 'LoginService', '$state','Course','$rootScope','LEFTTOOL','CourseService','$stateParams'];

    function LearnTestController ($scope, Principal, LoginService, $state, Course,$rootScope,LEFTTOOL,CourseService,$stateParams) {


        var vm = this;
        vm.showNavContainer = true;
        vm.toolbarInfos = angular.copy(LEFTTOOL);

        vm.account = $rootScope.accountInfo;
        vm.isAuthenticated = Principal.isAuthenticated;
        vm.login = LoginService.open;
        vm.closeContainer = closeContainer;
        vm.seleteToolBar = seleteToolBar;
        $scope.$on('authenticationSuccess', function() {
          console.log(  $rootScope.accountInfo);
          vm.account =  $rootScope.accountInfo;
        });

        vm.getCourseLessons=getCourseLessons;
        vm.getCourseLessons();

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
        //选择菜单
        function seleteToolBar(selectedIndex){
          var oldSelect = _.findIndex(vm.toolbarInfos,{active:true})
          if(oldSelect==selectedIndex){
            vm.showNavContainer = !vm.showNavContainer;
            return;
          }else{
            _.forEach(vm.toolbarInfos,function(tools,index){
              if(selectedIndex==index){
                tools.active=true;
              }else{
                tools.active=false;
              }
            })
            vm.showNavContainer = true;
          }
        }



        // 关闭侧面菜单详情
        function closeContainer(){
          vm.showNavContainer = false;
        }
    }
})();

(function() {
    'use strict';

    angular
        .module('zonesionCloudApplicationApp')
        .controller('CoursesController', CoursesController);

    CoursesController.$inject = ['$scope', '$rootScope', 'Principal', 'LoginService',
    '$state','$stateParams', 'CourseService', 'UsersService','$uibModal','$log'];

    function CoursesController($scope, $rootScope, Principal, LoginService, $state, $stateParams,
      CourseService, UsersService,$uibModal,$log) {
        var vm = this;
        vm.account = $rootScope.accountInfo;
        vm.isAuthenticated = Principal.isAuthenticated();
        vm.login = LoginService.open;
        vm.stateGo = stateGo;

        vm.getCourseLessons=getCourseLessons();
        vm.getCourseBase=getCourseBase();
        //登录成功  刷新信息
        $scope.$on('authenticationSuccess', function() {
          vm.account =  $rootScope.accountInfo;
          vm.isAuthenticated = Principal.isAuthenticated();
        });



        function getCourseLessons () {
        	CourseService.getCourseLessons({
        		id: $stateParams.id
            }, onSuccess, onError);

            function onSuccess(data, headers) {
                vm.courseInfo= data;
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
              $log.debug(data)
            }
            function onError(error) {
              $log.error();
                //AlertService.error(error.data.message);
            }
        }

        //路由跳转
        function stateGo(url){
          if(!vm.isAuthenticated){
            $uibModal.open({
              animation: true,
              templateUrl: 'app/components/login/login.html',
              controller: 'LoginController',
              controllerAs: 'vm',
              resolve:{
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('login');
                    return $translate.refresh();
                }]
              }
            }).result.then(function(result){
                // $state.go(url);
                //登录成功操作

            }, function(){

            });
          }else{
            $state.go(url);
          }

        }
    }
})();

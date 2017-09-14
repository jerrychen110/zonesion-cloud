(function() {
    'use strict';

    angular
        .module('zonesionCloudApplicationApp')
        .controller('CoursesController', CoursesController);

    CoursesController.$inject = ['$scope', '$rootScope', 'Principal', 'LoginService',
    '$state','$stateParams', 'CourseService', 'UsersService','$uibModal'];

    function CoursesController($scope, $rootScope, Principal, LoginService, $state, $stateParams,
      CourseService, UsersService,$uibModal) {
        var vm = this;
        vm.account = $rootScope.accountInfo;
        vm.isAuthenticated = Principal.isAuthenticated();
        vm.login = LoginService.open;
        vm.stateGo = stateGo;
        //登录成功  刷新信息
        $scope.$on('authenticationSuccess', function() {
          vm.account =  $rootScope.accountInfo;
          vm.isAuthenticated = Principal.isAuthenticated();
        });

        loadAll();
        loadUserInfo();

        function loadAll () {
        	CourseService.getCourseLessons({
        		id: $stateParams.id
            }, onSuccess, onError);

            function onSuccess(data, headers) {
                //
                // vm.courses = data[0];
                // vm.allcourses = data;
                // console.log(data);
                vm.courseInfo= data;

            }
            function onError(error) {
                //AlertService.error(error.data.message);
            }
        }

        function loadUserInfo () {
        	UsersService.query({
        		id: $stateParams.id
            }, onSuccess, onError);

            function onSuccess(data, headers) {

                vm.allusers = data;
                console.log(data);
                vm.countUsers = eval(data).length
            }
            function onError(error) {
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

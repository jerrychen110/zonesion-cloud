(function() {
    'use strict';

    angular
        .module('zonesionCloudApplicationApp')
        .controller('CoursesController', CoursesController);

    CoursesController.$inject = ['$scope', '$rootScope', 'Principal', 'LoginService',
    '$state','$stateParams', 'CourseService', 'UsersService','$uibModal','$log', 'CommonFactory'];

    function CoursesController($scope, $rootScope, Principal, LoginService, $state, $stateParams,
      CourseService, UsersService,$uibModal,$log, CommonFactory) {
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
                angular.forEach(vm.courseInfo.chapters,function(chapter){
                	angular.forEach(chapter.units,function(unit){
                		angular.forEach(unit.lessons,function(lesson){
                			lesson.mediaLength=CommonFactory.secondToTime(lesson.mediaLength);
                		});
                	});
                });
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
              $log.debug(data);
            }
            function onError(error) {
              $log.error();
                //AlertService.error(error.data.message);
            }
        }

        //路由跳转
        function stateGo(url,type,lessonId){
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
                getCourseBase();

            }, function(){

            });
          }else{
            if(type==0){
              CourseService.joinCourse({id:$stateParams.id,courseId:$stateParams.id,userId:vm.account.id},function(result){
                getCourseBase();
              },function(error){

              })
            }else{
              if(lessonId){
                $state.go('learn',{lessonId:lessonId});
              }else{
                CourseService.latestLearnLesson({
                    id:$stateParams.id,
                    userId:vm.account.id
                },
                function (result){
                    $state.go('learn',{lessonId:result.lessonId});
                },
                function (error) {

                })
              }

            }

          }

        }
    }
})();

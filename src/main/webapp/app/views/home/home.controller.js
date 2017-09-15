(function() {
    'use strict';

    angular
        .module('zonesionCloudApplicationApp')
        .controller('HomeCenterController', HomeCenterController);

    HomeCenterController.$inject = ['$scope', 'Principal', 'LoginService', '$state','CourseService','$rootScope'];

    function HomeCenterController ($scope, Principal, LoginService, $state, CourseService,$rootScope) {

        $(".swiper-container").luara({interval:3000,selected:"seleted",deriction:"left"});

        var vm = this;

        vm.account = $rootScope.accountInfo;
        vm.isAuthenticated = Principal.isAuthenticated;
        vm.login = LoginService.open;
        vm.register = register;
        vm.courseType = 'recommended';
        vm.toCourse = toCourse;
        vm.getCourseList=getCourseList;
        vm.getCourseList(vm.courseType);
        $scope.$on('authenticationSuccess', function() {
          console.log(  $rootScope.accountInfo);
          vm.account =  $rootScope.accountInfo;
        });

        function register () {
            $state.go('register');
        }

        function getCourseList(type) {
          vm.courseType = type;
          CourseService.getCourseList({courseQueryType:type},function(data, headers){
            vm.totalItems = headers('X-Total-Count');
            vm.queryCount = vm.totalItems;
            vm.courses = data;
          },function(error){

          })
        }


        function toCourse(courseId){
          $state.go('courses',{id:courseId});
        }
         /*$("#indicators li").click(function(){
             $(this).addClass("carouse_style");
         })*/

    }
})();

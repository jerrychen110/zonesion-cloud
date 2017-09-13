(function() {
    'use strict';

    angular
        .module('zonesionCloudApplicationApp')
        .controller('HomeCenterController', HomeCenterController);

    HomeCenterController.$inject = ['$scope', 'Principal', 'LoginService', '$state','Course','$rootScope'];

    function HomeCenterController ($scope, Principal, LoginService, $state, Course,$rootScope) {

        $(".swiper-container").luara({interval:3000,selected:"seleted",deriction:"left"});

        var vm = this;

        vm.account = $rootScope.accountInfo;
        vm.isAuthenticated = Principal.isAuthenticated;
        vm.login = LoginService.open;
        vm.register = register;
        vm.courseType = 'recommend';
        vm.getCourse = getCourse;
        vm.toCourse = toCourse;
        vm.getCourse(vm.courseType);
        $scope.$on('authenticationSuccess', function() {
          console.log(  $rootScope.accountInfo);
          vm.account =  $rootScope.accountInfo;
        });

        function register () {
            $state.go('register');
        }

        function getCourse(type){
          // $(".btn").button();
          switch (type) {
            case 'new':
            getNewCourse();
              break;
            case 'hot':
            getHotCourse();
              break;
            case 'recommend':
            getRecommendCourse();
              break;
            default:

          }
        }
        function getNewCourse() {
          Course.getNewCourse(function(data, headers){
            vm.totalItems = headers('X-Total-Count');
            vm.queryCount = vm.totalItems;
            vm.courses = data;
          },function(error){

          })
        }
        function getHotCourse() {
          Course.getHotCourse(function(data, headers){
            vm.totalItems = headers('X-Total-Count');
            vm.queryCount = vm.totalItems;
            vm.courses = data;
          },function(error){

          })
        }
        function getRecommendCourse() {
          Course.getRecommendCourse(function(data, headers){
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

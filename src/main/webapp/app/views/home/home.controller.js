(function() {
    'use strict';

    angular
        .module('zonesionCloudApplicationApp')
        .controller('HomeCenterController', HomeCenterController);

    HomeCenterController.$inject = ['$scope', 'Principal', 'LoginService', '$state','Course'];

    function HomeCenterController ($scope, Principal, LoginService, $state, Course) {

        $(".swiper-container").luara({interval:3000,selected:"seleted",deriction:"left"});

        var vm = this;

        vm.account = null;
        vm.isAuthenticated = null;
        vm.login = LoginService.open;
        vm.register = register;
        vm.getCourse = getCourse;
        vm.getCourse('new');
        $scope.$on('authenticationSuccess', function() {
            getAccount();
        });

        getAccount();

        function getAccount() {
            Principal.identity().then(function(account) {
                vm.account = account;
                vm.isAuthenticated = Principal.isAuthenticated;
            });
        }
        function register () {
            $state.go('register');
        }

        function getCourse(type){
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
         /*$("#indicators li").click(function(){
             $(this).addClass("carouse_style");
         })*/

    }
})();

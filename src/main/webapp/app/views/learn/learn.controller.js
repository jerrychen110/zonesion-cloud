(function() {
    'use strict';

    angular
        .module('zonesionCloudApplicationApp')
        .controller('LearnController', LearnController);

    LearnController.$inject = ['$scope', 'Principal', 'LoginService', '$state','Course','$rootScope'];

    function LearnController ($scope, Principal, LoginService, $state, Course,$rootScope) {

        $(".swiper-container").luara({interval:3000,selected:"seleted",deriction:"left"});

        var vm = this;

        vm.account = $rootScope.accountInfo;
        vm.isAuthenticated = Principal.isAuthenticated;
        vm.login = LoginService.open;
        vm.getCourse = getCourse;
        $scope.$on('authenticationSuccess', function() {
          console.log(  $rootScope.accountInfo);
          vm.account =  $rootScope.accountInfo;
        });
    }
})();

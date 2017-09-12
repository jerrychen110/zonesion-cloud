(function() {
    'use strict';

    angular
        .module('zonesionCloudApplicationApp')
        .controller('NavbarController', NavbarController);

    NavbarController.$inject = ['$state', 'Auth', 'Principal', 'ProfileService', 'LoginService','$scope','$rootScope'];

    function NavbarController ($state, Auth, Principal, ProfileService, LoginService,$scope,$rootScope) {
        var vm = this;

        vm.isNavbarCollapsed = true;
        vm.isAuthenticated = Principal.isAuthenticated;

        ProfileService.getProfileInfo().then(function(response) {
            vm.inProduction = response.inProduction;
            vm.swaggerEnabled = response.swaggerEnabled;
        });

        vm.login = login;
        vm.logout = logout;
        vm.toggleNavbar = toggleNavbar;
        vm.collapseNavbar = collapseNavbar;
        vm.$state = $state;

        function login() {
            collapseNavbar();
            LoginService.open();
        }

        function logout() {
            collapseNavbar();
            Auth.logout();
            $state.go('home-center');
        }

        function toggleNavbar() {
            vm.isNavbarCollapsed = !vm.isNavbarCollapsed;
        }

        function collapseNavbar() {
            vm.isNavbarCollapsed = true;
        }

        $scope.$watch(function(){
          return Principal.isAuthenticated()
        },function(newValue,oldValue){
          if(newValue){
            Principal.identity().then(function(account) {
              $rootScope.accountInfo = account;
              vm.isAuthenticated = Principal.isAuthenticated;
              $rootScope.$broadcast('authenticationSuccess');
            });
          }

        })
    }
})();

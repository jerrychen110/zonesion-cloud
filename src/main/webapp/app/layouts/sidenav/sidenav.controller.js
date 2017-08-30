(function() {
    'use strict';

    angular
        .module('zonesionCloudApplicationApp')
        .controller('SidenavController', SidenavController);

    SidenavController.$inject = ['$state', 'Auth', 'Principal', 'ProfileService', 'LoginService'];

    function SidenavController ($state, Auth, Principal, ProfileService, LoginService) {
        var vm = this;
    }
})();

(function() {
    'use strict';

    angular
        .module('zonesionCloudApplicationApp')
        .controller('SidenavController', SidenavController);

    SidenavController.$inject = ['$state', 'Auth', 'Principal', 'ProfileService', 'LoginService','$sce','SERVICEHTML'];

    function SidenavController ($state, Auth, Principal, ProfileService, LoginService,$sce,SERVICEHTML) {
        var vm = this;
         $("[data-toggle='tooltip']").tooltip();
         vm.htmlTooltip = $sce.trustAsHtml(SERVICEHTML);
    }
})();

(function() {
    'use strict';

    angular
        .module('zonesionCloudApplicationApp')
        .controller('CourseManagementController', CourseManagementController);

    CourseManagementController.$inject = ['Principal', 'ParseLinks', 'AlertService', '$state', 'JhiLanguageService'];

    function CourseManagementController(Principal, ParseLinks, AlertService, $state, JhiLanguageService) {
        var vm = this;
        
        vm.authorities = ['ROLE_USER', 'ROLE_ADMIN'];
        vm.currentAccount = null;
        vm.languages = null;
        
        JhiLanguageService.getAll().then(function (languages) {
            vm.languages = languages;
        });
        Principal.identity().then(function(account) {
            vm.currentAccount = account;
        });
    }
})();
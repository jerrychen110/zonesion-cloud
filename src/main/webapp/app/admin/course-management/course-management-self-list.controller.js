(function() {
    'use strict';

    angular
        .module('zonesionCloudApplicationApp')
        .controller('CourseManagementSelfListController', CourseManagementSelfListController);

    CourseManagementSelfListController.$inject = ['Principal', 'Course', 'ParseLinks', 'AlertService', '$state', 'pagingParams', 'custParams', 'paginationConstants', 'JhiLanguageService'];

    function CourseManagementSelfListController(Principal, Course, ParseLinks, AlertService, $state, pagingParams, custParams, paginationConstants, JhiLanguageService) {
        var vm = this;

        vm.authorities = ['ROLE_USER', 'ROLE_ADMIN'];
        vm.currentAccount = null;
        vm.languages = null;
        vm.loadAll = loadAll;
        vm.page = 1;
        vm.totalItems = null;
//        vm.clear = clear;
        vm.links = null;
        vm.loadPage = loadPage;
        vm.predicate = pagingParams.predicate;
        vm.reverse = pagingParams.ascending;
        vm.itemsPerPage = paginationConstants.itemsPerPage;
        vm.transition = transition;
        vm.loadAll();
        JhiLanguageService.getAll().then(function (languages) {
            vm.languages = languages;
        });
        Principal.identity().then(function(account) {
            vm.currentAccount = account;
        });

        function loadAll () {
            Course.query({
                page: pagingParams.page - 1,
                size: vm.itemsPerPage,
                sort: sort(),
                courseType: custParams.courseType,
                courseSource: custParams.courseSource
            }, onSuccess, onError);
            function sort() {
                var result = [vm.predicate + ',' + (vm.reverse ? 'asc' : 'desc')];
                if (vm.predicate !== 'id') {
                    result.push('id');
                }
                return result;
            }
            function onSuccess(data, headers) {
                vm.links = ParseLinks.parse(headers('link'));
                vm.totalItems = headers('X-Total-Count');
                vm.queryCount = vm.totalItems;
                vm.courses = data;
                vm.page = pagingParams.page;
                vm.statesObj= {
               		 0:"未发布",
               		 1:"已发布",
               		 2:"已关闭"
               		};
            }
            function onError(error) {
                AlertService.error(error.data.message);
            }
        }

//        function clear () {
//            vm.user = {
//                id: null, login: null, name: null, email: null,
//                activated: null, langKey: null, createdBy: null, createdDate: null,
//                lastModifiedBy: null, lastModifiedDate: null, resetDate: null,
//                resetKey: null, authorities: null
//            };
//        }

        function sort () {
            var result = [vm.predicate + ',' + (vm.reverse ? 'asc' : 'desc')];
            if (vm.predicate !== 'id') {
                result.push('id');
            }
            return result;
        }

        function loadPage (page) {
            vm.page = page;
            vm.transition();
        }

        function transition () {
            $state.transitionTo($state.$current, {
                page: vm.page,
                sort: vm.predicate + ',' + (vm.reverse ? 'asc' : 'desc'),
                search: vm.currentSearch
            });
        }
    }
})();
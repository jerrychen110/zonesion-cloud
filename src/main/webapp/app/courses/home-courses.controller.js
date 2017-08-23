(function() {
    'use strict';

    angular
        .module('zonesionCloudApplicationApp')
        .controller('HomeCoursesController', HomeCoursesController);

    HomeCoursesController.$inject = ['$scope', '$rootScope', '$stateParams', 'HomeCoursesService'];

    function HomeCoursesController($scope, $rootScope, $stateParams, HomeCoursesService) {
        var vm = this;
        vm.countUser = $stateParams.countUser
        console.log($stateParams.countUser);
        loadAll();

        function loadAll () {
        	HomeCoursesService.query({
        		id: $stateParams.id
                //size: vm.itemsPerPage
                //sort: sort()
            }, onSuccess, onError);
            /*function sort() {
                var result = [vm.predicate + ',' + (vm.reverse ? 'asc' : 'desc')];
                if (vm.predicate !== 'id') {
                    result.push('id');
                }
                return result;
            }*/
            function onSuccess(data, headers) {
                //vm.links = ParseLinks.parse(headers('link'));
                vm.totalItems = headers('X-Total-Count');
                vm.queryCount = vm.totalItems;
                vm.courses = data[0];
                vm.allcourses = data;
            }
            function onError(error) {
                //AlertService.error(error.data.message);
            }
        }
    }
})();

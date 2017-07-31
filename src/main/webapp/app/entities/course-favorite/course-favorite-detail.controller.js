(function() {
    'use strict';

    angular
        .module('zonesionCloudApplicationApp')
        .controller('CourseFavoriteDetailController', CourseFavoriteDetailController);

    CourseFavoriteDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'CourseFavorite', 'Course'];

    function CourseFavoriteDetailController($scope, $rootScope, $stateParams, previousState, entity, CourseFavorite, Course) {
        var vm = this;

        vm.courseFavorite = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('zonesionCloudApplicationApp:courseFavoriteUpdate', function(event, result) {
            vm.courseFavorite = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();

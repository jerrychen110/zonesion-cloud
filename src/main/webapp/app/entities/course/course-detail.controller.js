(function() {
    'use strict';

    angular
        .module('zonesionCloudApplicationApp')
        .controller('CourseDetailController', CourseDetailController);

    CourseDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Course', 'CourseFavorite', 'Chapter', 'CourseReview'];

    function CourseDetailController($scope, $rootScope, $stateParams, previousState, entity, Course, CourseFavorite, Chapter, CourseReview) {
        var vm = this;

        vm.course = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('zonesionCloudApplicationApp:courseUpdate', function(event, result) {
            vm.course = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();

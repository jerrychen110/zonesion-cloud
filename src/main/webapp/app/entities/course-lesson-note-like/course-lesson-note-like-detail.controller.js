(function() {
    'use strict';

    angular
        .module('zonesionCloudApplicationApp')
        .controller('CourseLessonNoteLikeDetailController', CourseLessonNoteLikeDetailController);

    CourseLessonNoteLikeDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'CourseLessonNoteLike', 'CourseLessonNote'];

    function CourseLessonNoteLikeDetailController($scope, $rootScope, $stateParams, previousState, entity, CourseLessonNoteLike, CourseLessonNote) {
        var vm = this;

        vm.courseLessonNoteLike = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('zonesionCloudApplicationApp:courseLessonNoteLikeUpdate', function(event, result) {
            vm.courseLessonNoteLike = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();

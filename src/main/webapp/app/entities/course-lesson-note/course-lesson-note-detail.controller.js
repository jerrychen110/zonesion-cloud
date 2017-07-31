(function() {
    'use strict';

    angular
        .module('zonesionCloudApplicationApp')
        .controller('CourseLessonNoteDetailController', CourseLessonNoteDetailController);

    CourseLessonNoteDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'CourseLessonNote', 'CourseLesson', 'CourseLessonNoteLike'];

    function CourseLessonNoteDetailController($scope, $rootScope, $stateParams, previousState, entity, CourseLessonNote, CourseLesson, CourseLessonNoteLike) {
        var vm = this;

        vm.courseLessonNote = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('zonesionCloudApplicationApp:courseLessonNoteUpdate', function(event, result) {
            vm.courseLessonNote = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();

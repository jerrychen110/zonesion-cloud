(function() {
    'use strict';

    angular
        .module('zonesionCloudApplicationApp')
        .controller('CourseLessonDetailController', CourseLessonDetailController);

    CourseLessonDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'CourseLesson', 'CourseLessonAttachment', 'CourseLessonLearn', 'CourseLessonNote', 'Chapter'];

    function CourseLessonDetailController($scope, $rootScope, $stateParams, previousState, entity, CourseLesson, CourseLessonAttachment, CourseLessonLearn, CourseLessonNote, Chapter) {
        var vm = this;

        vm.courseLesson = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('zonesionCloudApplicationApp:courseLessonUpdate', function(event, result) {
            vm.courseLesson = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();

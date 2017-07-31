(function() {
    'use strict';

    angular
        .module('zonesionCloudApplicationApp')
        .controller('CourseLessonLearnDetailController', CourseLessonLearnDetailController);

    CourseLessonLearnDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'CourseLessonLearn', 'CourseLesson'];

    function CourseLessonLearnDetailController($scope, $rootScope, $stateParams, previousState, entity, CourseLessonLearn, CourseLesson) {
        var vm = this;

        vm.courseLessonLearn = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('zonesionCloudApplicationApp:courseLessonLearnUpdate', function(event, result) {
            vm.courseLessonLearn = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();

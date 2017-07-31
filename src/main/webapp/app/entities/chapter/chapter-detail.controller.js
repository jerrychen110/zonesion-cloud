(function() {
    'use strict';

    angular
        .module('zonesionCloudApplicationApp')
        .controller('ChapterDetailController', ChapterDetailController);

    ChapterDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Chapter', 'CourseLesson', 'Course'];

    function ChapterDetailController($scope, $rootScope, $stateParams, previousState, entity, Chapter, CourseLesson, Course) {
        var vm = this;

        vm.chapter = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('zonesionCloudApplicationApp:chapterUpdate', function(event, result) {
            vm.chapter = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();

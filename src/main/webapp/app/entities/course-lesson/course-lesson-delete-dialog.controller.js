(function() {
    'use strict';

    angular
        .module('zonesionCloudApplicationApp')
        .controller('CourseLessonDeleteController',CourseLessonDeleteController);

    CourseLessonDeleteController.$inject = ['$uibModalInstance', 'entity', 'CourseLesson'];

    function CourseLessonDeleteController($uibModalInstance, entity, CourseLesson) {
        var vm = this;

        vm.courseLesson = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            CourseLesson.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();

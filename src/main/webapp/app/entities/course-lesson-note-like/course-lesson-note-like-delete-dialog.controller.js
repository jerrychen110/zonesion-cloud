(function() {
    'use strict';

    angular
        .module('zonesionCloudApplicationApp')
        .controller('CourseLessonNoteLikeDeleteController',CourseLessonNoteLikeDeleteController);

    CourseLessonNoteLikeDeleteController.$inject = ['$uibModalInstance', 'entity', 'CourseLessonNoteLike'];

    function CourseLessonNoteLikeDeleteController($uibModalInstance, entity, CourseLessonNoteLike) {
        var vm = this;

        vm.courseLessonNoteLike = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            CourseLessonNoteLike.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();

(function() {
    'use strict';

    angular
        .module('zonesionCloudApplicationApp')
        .controller('CourseLessonNoteDeleteController',CourseLessonNoteDeleteController);

    CourseLessonNoteDeleteController.$inject = ['$uibModalInstance', 'entity', 'CourseLessonNote'];

    function CourseLessonNoteDeleteController($uibModalInstance, entity, CourseLessonNote) {
        var vm = this;

        vm.courseLessonNote = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            CourseLessonNote.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();

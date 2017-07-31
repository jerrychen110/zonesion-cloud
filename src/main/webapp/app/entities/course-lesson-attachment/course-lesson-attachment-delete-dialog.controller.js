(function() {
    'use strict';

    angular
        .module('zonesionCloudApplicationApp')
        .controller('CourseLessonAttachmentDeleteController',CourseLessonAttachmentDeleteController);

    CourseLessonAttachmentDeleteController.$inject = ['$uibModalInstance', 'entity', 'CourseLessonAttachment'];

    function CourseLessonAttachmentDeleteController($uibModalInstance, entity, CourseLessonAttachment) {
        var vm = this;

        vm.courseLessonAttachment = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            CourseLessonAttachment.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();

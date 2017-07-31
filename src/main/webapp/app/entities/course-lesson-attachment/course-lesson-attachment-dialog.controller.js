(function() {
    'use strict';

    angular
        .module('zonesionCloudApplicationApp')
        .controller('CourseLessonAttachmentDialogController', CourseLessonAttachmentDialogController);

    CourseLessonAttachmentDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'CourseLessonAttachment', 'CourseLesson'];

    function CourseLessonAttachmentDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, CourseLessonAttachment, CourseLesson) {
        var vm = this;

        vm.courseLessonAttachment = entity;
        vm.clear = clear;
        vm.save = save;
        vm.courselessons = CourseLesson.query();

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.courseLessonAttachment.id !== null) {
                CourseLessonAttachment.update(vm.courseLessonAttachment, onSaveSuccess, onSaveError);
            } else {
                CourseLessonAttachment.save(vm.courseLessonAttachment, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('zonesionCloudApplicationApp:courseLessonAttachmentUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();

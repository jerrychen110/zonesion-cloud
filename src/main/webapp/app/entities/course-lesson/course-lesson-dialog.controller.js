(function() {
    'use strict';

    angular
        .module('zonesionCloudApplicationApp')
        .controller('CourseLessonDialogController', CourseLessonDialogController);

    CourseLessonDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'CourseLesson', 'CourseLessonAttachment', 'CourseLessonLearn', 'CourseLessonNote', 'Chapter'];

    function CourseLessonDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, CourseLesson, CourseLessonAttachment, CourseLessonLearn, CourseLessonNote, Chapter) {
        var vm = this;

        vm.courseLesson = entity;
        vm.clear = clear;
        vm.save = save;
        vm.courselessonattachments = CourseLessonAttachment.query();
        vm.courselessonlearns = CourseLessonLearn.query();
        vm.courselessonnotes = CourseLessonNote.query();
        vm.chapters = Chapter.query();

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.courseLesson.id !== null) {
                CourseLesson.update(vm.courseLesson, onSaveSuccess, onSaveError);
            } else {
                CourseLesson.save(vm.courseLesson, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('zonesionCloudApplicationApp:courseLessonUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();

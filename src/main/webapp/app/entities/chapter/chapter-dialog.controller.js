(function() {
    'use strict';

    angular
        .module('zonesionCloudApplicationApp')
        .controller('ChapterDialogController', ChapterDialogController);

    ChapterDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Chapter', 'CourseLesson', 'Course'];

    function ChapterDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Chapter, CourseLesson, Course) {
        var vm = this;

        vm.chapter = entity;
        vm.clear = clear;
        vm.save = save;
        vm.courselessons = CourseLesson.query();
        vm.courses = Course.query();

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.chapter.id !== null) {
                Chapter.update(vm.chapter, onSaveSuccess, onSaveError);
            } else {
                Chapter.save(vm.chapter, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('zonesionCloudApplicationApp:chapterUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();

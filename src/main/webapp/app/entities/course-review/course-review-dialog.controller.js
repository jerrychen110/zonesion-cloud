(function() {
    'use strict';

    angular
        .module('zonesionCloudApplicationApp')
        .controller('CourseReviewDialogController', CourseReviewDialogController);

    CourseReviewDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'CourseReview', 'Course'];

    function CourseReviewDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, CourseReview, Course) {
        var vm = this;

        vm.courseReview = entity;
        vm.clear = clear;
        vm.save = save;
        vm.courses = Course.query();

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.courseReview.id !== null) {
                CourseReview.update(vm.courseReview, onSaveSuccess, onSaveError);
            } else {
                CourseReview.save(vm.courseReview, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('zonesionCloudApplicationApp:courseReviewUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();

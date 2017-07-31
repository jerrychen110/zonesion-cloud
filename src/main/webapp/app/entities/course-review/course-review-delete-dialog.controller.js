(function() {
    'use strict';

    angular
        .module('zonesionCloudApplicationApp')
        .controller('CourseReviewDeleteController',CourseReviewDeleteController);

    CourseReviewDeleteController.$inject = ['$uibModalInstance', 'entity', 'CourseReview'];

    function CourseReviewDeleteController($uibModalInstance, entity, CourseReview) {
        var vm = this;

        vm.courseReview = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            CourseReview.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();

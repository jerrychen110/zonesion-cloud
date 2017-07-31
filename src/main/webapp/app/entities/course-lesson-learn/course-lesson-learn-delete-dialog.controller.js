(function() {
    'use strict';

    angular
        .module('zonesionCloudApplicationApp')
        .controller('CourseLessonLearnDeleteController',CourseLessonLearnDeleteController);

    CourseLessonLearnDeleteController.$inject = ['$uibModalInstance', 'entity', 'CourseLessonLearn'];

    function CourseLessonLearnDeleteController($uibModalInstance, entity, CourseLessonLearn) {
        var vm = this;

        vm.courseLessonLearn = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            CourseLessonLearn.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();

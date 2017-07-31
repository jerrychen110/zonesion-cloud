(function() {
    'use strict';

    angular
        .module('zonesionCloudApplicationApp')
        .controller('CourseFavoriteDeleteController',CourseFavoriteDeleteController);

    CourseFavoriteDeleteController.$inject = ['$uibModalInstance', 'entity', 'CourseFavorite'];

    function CourseFavoriteDeleteController($uibModalInstance, entity, CourseFavorite) {
        var vm = this;

        vm.courseFavorite = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            CourseFavorite.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();

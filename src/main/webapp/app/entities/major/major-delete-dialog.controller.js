(function() {
    'use strict';

    angular
        .module('zonesionCloudApplicationApp')
        .controller('MajorDeleteController',MajorDeleteController);

    MajorDeleteController.$inject = ['$uibModalInstance', 'entity', 'Major'];

    function MajorDeleteController($uibModalInstance, entity, Major) {
        var vm = this;

        vm.major = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Major.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();

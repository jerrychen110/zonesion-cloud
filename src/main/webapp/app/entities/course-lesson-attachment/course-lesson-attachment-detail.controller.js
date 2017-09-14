(function() {
    'use strict';

    angular
        .module('zonesionCloudApplicationApp')
        .controller('CourseLessonAttachmentDetailController', CourseLessonAttachmentDetailController);

    CourseLessonAttachmentDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'CourseLessonAttachment'];

    function CourseLessonAttachmentDetailController($scope, $rootScope, $stateParams, previousState, entity, CourseLessonAttachment) {
        var vm = this;

        vm.courseLessonAttachment = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('zonesionCloudApplicationApp:courseLessonAttachmentUpdate', function(event, result) {
            vm.courseLessonAttachment = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();

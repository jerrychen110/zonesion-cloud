(function() {
    'use strict';

    angular
        .module('zonesionCloudApplicationApp')
        .controller('CourseReviewDetailController', CourseReviewDetailController);

    CourseReviewDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'CourseReview', 'Course'];

    function CourseReviewDetailController($scope, $rootScope, $stateParams, previousState, entity, CourseReview, Course) {
        var vm = this;

        vm.courseReview = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('zonesionCloudApplicationApp:courseReviewUpdate', function(event, result) {
            vm.courseReview = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();

(function() {
    'use strict';

    angular
        .module('zonesionCloudApplicationApp')
        .controller('NotesController', NotesController);

    NotesController.$inject = ['$scope', '$rootScope', '$stateParams', 'CourseService'];

    function NotesController($scope, $rootScope, $stateParams, CourseService) {
        var vm = this;
        vm.allnotes = [];
        loadNotes();
        vm.courseLessonId = $stateParams.courseLessonId;
        function loadNotes () {
        	CourseService.getCourseNotes({
        		id: $stateParams.id
            }, onSuccess, onError);

            function onSuccess(data, headers) {

                vm.allnotes = data;
                console.log(data);

            }
            function onError(error) {
                //AlertService.error(error.data.message);
            }
        }

    }
})();

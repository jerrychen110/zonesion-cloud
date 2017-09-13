(function() {
    'use strict';

    angular
        .module('zonesionCloudApplicationApp')
        .controller('NotesController', NotesController);

    NotesController.$inject = ['$scope', '$rootScope', '$stateParams', 'NotesService'];

    function NotesController($scope, $rootScope, $stateParams, NotesService) {
        var vm = this;
        loadNotes();
        vm.courseLessonId = $stateParams.courseLessonId;
        function loadNotes () {
        	NotesService.query({
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

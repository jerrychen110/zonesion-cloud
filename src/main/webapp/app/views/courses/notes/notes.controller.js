(function() {
    'use strict';

    angular
        .module('zonesionCloudApplicationApp')
        .controller('NotesController', NotesController);

    NotesController.$inject = ['$scope', '$rootScope', '$stateParams', 'CourseService'];

    function NotesController($scope, $rootScope, $stateParams, CourseService) {
        var vm = this;
        vm.courseId = $stateParams.id;
        vm.allnotes = [];
        vm.currentPage = 1;
        vm.pageSize=1;
        vm.allreviews = [];
        vm.selectedLesson={id:null,title:'全部课程'};
        vm.loadNotes=loadNotes;
        vm.loadLessons = loadLessons;
        vm.selectLesson = selectLesson;
        vm.loadNotes();
        vm.loadLessons();
        vm.courseLessonId = $stateParams.courseLessonId;
        //get 笔记数据
        function loadNotes () {
        	CourseService.getCourseNotes({
        		id: vm.courseId,
            page:vm.currentPage,
            size:vm.pageSize,
            courseLessonId:vm.selectedLesson.id
            }, onSuccess, onError);

            function onSuccess(data, headers) {
              vm.totalCount = headers('X-Total-Count');
              vm.allnotes = data;
              console.log(data);

            }
            function onError(error) {
                //AlertService.error(error.data.message);
            }
        }

        //get 所有课程
        function loadLessons(){
          CourseService.getLessons({id:vm.courseId},onSuccess, onError);
            function onSuccess(data, headers) {
                vm.lessonDatas = data;
                vm.lessonDatas.unshift(vm.selectedLesson);
            }
            function onError(error) {
                //AlertService.error(error.data.message);
            }
        }

        //选择课程
        function selectLesson(lessonData){
          vm.selectedLesson = {id:lessonData.id,title:lessonData.title};
          vm.loadNotes();
        }

    }
})();

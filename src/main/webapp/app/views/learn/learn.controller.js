(function() {
    'use strict';

    angular
        .module('zonesionCloudApplicationApp')
        .controller('LearnController', LearnController);

    LearnController.$inject = ['$scope', 'Principal', 'LoginService', '$state','Course','$rootScope','$stateParams','CourseService','$log','LEFTTOOL','LearnService'];

    function LearnController ($scope, Principal, LoginService, $state, Course,$rootScope,$stateParams,CourseService,$log,LEFTTOOL,LearnService) {
        var vm = this;
        vm.showNavContainer = true;
        vm.toolbarInfos = angular.copy(LEFTTOOL);
        vm.selectedLessonId=$stateParams.lessonId;
        vm.courseId=$stateParams.id;

        vm.account = $rootScope.accountInfo;
        vm.isAuthenticated = Principal.isAuthenticated;
        vm.activeTab = 1;
        vm.closeContainer = closeContainer;
        vm.seleteToolBar = seleteToolBar;
        vm.selectedLesson = selectedLesson;
        vm.doneLearn = doneLearn;
        $scope.$on('authenticationSuccess', function() {
          console.log(  $rootScope.accountInfo);
          vm.account =  $rootScope.accountInfo;
        });

        vm.getCourseLessons = getCourseLessons();
        vm.lessonNoteContent = '';
        vm.saveLessonNote = saveLessonNote;

        // 课程信息
        function getCourseLessons () {
            CourseService.getCourseLessons({
                id: vm.courseId
            }, onSuccess, onError);

            function onSuccess(data, headers) {
                vm.courseInfo= data;
                //selected lesson  active
                _.forEach(vm.courseInfo.chapters,function(course){
                  _.forEach(course.units,function(unit){
                    _.forEach(unit.lessons,function(lesson){
                      if(lesson.id==vm.selectedLessonId){
                        lesson.active=true;
                        vm.lessonType = lesson.courseLessonType;
                        vm.mediaUri = lesson.mediaUri;
                        vm.contentInfo = lesson.content;
                      }else{
                        lesson.active=false;
                      }
                    })
                  })
                })
            }
            function onError(error) {
                //AlertService.error(error.data.message);
            }
        }

        //添加笔记
        function saveLessonNote() {
          if (vm.lessonNoteContent) {
            CourseService.saveLessonNote({
                id: vm.courseId,
                courseId: $stateParams.id,
                courseLessonId: $stateParams.lessonId,
                userId: vm.account.id,
                content: vm.lessonNoteContent
            }, onSuccess, onError);
            function onSuccess(data) {
              // console.log(data);
              if (data) {
                console.log('笔记保存成功')
              }
            }
            function onError(error) {
              //AlertService.error(error.data.message);
            }
          }else{
            console.log('笔记不能为空');
          }
        }

        //选择菜单
        function seleteToolBar(selectedIndex){
          vm.activeTab = selectedIndex+1;
          var oldSelect = _.findIndex(vm.toolbarInfos,{active:true})
          if(oldSelect==selectedIndex){
            vm.showNavContainer = !vm.showNavContainer;
            return;
          }else{
            _.forEach(vm.toolbarInfos,function(tools,index){
              if(selectedIndex==index){
                tools.active=true;
              }else{
                tools.active=false;
              }
            })
            vm.showNavContainer = true;
          }
        }

        //选择课程
        function selectedLesson(lessonId,index,unitIndex,chapterIndex){
          vm.selectedLessonId = lessonId;
          _.forEach(vm.courseInfo.chapters,function(course){
            _.forEach(course.units,function(unit){
              _.forEach(unit.lessons,function(lesson){
                if(lesson.id==vm.selectedLessonId){
                  lesson.active=true;
                  vm.lessonType = lesson.courseLessonType;
                  vm.mediaUri = lesson.mediaUri;
                  vm.contentInfo = lesson.content;
                }else{
                  lesson.active=false;
                }
              })
            })
          })
          if(vm.courseInfo.chapters[chapterIndex].units[unitIndex].lessons[index].learnedStatus!='0'){
            return;
          }else{
            LearnService.doLearn({id:lessonId,courseId:parseInt(vm.courseId),userId:vm.account.id},function(result){
              vm.courseInfo.chapters[chapterIndex].units[unitIndex].lessons[index].learnedStatus = 1;
            },function(error){

            })
          }
        }

        //学习完该课时
        function doneLearn() {
          LearnService.overLearn({id:vm.selectedLessonId,courseId:parseInt(vm.courseId),userId:vm.account.id},function(result){
            vm.courseInfo.chapters[chapterIndex].units[unitIndex].lessons[index].learnedStatus = 2;
          },function(error){

          })
        }


        // 关闭侧面菜单详情
        function closeContainer(){
          vm.showNavContainer = false;
        }

    }
})();

(function() {
    'use strict';

    angular
        .module('zonesionCloudApplicationApp')
        .controller('LessonDatabaseController', LessonDatabaseController);

    LessonDatabaseController.$inject = ['$scope', '$rootScope', '$stateParams', 'CourseService','Principal','CommonFactory','AttachementService'];

    function LessonDatabaseController($scope, $rootScope, $stateParams, CourseService,Principal,CommonFactory,AttachementService) {
        var vm = this;
        vm.isAuthenticated = Principal.isAuthenticated;
        // 初始化分页数据
        vm.currentPage = 1;
        vm.pageSize = 10;
        vm.attachements = [];
        vm.getLessonAttachments=getLessonAttachments;
        vm.getLessonAttachments();

        //获取资料库列表数据
        function getLessonAttachments() {
        	CourseService.getLessonAttachments({
        		id: $stateParams.lessonId,
                page:vm.currentPage-1,
                size:vm.pageSize
            }, onSuccess, onError);

            function onSuccess(data, headers) {
                console.log(data);
                vm.attachements = data;
                vm.totalCount = parseInt(headers('X-Total-Count'));
                //数据处理（size和时间）
                angular.forEach(vm.attachements,function(att){
                  att.fileSize=CommonFactory.bytesToSize(att.fileSize);
                  att.createdDate=moment(att.createdDate).format('YYYY-MM-DD HH:mm:ss');
                })
            }
            function onError(error) {
                //AlertService.error(error.data.message);
            }
        }
    }
})();

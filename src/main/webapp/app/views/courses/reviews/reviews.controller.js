(function() {
    'use strict';

    angular
        .module('zonesionCloudApplicationApp')
        .controller('ReviewsController', ReviewsController);

    ReviewsController.$inject = ['$scope', '$rootScope', '$stateParams', 'CourseService','Principal','LoginService'];

    function ReviewsController($scope, $rootScope, $stateParams, CourseService,Principal,LoginService) {
        var vm = this;
        vm.isAuthenticated = Principal.isAuthenticated();
        vm.account =  $rootScope.accountInfo;
        vm.currentPage = 1;
        vm.pageSize=10;
        vm.allreviews = [];
        vm.openReviewFlag = false;
        vm.reviewContent = '';
        vm.openReview = openReview;
        vm.cancelReview = cancelReview;
        vm.saveReview = saveReview;
        vm.loadReviews = loadReviews;
        vm.loadReviews();
        //登录成功  刷新信息
        $scope.$on('authenticationSuccess', function() {
          vm.account =  $rootScope.accountInfo;
          vm.isAuthenticated = Principal.isAuthenticated();
        });

        function loadReviews() {
        	CourseService.getCourseReviews({
        		id: $stateParams.id,
            page:vm.currentPage-1,
            size:vm.pageSize
            }, onSuccess, onError);

            function onSuccess(data, headers) {
                vm.allreviews = data;
                angular.forEach(vm.allreviews,function(reviewInfo){
                  reviewInfo.lastModifiedDate = moment(reviewInfo.lastModifiedDate).format('YYYY-MM-DD HH:mm:ss');
                })
                console.log(data);
            }
            function onError(error) {
                //AlertService.error(error.data.message);
            }
        }

        //打开评论
        function openReview(){
          if(!vm.isAuthenticated){
            LoginService.open();
          }else{
            vm.openReviewFlag = true;
          }
        }

        //save review
        function saveReview(){
          var params = {
            id:$stateParams.id,
            content: vm.reviewContent,
            privacy: 0,
            rating: 0,
            title: "string",
            userId: vm.account.id
          }
          // {
          //   "content": vm.reviewContent,
          //   "courseId": $stateParams.id,
          //   "userId": vm.account.id
          // }
          CourseService.saveReview(params,onSuccess, onError);
          function onSuccess(data, headers) {
            vm.openReviewFlag = false;
            vm.currentPage = 1;
            vm.loadReviews();
          }
          function onError(error) {
              //AlertService.error(error.data.message);
          }
        }

        //取消评论
        function cancelReview(){
          vm.openReviewFlag = false;
        }


    }
})();

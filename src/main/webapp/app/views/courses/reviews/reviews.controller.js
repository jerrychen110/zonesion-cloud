(function() {
    'use strict';

    angular
        .module('zonesionCloudApplicationApp')
        .controller('ReviewsController', ReviewsController);

    ReviewsController.$inject = ['$scope', '$rootScope', '$stateParams', 'ReviewsService'];

    function ReviewsController($scope, $rootScope, $stateParams, ReviewsService) {
        var vm = this;
        loadReviews();
        
        function loadReviews() {
        	ReviewsService.query({
        		id: $stateParams.id
            }, onSuccess, onError);

            function onSuccess(data, headers) {

                vm.allreviews = data;               
                console.log(data);
 
            }
            function onError(error) {
                //AlertService.error(error.data.message);
            }
        }
    }
})();

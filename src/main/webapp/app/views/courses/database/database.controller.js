(function() {
    'use strict';

    angular
        .module('zonesionCloudApplicationApp')
        .controller('DatabaseController', DatabaseController);

    DatabaseController.$inject = ['$scope', '$rootScope', '$stateParams', 'ReviewsService','Principal'];

    function DatabaseController($scope, $rootScope, $stateParams, ReviewsService,Principal) {
        var vm = this;
        vm.isAuthenticated = Principal.isAuthenticated;
        loadReviews();

        function loadReviews() {
        	// ReviewsService.query({
        	// 	id: $stateParams.id
          //   }, onSuccess, onError);
          //
          //   function onSuccess(data, headers) {
          //
          //       vm.allreviews = data;
          //       console.log(data);
          //
          //   }
          //   function onError(error) {
          //       //AlertService.error(error.data.message);
          //   }
        }
    }
})();

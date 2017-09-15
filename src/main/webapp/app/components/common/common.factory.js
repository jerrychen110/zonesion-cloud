(function() {
'use strict';

angular.module('zonesionCloudApplicationApp')
    .factory('CommonFactory', ['Principal', '$templateRequest', '$compile', function(Principal, $templateRequest, $compile) {
      return {
        bytesToSize:function(bytes) {
          if (bytes === 0) return '0 B';
          var k = 1024;
          var sizes = ['B','KB', 'MB', 'GB', 'TB', 'PB', 'EB', 'ZB', 'YB'];
          var i = Math.floor(Math.log(bytes) / Math.log(k));
          return Math.floor(((bytes / Math.pow(k, i)) ) * 100) / 100  + sizes[i] ;                                                                                                        //return (bytes / Math.pow(k, i)).toPrecision(3) + ' ' + sizes[i];
        }
      };
    }]);
})();

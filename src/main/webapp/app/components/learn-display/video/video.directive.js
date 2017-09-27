(function() {
    'use strict';

    angular
        .module('zonesionCloudApplicationApp')
        .directive('videoContainer', videoContainer);
        videoContainer.$inject = ['$templateRequest','$compile'];
        function videoContainer ($templateRequest,$compile) {
            var directive = {
                restrict: 'AE',
                scope: {
                  content: '=?',
                },
                link: linkFunc
            };

            return directive;

            function linkFunc (scope, element, attrs, formCtrl) {
              // 重新compile 刷新数据显示
              function initTemp() {
                $templateRequest('app/components/learn-display/video/video.html')
                .then(function(tmplHtml){
                  element.html($compile(tmplHtml)(scope));
                });
              }
              initTemp();
            }
        }
})();

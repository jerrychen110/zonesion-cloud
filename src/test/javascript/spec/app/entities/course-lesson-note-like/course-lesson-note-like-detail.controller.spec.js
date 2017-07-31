'use strict';

describe('Controller Tests', function() {

    describe('CourseLessonNoteLike Management Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockPreviousState, MockCourseLessonNoteLike, MockCourseLessonNote;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockPreviousState = jasmine.createSpy('MockPreviousState');
            MockCourseLessonNoteLike = jasmine.createSpy('MockCourseLessonNoteLike');
            MockCourseLessonNote = jasmine.createSpy('MockCourseLessonNote');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity,
                'previousState': MockPreviousState,
                'CourseLessonNoteLike': MockCourseLessonNoteLike,
                'CourseLessonNote': MockCourseLessonNote
            };
            createController = function() {
                $injector.get('$controller')("CourseLessonNoteLikeDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'zonesionCloudApplicationApp:courseLessonNoteLikeUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});

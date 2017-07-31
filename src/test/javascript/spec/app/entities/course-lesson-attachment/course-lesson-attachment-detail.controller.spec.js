'use strict';

describe('Controller Tests', function() {

    describe('CourseLessonAttachment Management Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockPreviousState, MockCourseLessonAttachment, MockCourseLesson;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockPreviousState = jasmine.createSpy('MockPreviousState');
            MockCourseLessonAttachment = jasmine.createSpy('MockCourseLessonAttachment');
            MockCourseLesson = jasmine.createSpy('MockCourseLesson');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity,
                'previousState': MockPreviousState,
                'CourseLessonAttachment': MockCourseLessonAttachment,
                'CourseLesson': MockCourseLesson
            };
            createController = function() {
                $injector.get('$controller')("CourseLessonAttachmentDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'zonesionCloudApplicationApp:courseLessonAttachmentUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});

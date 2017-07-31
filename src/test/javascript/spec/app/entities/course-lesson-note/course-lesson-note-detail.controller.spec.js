'use strict';

describe('Controller Tests', function() {

    describe('CourseLessonNote Management Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockPreviousState, MockCourseLessonNote, MockCourseLesson, MockCourseLessonNoteLike;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockPreviousState = jasmine.createSpy('MockPreviousState');
            MockCourseLessonNote = jasmine.createSpy('MockCourseLessonNote');
            MockCourseLesson = jasmine.createSpy('MockCourseLesson');
            MockCourseLessonNoteLike = jasmine.createSpy('MockCourseLessonNoteLike');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity,
                'previousState': MockPreviousState,
                'CourseLessonNote': MockCourseLessonNote,
                'CourseLesson': MockCourseLesson,
                'CourseLessonNoteLike': MockCourseLessonNoteLike
            };
            createController = function() {
                $injector.get('$controller')("CourseLessonNoteDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'zonesionCloudApplicationApp:courseLessonNoteUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
